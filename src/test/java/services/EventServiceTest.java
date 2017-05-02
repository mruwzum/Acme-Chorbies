package services;

import domain.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

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

    @Before
    public void setUp(){
        Event event = eventService.create();
        event.setDate(new Date(System.currentTimeMillis()-1000*60*12L));
        event.setDescription("erawer");
        event.setNumberOfSeats(344);
        Manager manager = managerService.create();
        manager.setCompany("dfs");
        manager.setName("dafsdf");
        manager.setPhone("+43-436535634563");
        CreditCard creditCard = new CreditCard();
        creditCard.setBrand(Brand.AMEX);
        creditCard.setCVV("345");
        creditCard.setExpirationMonth(12);
        creditCard.setExpirationYear(2020);
        creditCard.setHolder("csdse");
        creditCard.setNumber("54275498043695577");
        manager.setCreditCard(creditCard);
        manager.setEmail("perri@gmail.com");
        manager.setVatNumber("334453466T");
        manager.setSurname("dfafsd");
        UserAccount userAccount = new UserAccount();
        Authority authority = new Authority();
        authority.setAuthority("MANAGER");
        Collection<Authority> authorities = new HashSet<>();
        userAccount.setAuthorities(authorities);
        userAccount.setUsername("generic");
        userAccount.setPassword("generic");
        userAccountService.save(userAccount);
        manager.setFee(2.0);
        manager.setTotalFee(200.0);
        event.setOwner(manager);
        managerService.save(manager);
        event.setTitle("dsfdf");
        event.setPicture("http://pic.jpg");
        eventService.save(event);
        managerService.save(manager);


    }












    @Test
    public void listOfEventOrganizedInTheLastMonthAndHaveSeats(){
        authenticate(null);
        Collection<Event> events = eventService.okEvents();
        Assert.assertNotNull(events);


        unauthenticate();

    }

}