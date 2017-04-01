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
import domain.SearchCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AdministratorService;
import services.ChorbiService;
import services.SearchCacheService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
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

	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView actorList() {

		ModelAndView result;
		Collection<Administrator> administrator;

		administrator = administratorService.findAll();
		result = new ModelAndView("administrator/list");
		result.addObject("administrator", administrator);
		result.addObject("requestURI","administrator/list.do");

		return result;
	}


	//Create Method -----------------------------------------------------------



	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Administrator administrator = administratorService.create();
		result = createEditModelAndView(administrator);
		return result;

	}

	// Edition ---------------------------------------------------------

	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int administratorId){
		ModelAndView result;
		Administrator administrator;

		administrator= administratorService.findOne(administratorId);
		Assert.notNull(administrator);
		result= createEditModelAndView(administrator);

		return result;
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Administrator administrator, BindingResult binding){
		ModelAndView result;

		if(binding.hasErrors()){
			result= createEditModelAndView(administrator);
		}else{
			try{
				administratorService.save(administrator);
				result= new ModelAndView("chorbi/list.do");
			}catch(Throwable oops){
				result= createEditModelAndView(administrator, "administrator.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
	public ModelAndView delete(Administrator administrator){
		ModelAndView result;
		try{
			administratorService.delete(administrator);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result= createEditModelAndView(administrator, "administrator.commit.error");
		}

		return result;
	}


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

}
