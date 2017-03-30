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

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.UserAccount;
import services.ActorService;
import services.ChorbiService;
import services.CoordinateService;
import services.CreditCardService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public ChorbiController() {
		super();
	}

		@Autowired
		private ChorbiService chorbiService;
		@Autowired
		private ActorService actorService;
		@Autowired
		private CoordinateService coordinateService;
		@Autowired
		private CreditCardService creditCardService;


		@RequestMapping( value="/list", method = RequestMethod.GET)
		public ModelAndView list() {

			ModelAndView result;
			Collection<Chorbi> chorbies;

			chorbies = chorbiService.findAll();
			result = new ModelAndView("chorbi/list");
			result.addObject("chorbies", chorbies);
			result.addObject("requestURI","chorbi/list.do");

			return result;
		}


		//Create Method -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Chorbi chorbi = chorbiService.create();
		result = createEditModelAndView2(chorbi);
	result.addObject("genre",Genre.values());
		result.addObject("relationship",Relationship.values());
		return result;

	}
	@RequestMapping(value="/register", method=RequestMethod.POST, params="save")
	public ModelAndView register(@Valid Chorbi chorbi, BindingResult binding){
		ModelAndView result;
		if (!binding.hasErrors()) {
			result= createEditModelAndView2(chorbi);
		}else{
			try{
				actorService.registerAsAChorbi(chorbi);
				result= new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result= createEditModelAndView2(chorbi, "general.commit.error");
			}
		}
		return result;
	}
		// Edition ---------------------------------------------------------

		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit(){
			ModelAndView result;
			Chorbi chorbi = chorbiService.findByPrincipal();
			Assert.notNull(chorbi);
			result= createEditModelAndView(chorbi);
			result.addObject("genre",Genre.values());
			result.addObject("relationship",Relationship.values());
			result.addObject("brand",Brand.values());

			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid Chorbi chorbi, BindingResult binding){
			ModelAndView result;

			if(!binding.hasErrors()){
				result= createEditModelAndView(chorbi);
			}else{
				try{
					chorbi.setUserAccount(chorbiService.findByPrincipal().getUserAccount());
					chorbi.setCreditCard(chorbiService.findByPrincipal().getCreditCard());

					chorbi.setCoordinate(chorbiService.findByPrincipal().getCoordinate());
					chorbi.setBirthDate(chorbiService.findByPrincipal().getBirthDate());
					chorbiService.save(chorbi);

					result= new ModelAndView("chorbi/success");
				}catch(Throwable oops){
					result= createEditModelAndView(chorbi, "general.commit.error");
				}
			}
			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
		public ModelAndView delete(Chorbi chorbi){
			ModelAndView result;
			try{
				chorbiService.delete(chorbi);
				result=new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result= createEditModelAndView(chorbi, "general.commit.error");
			}

			return result;
		}


		// Ancillary methods ------------------------------------------------

		protected ModelAndView createEditModelAndView(Chorbi chorbi){
			ModelAndView result;

			result= createEditModelAndView(chorbi, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(Chorbi chorbi, String message){
			ModelAndView result;

			result= new ModelAndView("chorbi/edit");
			result.addObject("chorbi", chorbi);
			result.addObject("message", message);

			return result;

		}

	protected static ModelAndView createEditModelAndView2(Chorbi chorbi) {
		ModelAndView result;

		result= createEditModelAndView2(chorbi, null);

		return result;
	}



	protected static ModelAndView createEditModelAndView2(Chorbi chorbi, String message) {
		ModelAndView result;

		result = new ModelAndView("chorbi/register");
		result.addObject("chorbi", chorbi);
		result.addObject("message", message);

		return result;

	}
	}


