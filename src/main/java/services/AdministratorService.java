package services;

import domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

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

    public void flush(){
        administratorRepository.flush();
    }

}
