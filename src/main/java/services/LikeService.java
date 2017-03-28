package services;

import domain.Like;
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

    public Like findOne(int actorId) {
        Like result;

        result = likeRepository.findOne(actorId);

        return result;
    }

    public Collection<Like> findAll() {
        Collection<Like> result;

        result = likeRepository.findAll();

        return result;
    }

    public Like save(Like actor) {
        Assert.notNull(actor);
        return likeRepository.save(actor);
    }

    public void delete(Like actor) {
        Assert.notNull(actor);
        Assert.isTrue(likeRepository.exists(actor.getId()));
        likeRepository.delete(actor);
    }

    // Other business methods -----------------------



    public void flush(){
        likeRepository.flush();
    }

}
