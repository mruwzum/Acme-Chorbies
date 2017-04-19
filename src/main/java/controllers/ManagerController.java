package controllers;

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
import services.LikedService;
import services.ManagerService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by daviddelatorre on 19/4/17.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController extends AbstractController  {


    // Constructors -----------------------------------------------------------

    public ManagerController() {
        super();
    }

    @Autowired
    private ManagerService managerService;

    @RequestMapping( value="/list", method = RequestMethod.GET)
    public ModelAndView list() {

        ModelAndView result;
        Collection<Manager> chirps;

        chirps = managerService.findAll();
        result = new ModelAndView("manager/list");
        result.addObject("managers", chirps);
        result.addObject("requestURI","manager/list.do");

        return result;
    }


    //Create Method -----------------------------------------------------------

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

        Manager chirp = managerService.create();
        result = createEditModelAndView(chirp);

        return result;

    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value="/edit", method= RequestMethod.GET)
    public ModelAndView edit(@RequestParam int managerId){
        ModelAndView result;
        Manager chirp;

        chirp= managerService.findOne(managerId);
        Assert.notNull(chirp);
        result= createEditModelAndView(chirp);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Manager manager, BindingResult binding){
        ModelAndView result;

        if(!binding.hasErrors()){
            result= createEditModelAndView(manager);
        }else{
            try{
                managerService.save(manager);
                result= new ModelAndView("chorbi/success");
            }catch(Throwable oops){
                result= createEditModelAndView(manager, "chirp.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(int managerId){
        ModelAndView result;

        managerService.delete(managerService.findOne(managerId));
        result=new ModelAndView("chorbi/success");


        return result;
    }


    // Ancillary methods ------------------------------------------------

    protected ModelAndView createEditModelAndView(Manager chirp){
        ModelAndView result;

        result= createEditModelAndView(chirp, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Manager chirp, String message){
        ModelAndView result;

        result= new ModelAndView("manager/edit");
        result.addObject("manager", chirp);
        result.addObject("message", message);

        return result;

    }

}
