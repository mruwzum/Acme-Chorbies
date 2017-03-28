package services;

import domain.Actor;
import domain.Chirp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.ChirpRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class ChirpService {

    // Managed Repository ------------------------
    @Autowired
    private ChirpRepository chirpRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public ChirpService() {
        super();
    }

    // Simple CRUD methods -----------------------

    public Chirp findOne(int actorId) {
        Chirp result;

        result = chirpRepository.findOne(actorId);

        return result;
    }

    public Collection<Chirp> findAll() {
        Collection<Chirp> result;

        result = chirpRepository.findAll();

        return result;
    }

    public Chirp save(Chirp actor) {
        Assert.notNull(actor);
        return chirpRepository.save(actor);
    }

    public void delete(Chirp actor) {
        Assert.notNull(actor);
        Assert.isTrue(chirpRepository.exists(actor.getId()));
        chirpRepository.delete(actor);
    }

    // Other business methods -----------------------



    public void flush(){
        chirpRepository.flush();
    }

}
