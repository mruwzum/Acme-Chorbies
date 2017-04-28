package services;

import domain.Liked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.LikedRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 28/3/17.
 */

@Service
@Transactional
public class LikedService {

    // Managed Repository ------------------------
    @Autowired
    private LikedRepository likedRepository;


    // Supporting services -----------------------

    // Constructor -------------------------------
    public LikedService() {
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

        result = likedRepository.findOne(actorId);

        return result;
    }

    public Collection<Liked> findAll() {
        Collection<Liked> result;

        result = likedRepository.findAll();

        return result;
    }

    public Liked save(Liked actor) {
        Assert.notNull(actor);
        return likedRepository.save(actor);
    }

    public void delete(Liked actor) {
        Assert.notNull(actor);
        Assert.isTrue(likedRepository.exists(actor.getId()));
        likedRepository.delete(actor);
    }

    // Other business methods -----------------------



    public Boolean postLike(Liked liked){
        Boolean res = false;

        try {
            liked.setMoment(new Date(System.currentTimeMillis()-1000));
            liked.getReceiver().setNumberOfStars(liked.getReceiver().getNumberOfStars()+liked.getNumberOfStarts());
            liked.getReceiver().getLikes().add(liked);
            liked.getSender().getMyLikes().add(liked);
            res = true;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;
    }


    public void flush(){
        likedRepository.flush();
    }

}
