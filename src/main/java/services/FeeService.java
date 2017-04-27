package services;

import domain.Fee;
import domain.SearchCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.FeeRepository;
import repositories.SearchCacheRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class FeeService {

    // Managed Repository ------------------------
    @Autowired
    private FeeRepository feeRepository;


    // Supporting services -----------------------

    // Constructor -------------------------------
    public FeeService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Fee create() {
        Fee res;
        res = new Fee();
        return res;
    }

    public Fee findOne(int actorId) {
        Fee result;

        result = feeRepository.findOne(actorId);

        return result;
    }

    public Collection<Fee> findAll() {
        Collection<Fee> result;

        result = feeRepository.findAll();

        return result;
    }

    public Fee save(Fee actor) {
        Assert.notNull(actor);
        return feeRepository.save(actor);
    }

    public void delete(Fee actor) {
        Assert.notNull(actor);
        Assert.isTrue(feeRepository.exists(actor.getId()));
        feeRepository.delete(actor);
    }

    // Other business methods -----------------------

    public void flush(){
        feeRepository.flush();
    }


}
