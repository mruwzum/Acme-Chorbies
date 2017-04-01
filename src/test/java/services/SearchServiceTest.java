package services;

import domain.Chorbi;
import domain.CreditCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

import java.util.Date;
import java.util.List;

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
        authenticate("chorbi1");
        Chorbi chorbi = chorbiService.findByPrincipal();


        System.out.println(chorbi.getCreditCard());

        System.out.println(searchService.checkCreditCard(chorbi));



        authenticate(null);
    }

}