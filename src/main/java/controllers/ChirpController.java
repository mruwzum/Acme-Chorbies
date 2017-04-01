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
import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ChirpService;
import services.ChorbiService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/chirp")
public class ChirpController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public ChirpController() {
		super();
	}

		@Autowired
		private ChirpService chirpService;

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
//		//Create Method -----------------------------------------------------------
//
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create() {
//
//		ModelAndView result;
//
//		Chirp chirp = chirpService.create();
//		result = createEditModelAndView(chirp);
//
//		return result;
//
//	}

		// Edition ---------------------------------------------------------

		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit(@RequestParam int chirpId){
			ModelAndView result;
			Chirp chirp;

			chirp= chirpService.findOne(chirpId);
			Assert.notNull(chirp);
			result= createEditModelAndView(chirp);

			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid Chirp chirp, BindingResult binding){
			ModelAndView result;

//			if(binding.hasErrors()){
//				result= createEditModelAndView(chirp);
//			}else{
//				try{
					chirpService.postChirp(chirp);
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


	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView delete(int chirpId){
		ModelAndView result;

		chirpService.delete(chirpService.findOne(chirpId));
		result=new ModelAndView("chorbi/success");


		return result;
	}

		// Ancillary methods ------------------------------------------------

		protected ModelAndView createEditModelAndView(Chirp chirp){
			ModelAndView result;

			result= createEditModelAndView(chirp, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Chirp chirp, String message){
			ModelAndView result;

			result= new ModelAndView("chirp/edit");
			result.addObject("chirp", chirp);
			result.addObject("message", message);

			return result;

		}


		//OTHER METHODS

	@RequestMapping(value="/resend", method=RequestMethod.GET)
	public ModelAndView resend(@RequestParam int chirpId){
		ModelAndView result;
		Chirp chirp= chirpService.findOne(chirpId);
		Assert.notNull(chirp);

		try {
			chirpService.resend(chirp);
			result = new ModelAndView("chorbi/success");
		} catch (Exception e) {
			e.printStackTrace();
			result = new ModelAndView("chorbi/error");

		}

		return result;
	}

	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public ModelAndView reply(@RequestParam int chirpId){
		ModelAndView result;
		Chirp chirp= chirpService.findOne(chirpId);
		Assert.notNull(chirp);

		try {
			Chirp chirp1 =  chirpService.reply(chirp);
			result =  new ModelAndView("chirp/edit");
			result.addObject("chirp", chirp1);

		} catch (Exception e) {
			e.printStackTrace();
			result = new ModelAndView("chorbi/error");

		}

		return result;
	}


	}


