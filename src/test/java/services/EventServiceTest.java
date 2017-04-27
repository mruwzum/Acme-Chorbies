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
import security.UserAccount;
import utilities.AbstractTest;

import java.util.Date;

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

    @Test
    public void testEventsOK(){

        System.out.println(eventService.okEvents());


//        Long mes = 31*24*60*60*1000L;
//        Date now = new Date(System.currentTimeMillis()-100);
//        Date lastMonth = new Date(System.currentTimeMillis()-mes);
//        Date nextMonth = new Date(System.currentTimeMillis()+mes);

//        System.out.println(now);
//        System.out.println(lastMonth);
//        System.out.println(nextMonth);
//
//        System.out.println(now.getTime());
//        System.out.println(lastMonth);
//        System.out.println(nextMonth);


    }

}