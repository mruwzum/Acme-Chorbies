package services;

import com.sun.org.apache.regexp.internal.RE;
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
import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 2/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class ActorServiceTest extends AbstractTest {
    @Autowired
    private ActorService actorService;
    @Autowired
    private ChorbiService chorbiService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private CoordinateService coordinateService;

    @Before
    public void setUp()  {

    }

    @After
    public void tearDown() {
    }
//REGISTRATION POSITIVE&NEGATIVE TESTS
    @Test
    public void registerAsAChorbiOk(){
        Chorbi chorbi = chorbiService.create();
        CreditCard cr = creditCardService.create();
        cr.setBrand(Brand.AMEX);
        cr.setCVV("345");
        cr.setExpirationMonth(8);
        cr.setExpirationYear(2020);
        cr.setHolder("chorbi2");
        cr.setNumber("54275498043695577");
        chorbi.setCreditCard(cr);
        chorbi.setAge(25);
        chorbi.setName("perri");
        chorbi.setSurname("el perro");
        chorbi.setEmail("chorbi@gmail.com");
        chorbi.setPicture("http://picture.png");
        Coordinate coord = coordinateService.create();
        coord.setCity("Sevilla");
        chorbi.setCoordinate(coord);
        chorbi.setDescription("hola");
        chorbi.setGenre(Genre.MAN);
        chorbi.setRelationship(Relationship.ACTIVITIES);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("generic");
        userAccount.setPassword("generic");
        chorbi.setUserAccount(userAccount);
       Actor res=  actorService.registerAsAChorbi(chorbi);
       Assert.assertNotNull(res);
       actorService.flush();
    }
    @Test(expected = NullPointerException.class)
    public void registerAsAChorbiWithNoCity(){
        Chorbi chorbi = chorbiService.create();
        CreditCard cr = creditCardService.create();
        cr.setBrand(Brand.AMEX);
        cr.setCVV("345");
        cr.setExpirationMonth(8);
        cr.setExpirationYear(2012);
        cr.setHolder("chorbi2");
        cr.setNumber("54275498043695577");
        chorbi.setCreditCard(cr);
        chorbi.setAge(25);
        chorbi.setName("perri");
        chorbi.setSurname("el perro");
        chorbi.setEmail("chorbi@gmail.com");
        chorbi.setPicture("http://picture.png");
        chorbi.setDescription("hola");
        chorbi.setGenre(Genre.MAN);
        chorbi.setRelationship(Relationship.ACTIVITIES);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("generic");
        userAccount.setPassword("generic");
        chorbi.setUserAccount(userAccount);
        Actor res=  actorService.registerAsAChorbi(chorbi);
        Assert.assertNotNull(res);
        actorService.flush();
    }
    @Test(expected = NullPointerException.class)
    public void registerAsAChorbiWithoutPass(){
        Chorbi chorbi = chorbiService.create();
        CreditCard cr = creditCardService.create();
        cr.setBrand(Brand.AMEX);
        cr.setCVV("345");
        cr.setExpirationMonth(8);
        cr.setExpirationYear(2012);
        cr.setHolder("chorbi2");
        cr.setNumber("54275498043695577");
        chorbi.setCreditCard(cr);
        chorbi.setAge(25);
        chorbi.setName("perri");
        chorbi.setSurname("el perro");
        chorbi.setEmail("chorbi@gmail.com");
        chorbi.setPicture("http://picture.png");
        Coordinate coord = coordinateService.create();
        coord.setCity("Sevilla");
        chorbi.setCoordinate(coord);
        chorbi.setDescription("hola");
        chorbi.setGenre(Genre.MAN);
        chorbi.setRelationship(Relationship.ACTIVITIES);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("generic");
        Actor res=  actorService.registerAsAChorbi(chorbi);
        Assert.assertNotNull(res);
        actorService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void registerAsAChorbiUnderAge(){
        Chorbi chorbi = chorbiService.create();
        CreditCard cr = creditCardService.create();
        cr.setBrand(Brand.AMEX);
        cr.setCVV("345");
        cr.setExpirationMonth(8);
        cr.setExpirationYear(2020);
        cr.setHolder("chorbi2");
        cr.setNumber("54275498043695577");
        chorbi.setCreditCard(cr);
        chorbi.setAge(12);
        chorbi.setName("perri");
        chorbi.setSurname("el perro");
        chorbi.setEmail("chorbi@gmail.com");
        chorbi.setPicture("http://picture.png");
        Coordinate coord = coordinateService.create();
        coord.setCity("Sevilla");
        chorbi.setCoordinate(coord);
        chorbi.setDescription("hola");
        chorbi.setGenre(Genre.MAN);
        chorbi.setRelationship(Relationship.ACTIVITIES);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("generic");
        userAccount.setPassword("generic");
        chorbi.setUserAccount(userAccount);
        Actor res=  actorService.registerAsAChorbi(chorbi);
        Assert.assertNotNull(res);
        actorService.flush();

    }
    //FINDBYPRINCIPAL POSITIVE&NEGATIVE
    @Test
    public void findByPrincipalOk(){
        authenticate("chorbi1");
        Assert.assertNotNull(actorService.findByPrincipal());
        authenticate(null);
        actorService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void findByPrincipalNull(){
        authenticate(null);
        Assert.assertNotNull(actorService.findByPrincipal());
        authenticate(null);
        actorService.flush();
    }
}