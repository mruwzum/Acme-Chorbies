package services;

import domain.Actor;
import domain.Chirp;
import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.ChirpRepository;
import security.LoginService;
import security.UserAccount;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

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
    public Chirp create() {
        Chirp res;
        res = new Chirp();
        return res;
    }
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


    public Boolean postChirp(Chirp chirp){
        Boolean res = false;
        Assert.notNull(chirp.getReceiver(), "Receiver vacio");
        Assert.notNull(chirp.getSender(), "Receiver vacio");

        try {
            chirp.setMoment(new Date(System.currentTimeMillis()-1000));
            chirp.getReceiver().getChirps().add(chirp);
            chirp.getSender().getMyChirps().add(chirp);
            res = true;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;
    }


    public Boolean resend(Chirp chirp){
        Boolean res = false;

        try {
            Chirp resend =  create();
            resend.setReceiver(chirp.getReceiver());
            resend.setSender(chirp.getSender());
            resend.setMoment(chirp.getMoment());
            //resend.setAttachments(chirp.getAttachments());
            resend.setMessage(chirp.getMessage());
            resend.setSubject(chirp.getSubject());

            chirp.getReceiver().getChirps().add(resend);
            chirp.getSender().getMyChirps().add(resend);

            res = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public Chirp reply(Chirp chirp){

        Chirp chirpc = create();

        try {
            Chorbi sender = chirp.getReceiver();
            Chorbi receiver = chirp.getSender();

            chirpc.setSender(sender);
            chirpc.setReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return chirpc;
    }




    public void flush(){
        chirpRepository.flush();
    }

}
