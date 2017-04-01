package services;

import domain.Chorbi;
import domain.CreditCard;
import domain.Search;
import org.joda.time.DateTime;
import org.junit.After;
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


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkCreditCard() throws Exception {
        authenticate("chorbi2");
        Chorbi chorbi = chorbiService.findByPrincipal();


        System.out.println(chorbi.getCreditCard());

        Integer yearAct0 = (((new Date(System.currentTimeMillis())).getYear()));
        String year ="20"+ yearAct0.toString().substring(1);
        Integer finy = new Integer(year);

        System.out.println(finy);

        System.out.println(searchService.checkCreditCard(chorbi.getCreditCard()));


        authenticate(null);
        authenticate("chorbi1");
        Chorbi chorbi1 = chorbiService.findByPrincipal();


        System.out.println(chorbi1.getCreditCard());

        Integer yearAct01 = (((new Date(System.currentTimeMillis())).getYear()));
        String year1 ="20"+ yearAct01.toString().substring(1);
        Integer finy1 = new Integer(year1);

        System.out.println(finy1);

        System.out.println(searchService.checkCreditCard(chorbi1.getCreditCard()));


        authenticate(null);
        authenticate("mruwzum");
        Chorbi chorbi11 = chorbiService.findByPrincipal();


        System.out.println(chorbi11.getCreditCard());

        Integer yearAct011 = (((new Date(System.currentTimeMillis())).getYear()));
        String year11 ="20"+ yearAct011.toString().substring(1);
        Integer finy11 = new Integer(year11);

        System.out.println(finy11);

        System.out.println(searchService.checkCreditCard(chorbi11.getCreditCard()));


        authenticate(null);
    }

    @Test
    public void chechtime() {
        Date actual = new Date(System.currentTimeMillis());
        Integer hour = 12;
        Long VALUEZ = hour * 60 * 60 * 1000L;
        List<Search> searches = new ArrayList<>(searchService.findAll());
        System.out.println(searches);
        Collection<Search> searchesAux = new HashSet<>();
        for (Search s : searches) {
            if (Math.abs(s.getCreationDate().getTime() - actual.getTime()) > VALUEZ) {
                searchesAux.add(s);
            }
        }
        System.out.println(searchesAux);
    }


    @Test
    public void banText(){
String text = "Hola, soy anto y soy un chico formal horbi 4 description call me now!! +23-32464536754  ";
        String res = chorbiService.megaTextChecker(text);
        System.out.println(res);
    }
}