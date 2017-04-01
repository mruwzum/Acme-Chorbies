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
//TODO CAPTURAR ERROR BONITO
		Assert.isTrue(searchService.checkCreditCard(chorbiService.findByPrincipal()),"invalid cc / cc no válida");
		finders.addAll(chorbiService.findByPrincipal().getMySearches());
		result = new ModelAndView("search/list");
		result.addObject("searchs", finders);
		result.addObject("requestURI","search/list.do");

		return result;
	}
	
	
	//Create Method -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView result;
		
		Search search = searchService.create();
        //TODO CAPTURAR ERROR BONITO
        Assert.isTrue(searchService.checkCreditCard(chorbiService.findByPrincipal()),"invalid cc / cc no válida");
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
     
    @RequestMapping(value="/find", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Search search, BindingResult binding){
        ModelAndView result;

//        if(!binding.hasErrors()){
//            result= createEditModelAndView(search);
//        }else{
//            try{
        if (search.getCoordinate().getCity().isEmpty()) {
            search.getCoordinate().setCity("void");
        }
            List<Chorbi> chorbies = searchService.finder(search.getAge(),search.getRelationship(),search.getGenre(),search.getCoordinate(),search.getKeyword());

        search.setOwner(chorbiService.findByPrincipal());
        Search search1 = searchService.save(search);
        Chorbi chorbi = chorbiService.findByPrincipal();
        chorbi.getMySearches().add(search1);
       // search1.getOwner().getMySearches().add(search1);

            result= new ModelAndView("chorbi/list");
            result.addObject("chorbies",chorbies);



//            }catch(Throwable oops){
//                result= createEditModelAndView(search, "general.commit.error");
//            }
//        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Search search){
        ModelAndView result;
        try{
            searchService.delete(search);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(search, "general.commit.error");
        }
         
        return result;   
    }
	
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
