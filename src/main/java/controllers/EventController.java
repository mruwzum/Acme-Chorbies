package controllers;

import domain.Event;
import domain.Liked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.LikedService;

import javax.validation.Valid;
import java.util.Collection;

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

    @RequestMapping( value="/list", method = RequestMethod.GET)
    public ModelAndView list() {

        ModelAndView result;
        Collection<Event> chirps;

        chirps = eventService.findAll();
        result = new ModelAndView("event/list");
        result.addObject("events", chirps);
        result.addObject("requestURI","event/list.do");

        return result;
    }


    //Create Method -----------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Event chirp = eventService.create();
        result = createEditModelAndView(chirp);

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
                eventService.save(liked);
                result= new ModelAndView("chorbi/success");
            }catch(Throwable oops){
                result= createEditModelAndView(liked, "chirp.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(int eventId){
        ModelAndView result;

        eventService.delete(eventService.findOne(eventId));
        result=new ModelAndView("chorbi/success");


        return result;
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
