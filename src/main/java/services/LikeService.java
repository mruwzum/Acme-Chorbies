package services;

import domain.Liked;
import domain.Liked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.LikeRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 28/3/17.
 */

@Service
@Transactional
public class LikeService {

    // Managed Repository ------------------------
    @Autowired
    private LikeRepository likeRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public LikeService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Liked create() {
        Liked res;
        res = new Liked();
        return res;
    }
    public Liked findOne(int actorId) {
        Liked result;

        result = likeRepository.findOne(actorId);

        return result;
    }

    public Collection<Liked> findAll() {
        Collection<Liked> result;

        result = likeRepository.findAll();

        return result;
    }

    public Liked save(Liked actor) {
        Assert.notNull(actor);
        return likeRepository.save(actor);
    }

    public void delete(Liked actor) {
        Assert.notNull(actor);
        Assert.isTrue(likeRepository.exists(actor.getId()));
        likeRepository.delete(actor);
    }

    // Other business methods -----------------------



    public void flush(){
        likeRepository.flush();
    }

}
