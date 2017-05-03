package services;

import domain.Chirp;
import domain.Chorbi;
import domain.CreditCard;
import domain.Liked;
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
public class ChorbiServiceTest extends AbstractTest {

    @Autowired
    private ChorbiService chorbiService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private LikedService likedService;
    @Before
    public void setUp(){
        authenticate("chorbi1");
        Liked liked = likedService.create();
        liked.setText("fhdf");
        liked.setSender(chorbiService.findByPrincipal());

        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        liked.setReceiver(chorbis.get(0));
        likedService.postLike(liked);
        unauthenticate();
        authenticate("chorbi2");
        Liked liked2 = likedService.create();
        liked2.setText("fhdf");
        liked2.setSender(chorbiService.findByPrincipal());


        liked2.setReceiver(chorbis.get(0));
        likedService.postLike(liked2);
        unauthenticate();

        authenticate("chorbi4");

        Collection<Liked> my = chorbiService.getMyLikes();
        my.removeAll(my);

        Collection<Liked> my2 = chorbiService.getLikes();
        my2.removeAll(my2);

        Collection<Chirp> my3 = chorbiService.getMyChirps();
        my3.removeAll(my3);
        Collection<Chirp> my4 = chorbiService.getChirps();
        my4.removeAll(my4);
        unauthenticate();
    }

    @After
    public void tearDown() {
    }

    //FINDBYPRINCIPAL POSITIVE&NEGATIVE
    @Test
    public void findByPrincipalOk(){
        authenticate("chorbi1");
        Assert.assertNotNull(chorbiService.findByPrincipal());
        authenticate(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void findByPrincipalNull(){
        authenticate(null);
        Assert.assertNotNull(chorbiService.findByPrincipal());
        authenticate(null);
    }
    //GET MY LIKES POSITIVE&NEGATIVE FOR VALID LIKES AND EMPTY ONES
    @Test
    public void getMyLikes(){
        authenticate("chorbi1");

        Collection<Liked> my = chorbiService.getMyLikes();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void getMyLikesEmpty(){
        authenticate("chorbi4");

        Collection<Liked> my = chorbiService.getMyLikes();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
    //GET LIKES POSITIVE&NEGATIVE FOR VALID LIKES AND EMPTY ONES
    @Test
    public void getLikes()   {
        authenticate("chorbi1");

        Collection<Liked> my = chorbiService.getLikes();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void getLikesEmpty(){
        authenticate("chorbi4");

        Collection<Liked> my = chorbiService.getLikes();
        org.springframework.util.Assert.notEmpty(my);
        unauthenticate();
    }

    //GET MY CHIRPS POSITIVE&NEGATIVE FOR VALID LIKES AND EMPTY ONES
    @Test
    public void getMyChirps()   {
        authenticate("chorbi1");

        Collection<Chirp> my = chorbiService.getMyChirps();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void getMyChirpsEmpty(){
        authenticate("chorbi4");

        Collection<Chirp> my = chorbiService.getMyChirps();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
    //GET CHIRPS POSITIVE&NEGATIVE FOR VALID LIKES AND EMPTY ONES
    @Test
    public void getChirps()   {
        authenticate("chorbi1");

        Collection<Chirp> my = chorbiService.getChirps();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void getChirpsEmpty(){
        authenticate("chorbi4");

        Collection<Chirp> my = chorbiService.getChirps();
        org.springframework.util.Assert.notEmpty(my);

        unauthenticate();
    }
//TEST FOR BANNED CHOBBIES ON THE SYSTEM POSITIVE&NEGATIVE
    @Test
    public void chobiIsBannedOk(){
     Collection<Chorbi> allChor = chorbiService.findAll();
        int contTodos = 0;
        int contBaneados = 0;
        Authority auth = new Authority();
        auth.setAuthority("BAN");
        for (Chorbi chorbi : allChor){
            if (chorbi.getUserAccount().getAuthorities().contains(auth)){
                contTodos++;
            }
        }
     List<Chorbi> aux = new ArrayList<>(chorbiService.findAll());
     Chorbi c = aux.get(0);
     Collection<Authority> authorities = new HashSet<>();
     authorities.add(auth);
     c.getUserAccount().setAuthorities(authorities);
     chorbiService.chobiIsBanned();

     for (Chorbi chorbi : chorbiService.findAll()){
         if (chorbi.getUserAccount().getAuthorities().contains(auth)){
             contBaneados++;
         }
     }
        Assert.assertTrue(contTodos!=contBaneados);
    }

    @Test
    public void chobiIsBannedNotOk(){
        Collection<Chorbi> allChor = chorbiService.findAll();
        int contTodos = 0;
        int contBaneados = 0;
        Authority auth = new Authority();
        auth.setAuthority("BAN");
        for (Chorbi chorbi : allChor){
            if (chorbi.getUserAccount().getAuthorities().contains(auth)){
                contTodos++;
            }
        }
        List<Chorbi> aux = new ArrayList<>(chorbiService.findAll());
        Chorbi c = aux.get(0);
        chorbiService.chobiIsBanned();

        for (Chorbi chorbi : chorbiService.findAll()){
            if (chorbi.getUserAccount().getAuthorities().contains(auth)){
                contBaneados++;
            }
        }
        Assert.assertTrue(contTodos==contBaneados);
    }

    //MEGATEXTCHECKET POSITIVE&NEGATIVE
    @Test
    public void megaTextCheckerValid() {
        String text = "Hola, soy anto y soy un chico formal horbi descrip  ";
        String res = chorbiService.megaTextChecker(text);
        Assert.assertTrue(!res.contains("***"));
        searchService.flush();
    }
    @Test
    public void megaTextCheckerNotValidText(){

        String text = "Hola, soy anto chicoantolin@gmail.com y soy un chico formal horbi 4 description call me now!! +23-32464536754  ";
        String res = chorbiService.megaTextChecker(text);
        Assert.assertTrue(res.contains("***"));
        searchService.flush();
    }


    // LIST OF CHORBIES THAT HAVE SENT A LIKE TO ME, POSITIVE & NEGATIVE CASES
    @Test
    public void iveLikedHimorHerOk()   {
        authenticate("chorbi1");
        Collection<Liked> likeds = chorbiService.getLikes();
        searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard());
        List<Chorbi> chorbis = new ArrayList<>();
        for (Liked l : likeds){
            chorbis.add(l.getSender());
        }
        Assert.assertNotNull(chorbis);

        unauthenticate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void iveLikedHimorHerNotOk(){
        authenticate("chorbi3");
        CreditCard creditCard = chorbiService.findByPrincipal().getCreditCard();
        creditCard.setExpirationYear(1991);
        Collection<Liked> likeds = chorbiService.getLikes();
        org.springframework.util.Assert.isTrue(searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard()));
        List<Chorbi> chorbis = new ArrayList<>();
        for (Liked l : likeds){
            chorbis.add(l.getSender());
        }
        Assert.assertNotNull(chorbis);

        unauthenticate();
    }

}