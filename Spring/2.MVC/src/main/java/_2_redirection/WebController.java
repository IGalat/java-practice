package _2_redirection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ilya Galatyuk
 */

@Controller
public class WebController {
    //mapping to multiple URLs here. Empty ("") still requires URL to have slash ".../", not just "...", so "" equals "/"
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("headerH2", "Spring Page Redirection");
        //this points to the page name
        return "2_index";
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        //redirect, however, is to URL, not to page name. warn: "redirect:/finalPage" would redirect to absolute path!  localhost:8080/finalPage
        return "redirect:finalPage";
    }

    @RequestMapping(value = "/finalPage", method = RequestMethod.GET)
    public String finalPage() {
        return "2_finalPage";
    }

    //not relevant to redirecting. Shows how path variable can be used
    @RequestMapping(value = "/{appendixURL}", method = RequestMethod.GET)
    public String unusualURL(ModelMap modelMap, @PathVariable String appendixURL) {
        modelMap.addAttribute("TextForUnusualURL", "PEANUT BUTTER JELLY TIME! " + appendixURL);
        return "2_index";
    }
}
