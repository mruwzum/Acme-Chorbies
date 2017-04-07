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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 2/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class AdministratorServiceTest extends AbstractTest {
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

    @Before
    public void setUp(){
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
        Authority authority = new Authority();
        authority.setAuthority("CHORBI");
        Collection<Authority> authorities = new HashSet<>();
        userAccount.setAuthorities(authorities);
        userAccount.setUsername("generic");
        userAccount.setPassword("generic");
        chorbi.setUserAccount(userAccount);
        actorService.registerAsAChorbi(chorbi);
    }

    @After
    public void tearDown(){
    }

    //FINDBYPRINCIPAL POSITIVE&NEGATIVE
    @Test
    public void findByPrincipalOk(){
        authenticate("administrator1");
        Assert.assertNotNull(actorService.findByPrincipal());
        authenticate(null);
        administratorService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void findByPrincipalNull(){
        authenticate(null);
        Assert.assertNotNull(actorService.findByPrincipal());
        authenticate(null);
        administratorService.flush();
    }

//BAN & UNBAN POSITIVE&NEGATIVE TESTS
    @Test
    public void banChorbi(){
        authenticate("administrator1");
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        Chorbi chorbi = chorbis.get(0);
        Assert.assertTrue(administratorService.banChorbi(chorbi));
        Assert.assertTrue(chorbi.getBanned().equals(true));
        unauthenticate();
        administratorService.flush();
    }

    @Test
    public void unbanChorbi(){
        authenticate("administrator1");
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        Chorbi chorbi = chorbis.get(0);
        Assert.assertTrue(administratorService.banChorbi(chorbi));
        Assert.assertTrue(chorbi.getBanned().equals(true));
         administratorService.unbanChorbi(chorbi);
        Assert.assertTrue(chorbi.getBanned().equals(false));
        unauthenticate();
        administratorService.flush();
    }


    @Test(expected = IllegalArgumentException.class)
    public void banChorbiNegative(){
        authenticate("administrator1");
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        Chorbi chorbi = chorbis.get(0);
        administratorService.banChorbi(chorbi);
        Assert.assertTrue(chorbi.getBanned().equals(true));
        Assert.assertTrue(administratorService.banChorbi(chorbi));
        unauthenticate();
        administratorService.flush();
    }

    @Test(expected = IllegalArgumentException.class)
    public void unbanChorbiNegative(){
        authenticate("administrator1");
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        Chorbi chorbi = chorbis.get(0);
        administratorService.unbanChorbi(chorbi);
        Assert.assertTrue(chorbi.getBanned().equals(false));
        unauthenticate();
        administratorService.flush();
    }




    @Test
    public void dashboardPostiveTest(){
        authenticate("administrator1");

        Assert.assertTrue(administratorService.chorbiesPerCity()!=null);
        Assert.assertTrue(administratorService.chorbiesPerCountry()!=null);
        Assert.assertTrue(administratorService.averageAgesOfTheChorbies()!=null);
        Assert.assertTrue(administratorService.maxAgeOfTheChorbies()!=null);
        Assert.assertTrue(administratorService.minAgeOfTheChorbies()!=null);
        Assert.assertTrue(administratorService.chorbiesSortedByTheNumberOfLikes()!=null);
        Assert.assertTrue(administratorService.averageNumberOfLikesPerChorbi()!=null);
        Assert.assertTrue(administratorService.maxNumberOfLikePerChorbi()!=null);
        Assert.assertTrue(administratorService.minNumberOfLikePerChorbi()!=null);
        Assert.assertTrue(administratorService.averageNumberOfChirpsReceived()!=null);
        Assert.assertTrue(administratorService.maxNumberOfChirpsReceived()!=null);
        Assert.assertTrue(administratorService.minNumberOfChirpsReceived()!=null);
        Assert.assertTrue(administratorService.averageNumberOfChirpsSended()!=null);
        Assert.assertTrue(administratorService.maxNumberOfChirpsSended()!=null);
        Assert.assertTrue(administratorService.chorbieWhoHaveMoreChirpsReceived()!=null);
        Assert.assertTrue(administratorService.chorbieWhoHaveMoreChirpsSended()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoHaveInvalidOrUnregisteredCreditCard()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoSearchActivites()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoSearchFriendShip()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoSearchLove()!=null);


        unauthenticate();
        administratorService.flush();

    }

    @Test(expected = IllegalArgumentException.class)
    public void dashboardNegativeTest(){
        authenticate(null);


        Assert.assertTrue(administratorService.chorbiesPerCity()!=null);
        Assert.assertTrue(administratorService.chorbiesPerCountry()!=null);
        Assert.assertTrue(administratorService.averageAgesOfTheChorbies()!=null);
        Assert.assertTrue(administratorService.maxAgeOfTheChorbies()!=null);
        Assert.assertTrue(administratorService.minAgeOfTheChorbies()!=null);
        Assert.assertTrue(administratorService.chorbiesSortedByTheNumberOfLikes()!=null);
        Assert.assertTrue(administratorService.averageNumberOfLikesPerChorbi()!=null);
        Assert.assertTrue(administratorService.maxNumberOfLikePerChorbi()!=null);
        Assert.assertTrue(administratorService.minNumberOfLikePerChorbi()!=null);
        Assert.assertTrue(administratorService.averageNumberOfChirpsReceived()!=null);
        Assert.assertTrue(administratorService.maxNumberOfChirpsReceived()!=null);
        Assert.assertTrue(administratorService.minNumberOfChirpsReceived()!=null);
        Assert.assertTrue(administratorService.averageNumberOfChirpsSended()!=null);
        Assert.assertTrue(administratorService.maxNumberOfChirpsSended()!=null);
        Assert.assertTrue(administratorService.chorbieWhoHaveMoreChirpsReceived()!=null);
        Assert.assertTrue(administratorService.chorbieWhoHaveMoreChirpsSended()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoHaveInvalidOrUnregisteredCreditCard()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoSearchActivites()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoSearchFriendShip()!=null);
        Assert.assertTrue(administratorService.ratioOfChorbiesWhoSearchLove()!=null);

        administratorService.flush();

    }

}