package services;

import domain.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.CoordinateRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class CoordinateService {

    // Managed Repository ------------------------
    @Autowired
    private CoordinateRepository coordinateRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public CoordinateService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Coordinate create() {
        Coordinate res;
        res = new Coordinate();
        return res;
    }
    public Coordinate findOne(int actorId) {
        Coordinate result;

        result = coordinateRepository.findOne(actorId);

        return result;
    }

    public Collection<Coordinate> findAll() {
        Collection<Coordinate> result;

        result = coordinateRepository.findAll();

        return result;
    }

    public Coordinate save(Coordinate actor) {
        Assert.notNull(actor);
        return coordinateRepository.save(actor);
    }

    public void delete(Coordinate actor) {
        Assert.notNull(actor);
        Assert.isTrue(coordinateRepository.exists(actor.getId()));
        coordinateRepository.delete(actor);
    }

    // Other business methods -----------------------


    public void flush(){
        coordinateRepository.flush();
    }

}
