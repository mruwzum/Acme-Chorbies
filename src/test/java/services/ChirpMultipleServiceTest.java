package services;

import domain.ChirpMultiple;
import domain.Event;
import domain.Manager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import security.UserAccountService;
import utilities.AbstractTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 3/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional

public class ChirpMultipleServiceTest extends AbstractTest {
    @Autowired
    private ActorService actorService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ChorbiService chorbiService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private CoordinateService coordinateService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private FeeService feeService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ChirpMultipleService chirpMultipleService;

    @Before
    public void setUp() {

    }


//BROADCAST CHIRP POSITIVE AND NEGATIVE CASES

    @Test
    public void broadcastChirp() {
        authenticate("manager1");
        Manager manager = managerService.findByPrincipal();
       List<Event> events = new ArrayList<>(manager.getCreatedEvents());

       ChirpMultiple m = chirpMultipleService.create();
       m.setSender(manager);
       m.setReceivers(events.get(0).getPartakers());
       m.setSubject("su");
       m.setMessage("me");
       m.setMoment(new Date(System.currentTimeMillis()-1000));

       chirpMultipleService.broadcastChirp(m);
        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void broadcastChirpNotOk() {
        authenticate(null);
        Manager manager = managerService.findByPrincipal();
        List<Event> events = new ArrayList<>(manager.getCreatedEvents());

        ChirpMultiple m = chirpMultipleService.create();
        m.setSender(manager);
        m.setReceivers(events.get(0).getPartakers());
        m.setSubject("su");
        m.setMessage("me");
        m.setMoment(new Date(System.currentTimeMillis()-1000));

        chirpMultipleService.broadcastChirp(m);
        unauthenticate();
    }
}