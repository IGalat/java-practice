package _1_helloWorld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Basic spring MVC app setup:
 * 0) Have a 'webapp' folder with web.xml and WEB-INF/. Folder MUST be named 'webapp' not web or anything else, or Spring doesn't read correctly!
 * 1) Make a page with content in 'webapp/' (in this case: pages/com.jr.rest/spring/tutorialsPoint/1_hello.jsp)
 * 2) make @Controller class(this one), that has:
 * - @RequestMapping for class and methods
 * - method can return string, which with prefix and suffix
 * from XX-servlet.xml, in this case: WEB-INF/com.jr.rest/spring/tutorialsPoint/Servlet1_HW.xml
 * will make the name of the page with content(1)
 * - method's ModelMap can contain attributes in key-value pairs, where key is the name of variable in the page with content(1)
 * 3) Make the application context .xml file in 'webapp/' (in this case: WEB-INF/com.jr.rest/spring/tutorialsPoint/Servlet1_HW.xml)
 * - default path/name is WEB-APP/XX-servlet.xml, where XX - name of servlet in web.xml. But this isn't necessary(see (4) )
 * - contains component-scan, and prefix/suffix
 * 4) in web.xml make a <servlet>, optionally with <init-param> which contains path/name of app context(3), if you don't want the default one
 * <p/>
 * Request in client to get response:
 * http://{Server IP:port}/{servlet path from web.xml}/{@ResuestMapping path of this class}   in our case:
 * http://localhost:8080/HomePractice/rest/spring/hw/hello
 * when deploying tomcat from idea, project name isn't being specified:
 * http://localhost:8080/rest/spring/hw/hello
 * This will output 1_hello.jsp page, with ${message} parameter as set in printHello method
 */

@Controller
// relative path (to the <servlet-mapping> of web.xml). Optional.
@RequestMapping("/hello")
public class HelloController {
    // allows to set sub-path, and HTTP method
    @RequestMapping(method = RequestMethod.GET)
    // ModelMap implements Model. ModelMap is optional(injected). Map of Model(M in MVC) with key-value
    // Stores com.jr.model elements(POJO, or just any data) for future use in view(page) and other controllers(methods)
    public String printHello(ModelMap model) {
        // sets '${message}' variable of 1_hello.jsp
        model.addAttribute("message", "Hello Spring MVC Framework!");
        // prefix+this+suffix is name of the page(see Servlet1_HW.xml)
        // View(V in MVC)  http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/View.html
        return "1_hello";
    }
}