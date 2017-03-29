package controllers;


import domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/banner")
public class BannerController extends AbstractController {

	//Services ----------------------------------------------------------------

	@Autowired
	private BannerService bannerService;



	//Constructors----------------------------------------------

	public BannerController(){
		super();
	}

    protected static ModelAndView createEditModelAndView(Banner banner) {
        ModelAndView result;

        result= createEditModelAndView(banner, null);

        return result;
    }



    protected static ModelAndView createEditModelAndView(Banner banner, String message) {
        ModelAndView result;

        result = new ModelAndView("banner/edit");
        result.addObject("banner", banner);
        result.addObject("message", message);

        return result;

    }



    //Create Method -----------------------------------------------------------







	@RequestMapping( value="/list", method = RequestMethod.GET)
	public ModelAndView bannersList() {

		ModelAndView result;
		Collection<Banner> banner;

        banner = bannerService.findAll();
		result = new ModelAndView("banner/list");
		result.addObject("banner", banner);
		result.addObject("requestURI","banner/list.do");

		return result;
	}




	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {

        ModelAndView result;

		Banner banner = bannerService.create();
        result = createEditModelAndView(banner);

		return result;

		}


    // Ancillary methods ------------------------------------------------


    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam int bannerId){
        ModelAndView result;
        Banner banner;

        banner= bannerService.findOne(bannerId);
        Assert.notNull(banner);
        result= createEditModelAndView(banner);

        return result;
    }


    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Banner banner, BindingResult binding){
        ModelAndView result;
        if (binding.hasErrors()) {
            result= createEditModelAndView(banner);
        }else{
            try{
                bannerService.save(banner);
                result= new ModelAndView("redirect:list.do");
            }catch(Throwable oops){
                result= createEditModelAndView(banner, "banner.commit.error");
            }
        }
        return result;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST, params="delete")
    public ModelAndView delete(@Valid int bannerId){
        ModelAndView result;
        try{
            Banner banner = bannerService.findOne(bannerId);
            bannerService.delete(banner);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            Banner banner = bannerService.findOne(bannerId);
            result= createEditModelAndView(banner, "banner.commit.error");
        }

        return result;
    }
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public ModelAndView delete2(@RequestParam int bannerId){
        ModelAndView result;
        try{
            Banner banner = bannerService.findOne(bannerId);
            bannerService.delete(banner);
            result=new ModelAndView("redirect:list.do");
        }catch(Throwable oops){
            Banner banner = bannerService.findOne(bannerId);
            result= createEditModelAndView(banner, "banner.commit.error");
        }

        return result;
    }

}
