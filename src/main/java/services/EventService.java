package services;

import domain.Chorbi;
import domain.CreditCard;
import domain.Event;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.CreditCardRepository;
import repositories.EventRespository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

    //Return the events that are going to be organised in less than one month and have seats available
    public Collection<Event> okEvents(){
        Long mes = 31*24*60*60*1000L;
        Date now = new Date(System.currentTimeMillis()-100);
        Date nextMonth = new Date(System.currentTimeMillis()+mes);


        Collection<Event> EventinLastMonth= new ArrayList<>(eventRespository.getEventBetwenNowAndLastMonth(now,nextMonth));
        Collection<Event> EventWithSeatsAvailable= new ArrayList<>(eventRespository.getEventBetwenWithSeatsAvailable());

        EventinLastMonth.retainAll(EventWithSeatsAvailable);

        return EventinLastMonth;
    }
    //Return the list of past events
    public Collection<Event> pastEvents(){

        Date now = new Date(System.currentTimeMillis()-100);

        Collection<Event> res =  eventRespository.lastEventInSystem(now);

        return res;


    }




    public boolean registerNewPartaker(Chorbi chorbi, Event event){

        boolean res = true;



        if(event.getPartakers().contains(chorbi) || event.getPartakers().size() == event.getNumberOfSeats() ){
            res = false;
        }
        chorbi.getEventsToGo().add(event);
        event.getPartakers().add(chorbi);

        return res;

    }

    public boolean unRegisterPartaker(Chorbi chorbi, Event event){

        boolean res = true;

        if(!event.getPartakers().contains(chorbi)){
            res = false;
        }
        event.getPartakers().remove(chorbi);
        chorbi.getEventsToGo().remove(event);

        return res;


    }

    public void flush(){
        eventRespository.flush();
    }
}
