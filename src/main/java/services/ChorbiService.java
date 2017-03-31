package services;

import domain.Chorbi;
import domain.Liked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.ChorbiRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

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



    public void flush(){
        chorbiRepository.flush();
    }

}
