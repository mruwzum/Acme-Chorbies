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
public class LikedServiceTest extends AbstractTest {

    @Autowired
    private ActorService actorService;
    @Autowired
    private ChorbiService chorbiService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private CoordinateService coordinateService;
    @Autowired
    private LikedService likedService;



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



        Chorbi chorbi2 = chorbiService.create();
        CreditCard cr2 = creditCardService.create();
        cr2.setBrand(Brand.AMEX);
        cr2.setCVV("345");
        cr2.setExpirationMonth(8);
        cr2.setExpirationYear(2020);
        cr2.setHolder("chorbi2");
        cr2.setNumber("54275498043695577");
        chorbi2.setCreditCard(cr);
        chorbi2.setAge(25);
        chorbi2.setName("perri");
        chorbi2.setSurname("el perro");
        chorbi2.setEmail("chorbi@gmail.com");
        chorbi2.setPicture("http://picture.png");
        Coordinate coord2 = coordinateService.create();
        coord2.setCity("Sevilla");
        chorbi2.setCoordinate(coord);
        chorbi2.setDescription("hola");
        chorbi2.setGenre(Genre.MAN);
        chorbi2.setRelationship(Relationship.ACTIVITIES);
        UserAccount userAccount2 = new UserAccount();
        Authority authority2 = new Authority();
        authority2.setAuthority("CHORBI");
        Collection<Authority> authorities2 = new HashSet<>();
        userAccount2.setAuthorities(authorities2);
        userAccount2.setUsername("generic2");
        userAccount2.setPassword("generic2");
        chorbi2.setUserAccount(userAccount2);
        actorService.registerAsAChorbi(chorbi2);
    }

    @After
    public void tearDown(){
    }


    //CHORBIE POSTING A LIKE POSITIVE & NEGATIVE
    @Test
    public void postLikePositive() {

        authenticate("chorbi1");
        Liked liked = likedService.create();
        liked.setSender(chorbiService.findByPrincipal());
        List<Chorbi> chorbies = new ArrayList<>(chorbiService.findAll());
        Chorbi receiver = chorbies.get(0);
        liked.setReceiver(receiver);
        liked.setText("like u");
        Boolean res = likedService.postLike(liked);
        Assert.assertTrue(res);
        unauthenticate();
        likedService.flush();
    }
    @Test(expected = IllegalArgumentException.class)
    public void postLikeNegative() {
        authenticate(null);
        Liked liked = likedService.create();
        liked.setSender(chorbiService.findByPrincipal());
        List<Chorbi> chorbies = new ArrayList<>(chorbiService.findAll());
        Chorbi receiver = chorbies.get(0);
        liked.setReceiver(receiver);
        liked.setText("like u");
        Boolean res = likedService.postLike(liked);
        Assert.assertTrue(res);
        unauthenticate();
        likedService.flush();
    }

}