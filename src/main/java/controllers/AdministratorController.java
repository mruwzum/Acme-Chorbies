/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import domain.Administrator;
import domain.Chorbi;
import domain.Fee;
import domain.SearchCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;
import services.ChorbiService;
import services.FeeService;
import services.SearchCacheService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private ChorbiService chorbiService;
	@Autowired
	private SearchCacheService searchCacheService;
	@Autowired
	private FeeService feeService;




	// Ancillary methods ------------------------------------------------

	protected ModelAndView createEditModelAndView(Administrator administrator){
		ModelAndView result;

		result= createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator, String message){
		ModelAndView result;

		result= new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;

	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView banChorbi(int chorbiId){

		ModelAndView res;
		Chorbi chorbi = chorbiService.findOne(chorbiId);

//		try {
			administratorService.banChorbi(chorbi);
			res = new ModelAndView("chorbi/success");
//		} catch (Exception e) {
//			res = new ModelAndView("chorbi/error");
//			res.addObject("trace", e.fillInStackTrace());
//		}


		return res;


	}


	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unbanChorbi(int chorbiId){

		ModelAndView res;
		Chorbi chorbi = chorbiService.findOne(chorbiId);

		try {
			administratorService.unbanChorbi(chorbi);
			res = new ModelAndView("chorbi/success");
		} catch (Exception e) {
			res = new ModelAndView("chorbi/error");
			res.addObject("trace", e.toString());
		}


		return res;


	}


	@RequestMapping("/changeCache")
	public ModelAndView changeCache(){
		ModelAndView res;

		List<SearchCache> caches = new ArrayList<>(searchCacheService.findAll());
		res = new ModelAndView("administrator/editCache");
		res.addObject("searchCache", caches.get(0));


		return res;
	}
	@RequestMapping(value = "/editCache", method=RequestMethod.POST, params="save" )
	public ModelAndView editCache(@Valid SearchCache searchCache){
		ModelAndView res;

		try {
			searchCacheService.save(searchCache);
			res = new ModelAndView("chorbi/success");
		} catch (Exception e) {
			e.printStackTrace();
			res =  new ModelAndView("chorbi/error");
		}

		return res;
	}


	@RequestMapping("/changeFeeManager")
	public ModelAndView changeFeeManager(){
		ModelAndView res;

		List<Fee> caches = new ArrayList<>(feeService.findAll());
		res = new ModelAndView("administrator/editFee2");
		res.addObject("Fee", caches.get(0));


		return res;
	}


	@RequestMapping("/changeFeeChorbi")
	public ModelAndView changeFeeChorbi(){
		ModelAndView res;

		List<Fee> caches = new ArrayList<>(feeService.findAll());
		res = new ModelAndView("administrator/editFee2");
		res.addObject("Fee", caches.get(1));


		return res;
	}




	@RequestMapping(value = "/editFee", method=RequestMethod.POST, params="save" )
	public ModelAndView editFee(@Valid Fee searchCache){
		ModelAndView res;

		try {
			feeService.save(searchCache);
			res = new ModelAndView("chorbi/success");
		} catch (Exception e) {
			e.printStackTrace();
			res =  new ModelAndView("chorbi/error");
			res.addObject("trace",e.getStackTrace());
		}

		return res;
	}

}
