package services;

import domain.CreditCard;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.CreditCardRepository;
import repositories.ManagerRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Service
@Transactional
public class ManagerService {

    // Managed Repository ------------------------
    @Autowired
    private ManagerRepository managerRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public ManagerService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Manager create() {
        Manager res;
        res = new Manager();
        return res;
    }
    public Manager findOne(int actorId) {
        Manager result;

        result = managerRepository.findOne(actorId);

        return result;
    }

    public Collection<Manager> findAll() {
        Collection<Manager> result;

        result = managerRepository.findAll();

        return result;
    }

    public Manager save(Manager actor) {
        Assert.notNull(actor);
        return managerRepository.save(actor);
    }

    public void delete(Manager actor) {
        Assert.notNull(actor);
        Assert.isTrue(managerRepository.exists(actor.getId()));
        managerRepository.delete(actor);
    }

    // Other business methods -----------------------


    public void flush(){
        managerRepository.flush();
    }
}
