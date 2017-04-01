package controllers;


import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;

import java.util.Collection;


@Controller
@RequestMapping("/administrator")
public class DashboardController extends AbstractController {

    @Autowired
    private AdministratorService administratorService;


    public DashboardController() {
        super();
    }

    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard() {
        ModelAndView res;


        Collection<Chorbi> q1 = administratorService.chorbiesPerCity();
        Collection<Chorbi> q2 = administratorService.chorbiesPerCountry();
        Double q6 = administratorService.averageAgesOfTheChorbies();
        Double q7 = administratorService.averageNumberOfLikesPerChorbi();
        Double q8 = administratorService.averageNumberOfChirpsReceived();
        Double q9 = administratorService.averageNumberOfChirpsSended();
        Double q10 = administratorService.ratioOfChorbiesWhoHaveInvalidOrUnregisteredCreditCard();
        Double q11 = administratorService.ratioOfChorbiesWhoSearchActivites();
        Double q12 = administratorService.ratioOfChorbiesWhoSearchFriendShip();
        Double q13 = administratorService.ratioOfChorbiesWhoSearchLove();
        Integer q14 = administratorService.maxAgeOfTheChorbies();
        Integer q15 = administratorService.minAgeOfTheChorbies();
        Integer q16 = administratorService.maxNumberOfLikePerChorbi();
        Integer q17 = administratorService.minNumberOfLikePerChorbi();
        Integer q18 = administratorService.maxNumberOfChirpsReceived();
        Integer q19 = administratorService.minNumberOfChirpsReceived();
        Integer q20 = administratorService.maxNumberOfChirpsSended();
        Integer q21 = administratorService.minNumberOfChirpsSended();
        Collection<Chorbi> q3 = administratorService.chorbiesSortedByTheNumberOfLikes();
        Chorbi q4 = administratorService.chorbieWhoHaveMoreChirpsReceived();
        Chorbi q5 = administratorService.chorbieWhoHaveMoreChirpsSended();










   res = new ModelAndView("administrator/dashboard");



        res.addObject("q1",q1);
        res.addObject("q2",q2);
        res.addObject("q3",q3);
        res.addObject("q4",q4);
        res.addObject("q5",q5);
        res.addObject("q6",q6);
        res.addObject("q7",q7);
        res.addObject("q8",q8);
        res.addObject("q9",q9);
        res.addObject("q10",q10);
        res.addObject("q11",q11);
        res.addObject("q12",q12);
        res.addObject("q13",q13);
        res.addObject("q14",q14);
        res.addObject("q15",q15);
        res.addObject("q16",q16);
        res.addObject("q17",q17);
        res.addObject("q18",q18);
        res.addObject("q19",q19);
        res.addObject("q20",q20);
        res.addObject("q21",q21);





        return res;
    }
}
