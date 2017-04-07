package services;

import domain.*;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by mruwzum on 1/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml"})
@Transactional
public class SearchServiceTest extends AbstractTest {

   @Autowired
   private ChorbiService chorbiService;
   @Autowired
   private SearchService searchService;
    @Autowired
    private CreditCardService creditCardService;

    @Before
    public void setUp() {
        authenticate("chorbi2");
        CreditCard creditCard = creditCardService.create();
        creditCard.setBrand(Brand.AMEX);
        creditCard.setCVV("345");
        creditCard.setExpirationMonth(8);
        creditCard.setExpirationYear(2012);
        creditCard.setHolder("chorbi2");
        creditCard.setNumber("54275498043695577");
        chorbiService.findByPrincipal().setCreditCard(creditCard);
        unauthenticate();
        authenticate("chorbi1");
        CreditCard creditCard1 = creditCardService.create();
        creditCard1.setBrand(Brand.AMEX);
        creditCard1.setCVV("345");
        creditCard1.setExpirationMonth(8);
        creditCard1.setExpirationYear(2020);
        creditCard1.setHolder("chorbi1");
        creditCard1.setNumber("54275498043695577");
        chorbiService.findByPrincipal().setCreditCard(creditCard1);
        unauthenticate();
        authenticate("chorbi1");
        Search search = searchService.create();
        search.setAge(23);
        search.setRelationship(Relationship.ACTIVITIES);
        search.setKeyword("Sevilla");
        search.setGenre(Genre.MAN);
        search.setCreationDate(new Date("2017/03/30 00:00:00"));
        chorbiService.findByPrincipal().getMySearches().add(search);
        Search search1 = searchService.create();
        search1.setAge(23);
        search1.setRelationship(Relationship.ACTIVITIES);
        search1.setKeyword("Sevilla");
        search1.setGenre(Genre.MAN);
        search1.setCreationDate(new Date("2017/03/30 00:00:00"));
        chorbiService.findByPrincipal().getMySearches().add(search1);
        Search search2 = searchService.create();
        search2.setAge(23);
        search2.setRelationship(Relationship.ACTIVITIES);
        search2.setKeyword("Sevilla");
        search2.setGenre(Genre.MAN);
        search2.setCreationDate(new Date("2017/03/30 00:00:00"));
        chorbiService.findByPrincipal().getMySearches().add(search2);
        unauthenticate();
    }

    @After
    public void tearDown() {
    }


    //CHECK CREDIT CARD WITH POSITIVE AND NEGATIVE TEST FOR A VALID, NOT VALID OR EMPTY CREDIT CARD
    @Test(expected = AssertionError.class)
    public void checkCreditCardNotValid() throws Exception {
        authenticate("chorbi2");
        Chorbi chorbi = chorbiService.findByPrincipal();
        Assert.assertTrue(searchService.checkCreditCard(chorbi.getCreditCard()));
        authenticate(null);
        searchService.flush();
    }
    @Test
    public void checkCreditCardValid() throws Exception {
        authenticate("chorbi1");
        Chorbi chorbi1 = chorbiService.findByPrincipal();
        searchService.checkCreditCard(chorbi1.getCreditCard());
        Assert.assertTrue(searchService.checkCreditCard(chorbi1.getCreditCard()));
        authenticate(null);
        searchService.flush();
    }
    @Test
    public void checkCreditCardEMpty() throws Exception {
        authenticate("mruwzum");
        Chorbi chorbi11 = chorbiService.findByPrincipal();
        searchService.checkCreditCard(chorbi11.getCreditCard());
        Assert.assertTrue(!searchService.checkCreditCard(chorbi11.getCreditCard()));
        authenticate(null);
        searchService.flush();
    }
    // CHECK TIME FOR SEARCHES AND DELETE THEM FROM SYSTEM
    @Test
    public void chechtime() {
        Collection<Search> searches = searchService.findAll();
        int sizeA = searches.size();
        searchService.checkTime(searches);
        int sizeB = searches.size();
        Assert.assertTrue(sizeA!=sizeB);
        searchService.flush();
    }
    @Test(expected = AssertionError.class)
    public void chechtimeEmpty() {
        Collection<Search> searches = searchService.findAll();
        searches.removeAll(searches);
        int sizeA = searches.size();
        searchService.checkTime(searches);
        int sizeB = searches.size();
        Assert.assertTrue(sizeA!=sizeB);
        searchService.flush();
    }

    //BAN TEXT POSITIVE&NEGATIVE TESTS
    @Test
    public void banTextWithNotValidText(){

        String text = "Hola, soy anto chicoantolin@gmail.com y soy un chico formal horbi 4 description call me now!! +23-32464536754  ";
        String res = chorbiService.megaTextChecker(text);
        Assert.assertTrue(res.contains("***"));
        searchService.flush();
    }
    @Test
    public void banTextWithValidText(){

        String text = "Hola, soy anto y soy un chico formal horbi descrip  ";
        String res = chorbiService.megaTextChecker(text);
        Assert.assertTrue(!res.contains("***"));
        searchService.flush();
    }

    //FINDER POSITIVE&NEGATIVE TESTS
    @Test
    public void finderPositive(){
        authenticate("chorbi1");
       List<Search> searches = new ArrayList<>(searchService.findAll());
       Search search = searches.get(0);
       Assert.assertTrue( searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard()));
       List<Chorbi> chorbies = searchService.finder(search.getAge(),search.getRelationship(),search.getGenre(),search.getCoordinate(),search.getKeyword());
       org.springframework.util.Assert.notEmpty(chorbies);
       unauthenticate();

    }
    @Test(expected = AssertionError.class)
    public void finderNegative(){
        authenticate("chorbi2");
        List<Search> searches = new ArrayList<>(searchService.findAll());
        Search search = searches.get(0);
        Assert.assertTrue( searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard()));
        List<Chorbi> chorbies = searchService.finder(search.getAge(),search.getRelationship(),search.getGenre(),search.getCoordinate(),search.getKeyword());
        org.springframework.util.Assert.notEmpty(chorbies);

        unauthenticate();

    }


    @Test
    public void davidFinder(){
        authenticate("chorbi1");

        Coordinate coordinate = new Coordinate();
        coordinate.setCity("Huelva");
//        coordinate.setCountry("Spain");
//        coordinate.setProvince("Huelva");
//        coordinate.setState("Andalucía");

        Search search = new Search();

        search.setAge(27);
        search.setCoordinate(coordinate);
        //search.setGenre(Genre.WOMAN);
        search.setRelationship(Relationship.FRIENDSHIP);
        search.setKeyword("5name");

        Search saved = searchService.save(search);

        System.out.println(searchService.davidFinder(saved));

        unauthenticate();
        searchService.flush();
    }

}