package services;

import domain.Chirp;
import domain.Chorbi;
import domain.Liked;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.ChorbiRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class ChorbiService {

    // Managed Repository ------------------------
    @Autowired
    private ChorbiRepository chorbiRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public ChorbiService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Chorbi create() {
        Chorbi res;
        res = new Chorbi();
        return res;
    }
    public Chorbi findOne(int actorId) {
        Chorbi result;

        result = chorbiRepository.findOne(actorId);

        return result;
    }

    public Collection<Chorbi> findAll() {
        Collection<Chorbi> result;

        result = chorbiRepository.findAll();

        return result;
    }

    public Chorbi save(Chorbi actor) {
        Assert.notNull(actor);
        return chorbiRepository.save(actor);
    }

    public void delete(Chorbi actor) {
        Assert.notNull(actor);
        Assert.isTrue(chorbiRepository.exists(actor.getId()));
        chorbiRepository.delete(actor);
    }

    // Other business methods -----------------------


    public Chorbi findByPrincipal() {
        Chorbi result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    private Chorbi findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Chorbi result;

        result = chorbiRepository.findByUserAccountId(userAccount.getId());

        return result;
    }



    public Collection<Liked> getMyLikes(){

        Chorbi chorbi = findByPrincipal();
        Assert.notNull(chorbi);
        Collection<Liked> myLiked = findByPrincipal().getMyLikes();
        return myLiked;
    }

    public Collection<Liked> getLikes(){

        Chorbi chorbi = findByPrincipal();
        Assert.notNull(chorbi);
        Collection<Liked> likeds = findByPrincipal().getLikes();
        return likeds;
    }


    public Collection<Chirp> getMyChirps(){

        Chorbi chorbi = findByPrincipal();
        Assert.notNull(chorbi);
        Collection<Chirp> myChirps= findByPrincipal().getMyChirps();
        return myChirps;
    }

    public Collection<Chirp> getChirps(){

        Chorbi chorbi = findByPrincipal();
        Assert.notNull(chorbi);
        Collection<Chirp> Chirps= findByPrincipal().getChirps();
        return Chirps;
    }

    public void flush(){
        chorbiRepository.flush();
    }

    public Collection<Chorbi> chobiIsBanned(){
        List<Chorbi> retur = new ArrayList<>();
        Authority auth = new Authority();
        auth.setAuthority("BAN");
        for (Chorbi c : findAll()){
            if (!c.getUserAccount().getAuthorities().contains(auth)){
                retur.add(c);
            }
        }
        return retur;
    }
    private String checkTextForPhonesAndReplaceIt(String textToReplace){
        String res = "";
        String regexpPhone = "(\\+\\d{1,3}[ -.])?(\\(?\\d+\\)?[ -.]?)+";
        Pattern phone = Pattern.compile(regexpPhone);
        Matcher matcher = phone.matcher(textToReplace);
        if(matcher.find()){
            res = matcher.replaceAll("***");
         }else{
        return textToReplace;
    }
        return res;
    }
    private String checkTextForEmailsAndReplaceIt(String textToReplace){
        String res = "";
        String regexpMail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern mail = Pattern.compile(regexpMail);
        Matcher matcher1 = mail.matcher(textToReplace);
        if(matcher1.find()){
            res = matcher1.replaceAll("***");
        }else{
            return textToReplace;
        }
        return res;
    }

   public String megaTextChecker(String text){
        String res = checkTextForEmailsAndReplaceIt(text);
        String res2 = checkTextForPhonesAndReplaceIt(res);
        return res2;
   }


   public Collection<Chirp> myEventsChirp(){

       Collection<Chirp> chirps =  chorbiRepository.myEventsChirps(findByPrincipal());
       Collection<Chirp> res =  new ArrayList<>();
       res.addAll(chirps);
       return res;
   }
}
