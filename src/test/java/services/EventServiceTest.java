package services;

import domain.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;

import javax.jws.soap.SOAPBinding;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * Created by mruwzum on 2/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class EventServiceTest extends AbstractTest {


    @Autowired
    private EventService eventService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ChorbiService chorbiService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private SearchService searchService;

    @Before
    public void setUp(){
        Event event = eventService.create();
        event.setDate(new Date(System.currentTimeMillis()+1000*60*12L));
        event.setDescription("erawer");
        event.setNumberOfSeats(344);
        List<Manager> manager = new ArrayList<>(managerService.findAll());
        event.setOwner(manager.get(0));
        event.setTitle("dsfdf");
        event.setPicture("http://pic.jpg");
        eventService.save(event);
    }

    @Test
    public void listOfEventOrganizedInTheLastMonthAndHaveSeatsOK(){
        authenticate(null);
        Collection<Event> events = eventService.okEvents();
        Assert.assertNotNull(events);
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void listOfEventOrganizedInTheLastMonthAndHaveSeatsNotOK(){
        authenticate(null);
        Collection<Event> event0 = eventService.okEvents();
        event0.removeAll(event0);
        org.springframework.util.Assert.isNull(event0);
        unauthenticate();
    }
    @Test
    public void listOfEventsOnTheSystemOK(){
        authenticate(null);
        Collection<Event> events = eventService.findAll();
        Assert.assertNotNull(events);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void listOfEventsOnTheSystemNotOK(){
        authenticate(null);
        Collection<Event> events = eventService.findAll();
        events.removeAll(events);
        org.springframework.util.Assert.isNull(events);
        unauthenticate();
    }

    @Test
    public void pastEventsOk(){
        authenticate(null);
        Collection<Event> events = eventService.pastEvents();
        Assert.assertNotNull(events);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void pastEventsNotOk(){
        authenticate(null);
        Collection<Event> events = eventService.pastEvents();
        events.removeAll(events);
        org.springframework.util.Assert.isNull(events);
        unauthenticate();
    }


    @Test
    public void manageEventsOk(){
        authenticate("manager1");
        Collection<Event> events = eventService.findAll();
        Collection<Event> res = new ArrayList<>();
        for (Event e : events){
            if (e.getOwner().equals(managerService.findByPrincipal())){
                res.add(e);
            }
        }
        Assert.assertNotNull(res);

        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        event.setPicture("http://pic.jpg");
        event.setTitle("title");
        event.setNumberOfSeats(23);
        event.setDescription("dsfsdf");
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*200L));
        eventService.save(event);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        Collection<ChirpMultiple> chirpMultiples = new HashSet<>();
        ChirpMultiple chirpMultiple = new ChirpMultiple();
        chirpMultiple.setSender(managerService.findByPrincipal());
        chirpMultiple.setMessage("dfsdf");
        chirpMultiple.setMoment(new Date(System.currentTimeMillis()+1000*60*60*12L));
        chirpMultiple.setSubject("perri");
        chirpMultiples.add(chirpMultiple);
        event.setAnnouncements(chirpMultiples);
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*290L));

        unauthenticate();
    }
    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNotOk(){
        authenticate("manager1");
        Collection<Event> events = eventService.findAll();
        Collection<Event> res = new ArrayList<>();
        for (Event e : events){
            if (e.getOwner().equals(managerService.findByPrincipal())){
                res.add(e);
            }
        }
        Assert.assertNotNull(res);

        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        eventService.save(event);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        Collection<ChirpMultiple> chirpMultiples = new HashSet<>();
        ChirpMultiple chirpMultiple = new ChirpMultiple();
        chirpMultiple.setSender(managerService.findByPrincipal());
        chirpMultiple.setMessage("dfsdf");
        chirpMultiple.setMoment(new Date(System.currentTimeMillis()+1000*60*60*12L));
        chirpMultiple.setSubject("perri");
        chirpMultiples.add(chirpMultiple);
        event.setAnnouncements(chirpMultiples);
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*290L));

        eventService.delete(event);


        unauthenticate();
    }
    @Test()
    public void registerToAnEventOk(){
        authenticate("chorbi1");
        List<Event> events = new ArrayList<>(eventService.findAll());
        eventService.registerNewPartaker(chorbiService.findByPrincipal(),events.get(0));
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerToAnEventNotOk(){
        authenticate(null);
        List<Event> events = new ArrayList<>(eventService.findAll());
        eventService.registerNewPartaker(chorbiService.findByPrincipal(),events.get(0));
        unauthenticate();
    }
    @Test()
    public void unregisterToAnEventOk(){
        authenticate("chorbi1");
        List<Event> events = new ArrayList<>(eventService.findAll());
        eventService.unRegisterPartaker(chorbiService.findByPrincipal(),events.get(0));
        unauthenticate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void unregisterToAnEventNotOk(){
        authenticate(null);
        List<Event> events = new ArrayList<>(eventService.findAll());
        eventService.unRegisterPartaker(chorbiService.findByPrincipal(),events.get(0));
        unauthenticate();
    }
    @Test()
    public void listOfMyregisteredEventsOk(){
        authenticate("chorbi1");
        List<Event> events = new ArrayList<>(chorbiService.findByPrincipal().getEventsToGo());
        Assert.assertNotNull(events);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void listOfMyregisteredEventsNotOk(){
        authenticate(null);
        List<Event> events = new ArrayList<>(chorbiService.findByPrincipal().getEventsToGo());
        Assert.assertNotNull(events);
        unauthenticate();
    }
}