/*
 * CustomerController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import domain.Liked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.LikedService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/liked")
public class LikedController extends AbstractController {

    // Constructors -----------------------------------------------------------

    public LikedController() {
        super();
    }

    @Autowired
    private LikedService likedService;

//    @RequestMapping( value="/list", method = RequestMethod.GET)
//    public ModelAndView list() {
//
//        ModelAndView result;
//        Collection<Liked> chirps;
//
//        chirps = likedService.findAll();
//        result = new ModelAndView("liked/list");
//        result.addObject("likes", chirps);
//        result.addObject("requestURI","liked/list.do");
//
//        return result;
//    }
//

//    //Create Method -----------------------------------------------------------
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView create() {
//
//        ModelAndView result;
//
//        Liked chirp = likedService.create();
//        result = createEditModelAndView(chirp);
//
//        return result;
//
//    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int chirpId){
        ModelAndView result;
        Liked chirp;

        chirp= likedService.findOne(chirpId);
        Assert.notNull(chirp);
        result= createEditModelAndView(chirp);

        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Liked liked, BindingResult binding){
        ModelAndView result;

        if(!binding.hasErrors()){
            result= createEditModelAndView(liked);
        }else{
            try{
                likedService.postLike(liked);
                result= new ModelAndView("chorbi/success");
            }catch(Throwable oops){
                result= createEditModelAndView(liked, "chirp.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete(int likeId){
        ModelAndView result;

            likedService.delete(likedService.findOne(likeId));
            result=new ModelAndView("chorbi/success");


        return result;
    }


    // Ancillary methods ------------------------------------------------

    protected ModelAndView createEditModelAndView(Liked chirp){
        ModelAndView result;

        result= createEditModelAndView(chirp, null);

        return result;
    }

    protected ModelAndView createEditModelAndView(Liked chirp, String message){
        ModelAndView result;

        result= new ModelAndView("liked/edit");
        result.addObject("like", chirp);
        result.addObject("message", message);

        return result;

    }


}


