package controllers;
import domain.Chorbi;
import domain.Genre;
import domain.Relationship;
import domain.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ChorbiService;
import services.SearchService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController {
	
	//Services ----------------------------------------------------------------
	
	@Autowired
	private SearchService searchService;
	@Autowired
    private ChorbiService chorbiService;
	//Constructors----------------------------------------------


	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView finderList() {
		
		ModelAndView result;
		Collection<Search> finders = new HashSet<>();
		if(searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard())){
            finders.addAll(chorbiService.findByPrincipal().getMySearches());
            searchService.checkTime(finders);
            result = new ModelAndView("search/list");
            result.addObject("searchs", finders);
            result.addObject("requestURI","search/list.do");
        }else{
		    result =  new ModelAndView("credit-card/error");
        }


		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Search search = searchService.create();


		result = createEditModelAndView(search);
        result.addObject("genre", Genre.values());
        result.addObject("relationship", Relationship.values());
		
		return result;

		}
	
	 // Edition ---------------------------------------------------------
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int searchId){
        ModelAndView result;
        Search search;

        search= searchService.findOne(searchId);
        Assert.notNull(search);
        result= createEditModelAndView(search);
        result.addObject("genre",Genre.values());
        result.addObject("relationship",Relationship.values());
        return result;
    }


    @RequestMapping(value="/findR", method=RequestMethod.GET)
    public ModelAndView findR(@RequestParam int searchId) {
        ModelAndView result;
        Search search;

        search = searchService.findOne(searchId);
        Assert.notNull(search);

        if (searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard())) {


            if (search.getCoordinate().getCity().isEmpty()) {
                search.getCoordinate().setCity("void");
            }
            List<Chorbi> chorbies = searchService.davidFinder(search);

            result = new ModelAndView("chorbi/list");
            result.addObject("chorbies", chorbies);
        } else {
            result =  new ModelAndView("credit-card/error");
        }
        return result;
    }
    @RequestMapping(value="/find", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Search search, BindingResult binding){
        ModelAndView result;

        if(binding.hasErrors()){
			result= createEditModelAndView(search);
		}else{
			try{

        if(searchService.checkCreditCard(chorbiService.findByPrincipal().getCreditCard())){
            if (search.getCoordinate().getCity().isEmpty()) {
                search.getCoordinate().setCity("void");
            }
            search.setCreationDate(new Date(System.currentTimeMillis()-100));
            List<Chorbi> chorbies = searchService.davidFinder(search);

            search.setOwner(chorbiService.findByPrincipal());
            Search search1 = searchService.save(search);
            Chorbi chorbi = chorbiService.findByPrincipal();
            chorbi.getMySearches().add(search1);

            result= new ModelAndView("chorbi/list");
            result.addObject("chorbies",chorbies);
        }else{
            result =  new ModelAndView("credit-card/error");
        }
            }catch(Throwable oops){
				result= createEditModelAndView(search, "administrator.commit.error");
			}
		}
        return result;
    }
     
//    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
//    public ModelAndView delete(Search search){
//        ModelAndView result;
//        try{
//            searchService.delete(search);
//            result=new ModelAndView("redirect:list.do");
//        }catch(Throwable oops){
//            result= createEditModelAndView(search, "general.commit.error");
//        }
//
//        return result;
//    }
	
	// Ancillary methods ------------------------------------------------
    
    protected ModelAndView createEditModelAndView(Search search){
        ModelAndView result;
         
        result= createEditModelAndView(search, null);
         
        return result;
    }
     
    protected ModelAndView createEditModelAndView(Search search, String message){
    	ModelAndView result;
    	
        result= new ModelAndView("search/edit");
        result.addObject("search", search);
        result.addObject("message", message);

         
        return result;
 
    }


}
