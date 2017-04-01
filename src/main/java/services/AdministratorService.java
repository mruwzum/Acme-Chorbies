package services;

import domain.Administrator;
import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class AdministratorService{

    // Managed Repository ------------------------
    @Autowired
    private AdministratorRepository administratorRepository;

    // Supporting services -----------------------

    public AdministratorService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Administrator create() {
        Administrator res;
        res = new Administrator();
        return res;
    }
    public Administrator findOne(int actorId) {
        Administrator result;

        result = administratorRepository.findOne(actorId);

        return result;
    }

    public Collection<Administrator> findAll() {
        Collection<Administrator> result;

        result = administratorRepository.findAll();

        return result;
    }

    public Administrator save(Administrator actor) {
        Assert.notNull(actor);
        return administratorRepository.save(actor);
    }

    public void delete(Administrator actor) {
        Assert.notNull(actor);
        Assert.isTrue(administratorRepository.exists(actor.getId()));
        administratorRepository.delete(actor);
    }


    // Other business methods -----------------------


    public Administrator findByPrincipal() {
        Administrator result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    private Administrator findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Administrator result;

        result = administratorRepository.findByUserAccountId(userAccount.getId());

        return result;
    }


    public Boolean banChorbi(Chorbi chorbi){
        Boolean res = false;

        if(!chorbi.getUserAccount().getAuthorities().isEmpty()){
            Authority authority =  new Authority();
            authority.setAuthority("CHORBI");
            Authority authority2 =  new Authority();
            authority2.setAuthority("BAN");

            chorbi.getUserAccount().addAuthority(authority2);
            chorbi.getUserAccount().removeAuthority(authority);

            chorbi.setBanned(true);

            res = true;

        }
        return res;

    }


    public Boolean unbanChorbi(Chorbi chorbi){
        Boolean res = false;

            Authority authority =  new Authority();
            authority.setAuthority("CHORBI");
            Authority authority2 =  new Authority();
            authority2.setAuthority("BAN");
            chorbi.getUserAccount().addAuthority(authority);
            chorbi.getUserAccount().removeAuthority(authority2);


            chorbi.setBanned(false);

            res = true;

        return res;

    }


    //DASHBOARD


    public Collection<Chorbi> chorbiesPerCity(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.chorbiesPerCity();
    }
    public Collection<Chorbi> chorbiesPerCountry(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.chorbiesPerCountry();
    }

    public Double averageAgesOfTheChorbies(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.averageAgesOfTheChorbies();
    }
    public Integer maxAgeOfTheChorbies(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.maxAgeOfTheChorbies();
    }
    public Integer minAgeOfTheChorbies(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.minAgeOfTheChorbies();
    }

    public Collection<Chorbi> chorbiesSortedByTheNumberOfLikes(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.chorbiesSortedByTheNumberOfLikes();
    }


    public Double averageNumberOfLikesPerChorbi(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.averageNumberOfLikesPerChorbi();
    }
    public Integer maxNumberOfLikePerChorbi(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.maxNumberOfLikePerChorbi();
    }
    public Integer minNumberOfLikePerChorbi(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.minNumberOfLikePerChorbi();
    }

    public Double averageNumberOfChirpsReceived(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.averageNumberOfChirpsReceived();
    }
    public Integer maxNumberOfChirpsReceived(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.maxNumberOfChirpsReceived();
    }
    public Integer minNumberOfChirpsReceived(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.minNumberOfChirpsReceived();
    }

    public Double averageNumberOfChirpsSended(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.averageNumberOfChirpsSended();
    }
    public Integer maxNumberOfChirpsSended(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.maxNumberOfChirpsSended();
    }
    public Integer minNumberOfChirpsSended(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        return  administratorRepository.minNumberOfChirpsSended();
    }

    public Chorbi chorbieWhoHaveMoreChirpsReceived(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        List<Chorbi> chorbis =  new ArrayList<>( administratorRepository.chorbieWhoHaveMoreChirpsReceived());
        return chorbis.get(0);
    }

    public Chorbi chorbieWhoHaveMoreChirpsSended(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);
        List<Chorbi> chorbis =  new ArrayList<>( administratorRepository.chorbieWhoHaveMoreChirpsSended());
        return chorbis.get(0);
    }

    //RATIOS

    public Double ratioOfChorbiesWhoHaveInvalidOrUnregisteredCreditCard(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        Collection<Chorbi> chorbis = administratorRepository.allChorbi();
        Collection<Chorbi> chorbisInvalid = administratorRepository.chorbiWithInvalidCreditCard();
        Collection<Chorbi> chorbisWithout = administratorRepository.chorbiWithoutCreditCard();

        chorbisInvalid.addAll(chorbisWithout);

        Double res  = (double) chorbisInvalid.size()/chorbis.size();
        return res;
    }

    public Double ratioOfChorbiesWhoSearchActivites(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        Collection<Chorbi> chorbis = administratorRepository.allChorbi();
        Collection<Chorbi> chorbis1 = administratorRepository.chorbiWhoSearchActivities();

        Double res = (double) chorbis1.size()/chorbis.size();
        return res;
    }

    public Double ratioOfChorbiesWhoSearchFriendShip(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        Collection<Chorbi> chorbis = administratorRepository.allChorbi();
        Collection<Chorbi> chorbis1 = administratorRepository.chorbiWhoSearchFriendShip();

        Double res = (double) chorbis1.size()/chorbis.size();
        return res;
    }

    public Double ratioOfChorbiesWhoSearchLove(){
        Administrator administrator = findByPrincipal();
        Assert.notNull(administrator);

        Collection<Chorbi> chorbis = administratorRepository.allChorbi();
        Collection<Chorbi> chorbis1 = administratorRepository.chorbiWhoSearchLove();

        Double res = (double) chorbis1.size()/chorbis.size();
        return res;
    }






    public void flush(){
        administratorRepository.flush();
    }

}
