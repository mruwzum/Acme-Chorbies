package services;

import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.CreditCardRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class CreditCardService {

    // Managed Repository ------------------------
    @Autowired
    private CreditCardRepository creditCardRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public CreditCardService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public CreditCard create() {
        CreditCard res;
        res = new CreditCard();
        return res;
    }
    public CreditCard findOne(int actorId) {
        CreditCard result;

        result = creditCardRepository.findOne(actorId);

        return result;
    }

    public Collection<CreditCard> findAll() {
        Collection<CreditCard> result;

        result = creditCardRepository.findAll();

        return result;
    }

    public CreditCard save(CreditCard actor) {
        Assert.notNull(actor);
        return creditCardRepository.save(actor);
    }

    public void delete(CreditCard actor) {
        Assert.notNull(actor);
        Assert.isTrue(creditCardRepository.exists(actor.getId()));
        creditCardRepository.delete(actor);
    }

    // Other business methods -----------------------


    public void flush(){
        creditCardRepository.flush();
    }

}
