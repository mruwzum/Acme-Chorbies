package controllers;
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
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/finder")
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
		Collection<Search> finders;
		
		finders = searchService.findAll();
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
		result = createEditModelAndView(search);
		
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
         
        return result;
    }
     
    @RequestMapping(value="/find", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Search search, BindingResult binding){
        ModelAndView result;
         
        if(binding.hasErrors()){
            result= createEditModelAndView(search);
        }else{
            try{
                Collection<Search> searches = searchService.findAll();
                 searchService.save(search);
                result= new ModelAndView("search/list");
                result.addObject("searches",searches);
            }catch(Throwable oops){
                result= createEditModelAndView(search, "search.commit.error");
            }
        }
        return result;
    }
     
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(Search search){
        ModelAndView result;
        try{
            searchService.delete(search);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            result= createEditModelAndView(search, "search.commit.error");
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
