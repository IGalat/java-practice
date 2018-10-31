package _5_exceptionHandling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * see .xml app context for exception resolver config
 *
 * @author Ilya Galatyuk
 */

@Controller
public class HumanController {
    @RequestMapping(value = "/human", method = RequestMethod.GET)
    public ModelAndView human() {
        return new ModelAndView("5_human", "command", new Human());
    }

    @RequestMapping(value = "/addHuman", method = RequestMethod.POST)
    @ExceptionHandler({MySpringException.class})
    public String addHuman(@ModelAttribute Human human, ModelMap modelMap) {
        if(human.getName().length() < 5)
            throw new MySpringException("Given name is too short");
        else modelMap.addAttribute("name", human.getName());

        if(human.getAge() < 10)
            throw new MySpringException("Given age is too low");
        else modelMap.addAttribute("age", human.getAge());

        modelMap.addAttribute("id", human.getId());
        return "5_result";
    }
}
