package controllers;

import domain.Event;
import domain.Liked;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

    // Constructors -----------------------------------------------------------

    public EventController() {
        super();
    }

    @Autowired
    private EventService eventService;
    @Autowired
    private ChorbiService chorbiService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private SearchService searchService;




    @RequestMapping( value="/list", method = RequestMethod.GET)
    public ModelAndView list() {

        ModelAndView result;
        Collection<Event> chirps;

        chirps = eventService.findAll();
        result = new ModelAndView("event/list");
        result.addObject("event", chirps);
        result.addObject("requestURI","event/list.do");

        return result;
    }
    @RequestMapping( value="/listMy", method = RequestMethod.GET)
    public ModelAndView listMyEvents() {

        ModelAndView result;
        Collection<Event> events = managerService.findByPrincipal().getCreatedEvents();

        result = new ModelAndView("event/list");
        result.addObject("event", events);
        result.addObject("requestURI","event/list.do");

        return result;
    }

    @RequestMapping( value="/eventToAssist", method = RequestMethod.GET)
    public ModelAndView listEventToAssits() {

        ModelAndView result;
        Collection<Event> events = chorbiService.findByPrincipal().getEventsToGo();

        result = new ModelAndView("event/list");
        result.addObject("event", events);
        result.addObject("requestURI","event/list.do");

        return result;
    }

    //Create Method -----------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;
        Manager us = managerService.findByPrincipal();
       if (searchService.checkCreditCard(managerService.findByPrincipal().getCreditCard())){
           Event chirp = eventService.create();
           result = createEditModelAndView(chirp);

    } else {
        result =  new ModelAndView("credit-card/error");
    }
        return result;

    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value="/edit", method= RequestMethod.GET)
    public ModelAndView edit(@RequestParam int eventId){
        ModelAndView result;
        Event chirp;

        chirp= eventService.findOne(eventId);
        Assert.notNull(chirp);
        result= createEditModelAndView(chirp);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Event liked, BindingResult binding){
        ModelAndView result;

        if(binding.hasErrors()){
            result= createEditModelAndView(liked);
        }else{
            try{
                liked.setOwner(managerService.findByPrincipal());
                eventService.save(liked);

                result= new ModelAndView("chorbi/success");
            }catch(Throwable oops){
                result= createEditModelAndView(liked, "chirp.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(int eventId) {
        ModelAndView result;

        eventService.delete(eventService.findOne(eventId));
        result = new ModelAndView("chorbi/success");
        //TODO sale errorzaco al borrar evento ya que está asociado, mirar como borrar pero sin borrar la asociación
        // o borrar el evento sin que borre el resto y punto.

        return result;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int eventId){

        ModelAndView res;
        Event event = eventService.findOne(eventId);

        res = new ModelAndView("event/view");
        res.addObject("title",event.getTitle());
        res.addObject("date", event.getDate());
        res.addObject("picture", event.getPicture());
        res.addObject("description", event.getDescription());
        res.addObject("numberOfSeats", event.getNumberOfSeats());

        return res;


    }

    @RequestMapping("/okevents")
    public ModelAndView okEvents(){

        ModelAndView res;
        Collection<Event> events = eventService.okEvents();

        res =  new ModelAndView("event/list");
        res.addObject("event", events);

        return res;


    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerNewPartaker(@RequestParam int eventID){

        ModelAndView res;
        Event event = eventService.findOne(eventID);

        Boolean bol = eventService.registerNewPartaker(chorbiService.findByPrincipal(), event);


        if(bol){
            res = new ModelAndView("chorbi/success");
        }else{
            res = new ModelAndView("chorbi/error");
        }

        return res;

    }

    @RequestMapping(value = "/unregister", method = RequestMethod.GET)
    public ModelAndView unRegisterNewPartaker(@RequestParam int eventID){

        ModelAndView res;
        Event event = eventService.findOne(eventID);

        Boolean bol = eventService.unRegisterPartaker(chorbiService.findByPrincipal(), event);


        if(bol){
            res = new ModelAndView("chorbi/success");
        }else{
            res = new ModelAndView("chorbi/error");
        }

        return res;

    }


    // Ancillary methods ------------------------------------------------

    protected ModelAndView createEditModelAndView(Event chirp){
        ModelAndView result;

        result= createEditModelAndView(chirp, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Event chirp, String message){


        ModelAndView result;

        result= new ModelAndView("event/edit");
        result.addObject("event", chirp);
        result.addObject("message", message);

        return result;

    }

}
