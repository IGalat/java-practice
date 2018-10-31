package _4_staticContent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * DOESN'T WORK
 * <p/>
 * Everything is HelloWorld-level, except .xml config file(maps resources folder), and .jsp that receives resource.
 */

@Controller
public class KittyController {
    @RequestMapping(value = "kitty", method = RequestMethod.GET)
    public String getPageWithCat() {
        return "4_staticContent";
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public String getHTMLPage() {
        return "redirect:resourcesLala/Static.html";
    }
}
