package services;

import domain.Actor;
import domain.Chirp;
import domain.ChirpMultiple;
import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ChirpMultipleRepository;
import repositories.ChirpRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

/**
 * Created by daviddelatorre on 28/3/17.
 */
@Service
@Transactional
public class ChirpMultipleService {

    // Managed Repository ------------------------
    @Autowired
    private ChirpMultipleRepository chirpMultipleRepository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public ChirpMultipleService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public ChirpMultiple create() {
        ChirpMultiple res;
        res = new ChirpMultiple();
        return res;
    }
    public ChirpMultiple findOne(int actorId) {
        ChirpMultiple result;

        result = chirpMultipleRepository.findOne(actorId);

        return result;
    }

    public Collection<ChirpMultiple> findAll() {
        Collection<ChirpMultiple> result;

        result = chirpMultipleRepository.findAll();

        return result;
    }

    public ChirpMultiple save(ChirpMultiple actor) {
        Assert.notNull(actor);
        return chirpMultipleRepository.save(actor);
    }

    public void delete(ChirpMultiple actor) {
        Assert.notNull(actor);
        Assert.isTrue(chirpMultipleRepository.exists(actor.getId()));
        chirpMultipleRepository.delete(actor);
    }

    // Other business methods -----------------------


    public Boolean broadcastChirp(ChirpMultiple chirp){
        Boolean res = false;
        Assert.notNull(chirp.getReceivers(), "Receivers vacio");
        Assert.notNull(chirp.getSender(), "Receiver vacio");

        try {
            chirp.setMoment(new Date(System.currentTimeMillis()-1000));


            for (Chorbi c : chirp.getReceivers()){
                Chirp b = new Chirp();
                b.setAttachments(chirp.getAttachments());
                b.setMessage(chirp.getMessage());
                b.setMoment(chirp.getMoment());
                b.setSubject(chirp.getSubject());
                b.setReceiver(c);
                c.getChirps().add(b);
            }

            res = true;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;
    }







    public void flush(){
        chirpMultipleRepository.flush();
    }

}
