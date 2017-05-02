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

import domain.Chirp;
import domain.ChirpMultiple;
import domain.Chorbi;
import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ChirpMultipleService;
import services.ChirpService;
import services.EventService;
import services.ManagerService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/chirpMultiple")
public class ChirpMultipleController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public ChirpMultipleController() {
		super();
	}

		@Autowired
		private ChirpMultipleService chirpMultipleService;
	@Autowired
	private EventService eventService;

	@Autowired
	private ManagerService managerService;

//		@RequestMapping( value="/list", method = RequestMethod.GET)
//		public ModelAndView list() {
//
//			ModelAndView result;
//			Collection<Chirp> chirps;
//
//			chirps = chirpService.findAll();
//			result = new ModelAndView("chirp/list");
//			result.addObject("chirps", chirps);
//			result.addObject("requestURI","chirp/list.do");
//
//			return result;
//		}
//
//
		//Create Method -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int eventId) {

		ModelAndView result;

		ChirpMultiple chirp = chirpMultipleService.create();
		Event e  = eventService.findOne(eventId);
		chirp.setReceivers(e.getPartakers());
		chirp.setSender(managerService.findByPrincipal());
		result = createEditModelAndView2(chirp);

		return result;

	}


		// Edition ---------------------------------------------------------

		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit(@RequestParam int chirpId){
			ModelAndView result;
			ChirpMultiple chirp;

			chirp= chirpMultipleService.findOne(chirpId);
			Assert.notNull(chirp);
			result= createEditModelAndView2(chirp);

			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid ChirpMultiple chirp, BindingResult binding){
			ModelAndView result;

//			if(binding.hasErrors()){
//				result= createEditModelAndView(chirp);
//			}else{
//				try{
					chirpMultipleService.broadcastChirp(chirp);
					result= new ModelAndView("chorbi/success");
//				}catch(Throwable oops){
//					result= createEditModelAndView(chirp, "chirp.commit.error");
//				}
//			}
			return result;
		}

//		@RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//		public ModelAndView delete(Chirp chirp){
//			ModelAndView result;
//			try{
//				chirpService.delete(chirp);
//				result=new ModelAndView("redirect:list.do");
//			}catch(Throwable oops){
//				result= createEditModelAndView(chirp, "chirp.commit.error");
//			}
//
//			return result;
//		}


//		@RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//		public ModelAndView delete(Chirp chirp){
//			ModelAndView result;
//			try{
//				chirpService.delete(chirp);
//				result=new ModelAndView("redirect:list.do");
//			}catch(Throwable oops){
//				result= createEditModelAndView(chirp, "chirp.commit.error");
//			}
//
//			return result;
//		}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam int chirpId, int eventId){
		ModelAndView result;
		ChirpMultiple chirpMultiple  = chirpMultipleService.findOne(chirpId);

				 if (eventService.checkChirMultipletoDelete(eventService.findOne(eventId),chirpMultiple)){
					 chirpMultipleService.delete(chirpMultiple,eventService.findOne(eventId));
					 result = new ModelAndView("chorbi/success");

				 }else{
				 	result =  new ModelAndView("chorbi/error");
				 }



return result;

	}

		// Ancillary methods ------------------------------------------------


	protected ModelAndView createEditModelAndView2(ChirpMultiple chirp){
		ModelAndView result;

		result= createEditModelAndView2(chirp, null);

		return result;
	}

	protected ModelAndView createEditModelAndView2(ChirpMultiple chirpM, String message){
		ModelAndView result;

		result= new ModelAndView("chirpMultiple/edit");
		result.addObject("chirp", chirpM);
		result.addObject("message", message);

		return result;

	}



	}


