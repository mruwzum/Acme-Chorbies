/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package sample;

import javax.transaction.Transactional;

import domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.*;
import utilities.AbstractTest;

import java.util.*;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SampleTest extends AbstractTest {

	// System under test ------------------------------------------------------
@Autowired
private ChorbiService chorbiService;
@Autowired
private FeeService feeService;
@Autowired
private EventService eventService;
@Autowired
private ChirpMultipleService chirpMultipleService;
@Autowired
private ManagerService managerService;
	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.








//
//	@Test
//	public void ComputeSuscription(){
//		List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
//		List<Fee> fees = new ArrayList<>(feeService.findAll());
//		Date actual1 = new Date(System.currentTimeMillis());
//		Calendar startCalendar1 = new GregorianCalendar();
//		Calendar endCalendar1 = new GregorianCalendar();
//		endCalendar1.setTime(actual1);
//
//
//		for(Chorbi c: chorbis){
//			Date registeredDate = c.getSignUpDate();
//			startCalendar1.setTime(registeredDate);
//
//
//			int diffYear = endCalendar1.get(Calendar.YEAR) - startCalendar1.get(Calendar.YEAR);
//			int diffMonth = diffYear * 12 + endCalendar1.get(Calendar.MONTH) - startCalendar1.get(Calendar.MONTH);
//			c.setTotalFeeToPay(fees.get(1).getFeeValue() * diffMonth);
//			System.out.println(c.getName() +", has been registered since " +registeredDate + " - which has been registered for " + diffMonth + " months.");
//			System.out.println("then, he/she has to pay " + c.getTotalFeeToPay() +"$");
//			System.out.println("--------------------");
//		}
//
//
//	}
//
//	@Test
//	public void delteMultipleChirp(){
//
//
//		List<Event> events =  new ArrayList<>(eventService.findAll());
//
//		List<ChirpMultiple> chirpMultiples =  new ArrayList<>(events.get(0).getAnnouncements());
//		System.out.println(events.get(0).getAnnouncements());
//
//		chirpMultipleService.delete(chirpMultiples.get(0),events.get(0));
//		System.out.println(events.get(0).getAnnouncements());
//
//
//
//	}









	/*@Test
	public void samplePositiveTest() {
		Assert.isTrue(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sampleNegativeTest() {
		Assert.isTrue(false);
	}
*/
	// Ancillary methods ------------------------------------------------------

}
