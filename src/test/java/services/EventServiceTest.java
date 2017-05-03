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

    //LISTING OF EVENTS ORGANIZED ON LAST MONTH WITH AVAILABLE SEATS POSITIVE & NEGATIVE

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
    //LISTING ALL THE SYSTEM EVENTS POSITIVE & NEGATIVE
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
    //LISTING ALL THE PASSED EVENTS POSITIVE & NEGATIVE

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

//MANAGING EVENTS CASES:
    // LISTING POSITIVE AND NEGATIVE
    //CREATING WITH POSITIVE & NEGATIVE CASES SUCH AS: VALID CREDIT CARD AND INVALID CREDITCARD
    // INCREASING THE RANGES OF VALID AND INVALID CARDS
    //CREATING THEM WITH DIFFERENT MANAGERS OR EVEN CHORBIES FOR NEGATIVE CASES
    //EDITING AND CREATING EVENTS WITH NO DATE OR NO ANNOUNCEMENTS

    @Test
    public void manageEventsOkList(){
        authenticate("manager1");
        Collection<Event> events = eventService.findAll();
        Collection<Event> res = new ArrayList<>();
        for (Event e : events){
            if (e.getOwner().equals(managerService.findByPrincipal())){
                res.add(e);
            }
        }
        Assert.assertNotNull(res);



        unauthenticate();
    }
    @Test
    public void manageEventsOkCreate(){
        authenticate("manager1");
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
        unauthenticate();
    }
    @Test
    public void manageEventsOkCreate1(){
        authenticate("manager2");
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
        unauthenticate();
    }
    @Test
    public void manageEventsOkCreate3(){
        authenticate("manager2");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        event.setPicture("http://pic.jpg");
        event.setTitle("title");
        event.setNumberOfSeats(23);
        event.setDescription("dsfsdf");
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*200L));
        eventService.save(event);
        CreditCard cred = new CreditCard();
        cred.setExpirationMonth(06);
        cred.setExpirationYear(2018);
        cred.setNumber("54275498043695577");
        cred.setHolder("manager2");
        cred.setCVV("234");
        cred.setBrand(Brand.AMEX);
        managerService.findByPrincipal().setCreditCard(cred);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        unauthenticate();
    }
    @Test
    public void manageEventsOkCreate4(){
        authenticate("manager3");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        event.setPicture("http://pic.jpg");
        event.setTitle("title");
        event.setNumberOfSeats(23);
        event.setDescription("dsfsdf");
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*200L));
        eventService.save(event);
        CreditCard cred = new CreditCard();
        cred.setExpirationMonth(06);
        cred.setExpirationYear(2018);
        cred.setNumber("54275498043695577");
        cred.setHolder("manager2");
        cred.setCVV("234");
        cred.setBrand(Brand.AMEX);
        managerService.findByPrincipal().setCreditCard(cred);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void manageEventsNotOkCreateChorb(){
        authenticate("chorbi1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        event.setPicture("http://pic.jpg");
        event.setTitle("title");
        event.setNumberOfSeats(23);
        event.setDescription("dsfsdf");
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*200L));
        eventService.save(event);
        CreditCard cred = new CreditCard();
        cred.setExpirationMonth(06);
        cred.setExpirationYear(2018);
        cred.setNumber("54275498043695577");
        cred.setHolder("manager2");
        cred.setCVV("234");
        cred.setBrand(Brand.AMEX);
        managerService.findByPrincipal().setCreditCard(cred);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void manageEventsNotOkCreateAdmin(){
        authenticate("administrator1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        event.setPicture("http://pic.jpg");
        event.setTitle("title");
        event.setNumberOfSeats(23);
        event.setDescription("dsfsdf");
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*200L));
        eventService.save(event);
        CreditCard cred = new CreditCard();
        cred.setExpirationMonth(06);
        cred.setExpirationYear(2018);
        cred.setNumber("54275498043695577");
        cred.setHolder("manager2");
        cred.setCVV("234");
        cred.setBrand(Brand.AMEX);
        managerService.findByPrincipal().setCreditCard(cred);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void manageEventsNotOkCreate(){

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
    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNotCreditCardOk(){
        authenticate("manager1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        eventService.save(event);
        managerService.findByPrincipal().getCreditCard().setExpirationYear(1991);
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
    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNotCreditCardOk2(){
        authenticate("manager1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        eventService.save(event);
        managerService.findByPrincipal().getCreditCard().setExpirationYear(2017);
        managerService.findByPrincipal().getCreditCard().setExpirationMonth(03);
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
    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNotCreditCardOk3(){
        authenticate("manager1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        eventService.save(event);
        CreditCard creditCard = new CreditCard();
        managerService.findByPrincipal().setCreditCard(creditCard);
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

    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNotCreditCardOk4(){
        authenticate("manager1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        eventService.save(event);
        managerService.findByPrincipal().setCreditCard(null);
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

    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNoDate(){
        authenticate("manager1");
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
        eventService.delete(event);
        unauthenticate();
    }

    @Test(expected = ConstraintViolationException.class)
    public void manageEventsNoAnnouncements(){
        authenticate("manager1");
        Event event = eventService.create();
        event.setOwner(managerService.findByPrincipal());
        eventService.save(event);
        searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard());
        Assert.assertNotNull(event);
        ChirpMultiple chirpMultiple = new ChirpMultiple();
        chirpMultiple.setSender(managerService.findByPrincipal());
        event.setDate(new Date(System.currentTimeMillis()+1000*60*60*290L));
        eventService.delete(event);
        unauthenticate();
    }
    // CREATING CHIRP BROADCAST POSITIVE & NEGATIVE
    @Test
    public void createEventChirp(){
        authenticate("manager1");
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

    @Test(expected = IllegalArgumentException.class)
    public void createEventChirpNotOk(){
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


   //REGISTER AND UNREGISTER TO AN EVENT POSITIVE & NEGATIVE CASES

    @Test()
    public void registerToAnEventOk(){
        authenticate("chorbi1");
        List<Event> events = new ArrayList<>(eventService.findAll());
        eventService.registerNewPartaker(chorbiService.findByPrincipal(),events.get(0));
        unauthenticate();
    }
    @Test()
    public void registerToAnEventOk1(){
        authenticate("chorbi2");
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
    //LISTING OF EVENTS IN WITH I'VE REGISTERED POSITIVE AND NEGATIVE CASES
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