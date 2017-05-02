package controllers;

import domain.Brand;
import domain.CreditCard;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.CreditCardService;
import services.ManagerService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("creditcard")
public class CreditCardController extends AbstractController {


    //Services ----------------------------------------------------------------

    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private ManagerService managerService;

    //Constructors----------------------------------------------

    public CreditCardController() {
        super();
    }



//
//    @RequestMapping( value="/list", method = RequestMethod.GET)
//    public ModelAndView creditcardList() {
//
//        ModelAndView result;
//    Collection<CreditCard> creditCard;
//        creditCard = creditCardService.findAll();
//        result = new ModelAndView("credit-card/list");
//        result.addObject("creditCard", creditCard);
//        result.addObject("requestURI","creditcard/list.do");
//
//        return result;
//    }
//
//
//    //Create Method -----------------------------------------------------------
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView create(){
//
//        ModelAndView result;
//
//        CreditCard creditCard = creditCardService.create();
//        result = createEditModelAndView(creditCard);
//
//        return result;
//
//    }

    // Edition ---------------------------------------------------------

    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView edit(){
        ModelAndView result;
        CreditCard creditCard;
        Manager manager = managerService.findByPrincipal();
        creditCard= manager.getCreditCard();
        Assert.notNull(creditCard);
        result= createEditModelAndView(creditCard);
        result.addObject("creditCard", creditCard);
        result.addObject("brand", Brand.values());


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
                managerService.save(managerService.findByPrincipal());
                result= new ModelAndView("chorbi/success");
            }catch(Throwable oops){
                result= createEditModelAndView(creditCard, "general.commit.error");
            }
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

        result= new ModelAndView("credit-card/edit");
        result.addObject("creditCard", creditCard);
        result.addObject("message", message);

        return result;

    }


}








