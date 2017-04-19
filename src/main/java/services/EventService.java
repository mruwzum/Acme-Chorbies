package services;

import domain.CreditCard;
import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.CreditCardRepository;
import repositories.EventRespository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Service
@Transactional
public class EventService {


    // Managed Repository ------------------------
    @Autowired
    private EventRespository eventRespository;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public EventService() {
        super();
    }

    // Simple CRUD methods -----------------------
    public Event create() {
        Event res;
        res = new Event();
        return res;
    }
    public Event findOne(int actorId) {
        Event result;

        result = eventRespository.findOne(actorId);

        return result;
    }

    public Collection<Event> findAll() {
        Collection<Event> result;

        result = eventRespository.findAll();

        return result;
    }

    public Event save(Event actor) {
        Assert.notNull(actor);
        return eventRespository.save(actor);
    }

    public void delete(Event actor) {
        Assert.notNull(actor);
        Assert.isTrue(eventRespository.exists(actor.getId()));
        eventRespository.delete(actor);
    }

    // Other business methods -----------------------


    public void flush(){
        eventRespository.flush();
    }
}
