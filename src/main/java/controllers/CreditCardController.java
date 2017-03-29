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
import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ChorbiService;
import services.CreditCardService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/chorbi")
public class CreditCardController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CreditCardController() {
		super();
	}

		@Autowired
		private CreditCardService creditCardService;

		@RequestMapping( value="/list", method = RequestMethod.GET)
		public ModelAndView list() {

			ModelAndView result;
			Collection<CreditCard> creditCards;

			creditCards = creditCardService.findAll();
			result = new ModelAndView("creditCard/list");
			result.addObject("creditCards", creditCards);
			result.addObject("requestURI","creditCard/list.do");

			return result;
		}


		//Create Method -----------------------------------------------------------



		// Edition ---------------------------------------------------------

		@RequestMapping(value="/edit", method=RequestMethod.GET)
		public ModelAndView edit(@RequestParam int creditCardId){
			ModelAndView result;
			CreditCard creditCard;

			creditCard = creditCardService.findOne(creditCardId);
			Assert.notNull(creditCard);
			result= createEditModelAndView(creditCard);

			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding){
			ModelAndView result;

			if(binding.hasErrors()){
				result= createEditModelAndView(creditCard);
			}else{
				try{
					creditCardService.save(creditCard);
					result= new ModelAndView("/profile.do");
				}catch(Throwable oops){
					result= createEditModelAndView(creditCard, "creditCard.commit.error");
				}
			}
			return result;
		}

		@RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
		public ModelAndView delete(CreditCard creditCard){
			ModelAndView result;
			try{
				creditCardService.delete(creditCard);
				result=new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result= createEditModelAndView(creditCard, "creditCard.commit.error");
			}

			return result;
		}


		// Ancillary methods ------------------------------------------------

		protected ModelAndView createEditModelAndView(CreditCard creditCard){
			ModelAndView result;

			result= createEditModelAndView(creditCard, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(CreditCard creditCard, String message){
			ModelAndView result;

			result= new ModelAndView("creditCard/edit");
			result.addObject("creditCard", creditCard);
			result.addObject("message", message);

			return result;

		}


	}


