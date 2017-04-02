package services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import utilities.AbstractTest;

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
    @Before
    public void setUp(){
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findByPrincipal() throws Exception {
    }

    @Test
    public void getMyLikes() throws Exception {
    }

    @Test
    public void getLikes() throws Exception {
    }

    @Test
    public void getMyChirps() throws Exception {
    }

    @Test
    public void getChirps() throws Exception {
    }

    @Test
    public void chobiIsBanned() throws Exception {
    }

    @Test
    public void megaTextChecker() throws Exception {
    }

}