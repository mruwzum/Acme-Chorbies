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

import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ChorbiService;

import javax.validation.Valid;
import java.util.Collection;

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

		@RequestMapping( value="/list", method = RequestMethod.GET)
		public ModelAndView list() {

			ModelAndView result;
			Collection<Chorbi> chorbies;

			chorbies = chorbiService.findAll();
			result = new ModelAndView("chrobi/list");
			result.addObject("chorbies", chorbies);
			result.addObject("requestURI","chrobi/list.do");

			return result;
		}


		//Create Method -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Chorbi chorbi = chorbiService.create();
		result = createEditModelAndView2(chorbi);

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
		public ModelAndView edit(@RequestParam int chorbiId){
			ModelAndView result;
			Chorbi chorbi;

			chorbi= chorbiService.findOne(chorbiId);
			Assert.notNull(chorbi);
			result= createEditModelAndView(chorbi);

			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid Chorbi chorbi, BindingResult binding){
			ModelAndView result;

			if(binding.hasErrors()){
				result= createEditModelAndView(chorbi);
			}else{
				try{
					chorbiService.save(chorbi);
					result= new ModelAndView("/profile.do");
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


