package _6_javaConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Java-configured app setup:
 * 1) Make a page with content(6_javaconfig.jsp here)
 * 2) Make @Controller class(this one). This class is same as it would be with XML config.
 * 3) Make a class that extends WebMvcConfigurerAdapter. It will contain prefixes/suffixes, exception handlers, etc(replace .xml config)
 * 4) Make a class that extends AbstractAnnotationConfigDispatcherServletInitializer.
 * It will override web.xml config and DISABLE it(even parts that don't conflict).
 * Side note: Java-configured app is initialized at server startup, not lazily, unlike xml-configured..
 *
 * @author Ilya Galatyuk
 */

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomeNoName() {
        return "6_javaConfig";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ModelAndView welcomeWithName(@PathVariable String name) {
        return new ModelAndView("6_javaConfig", "name", name);
    }
}
