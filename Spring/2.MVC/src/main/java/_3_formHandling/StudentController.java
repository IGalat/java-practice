package _3_formHandling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ilya Galatyuk
 */

@Controller
public class StudentController {
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView student() {
        // ModelAndView is a container of view + ModelMap. view=page(in this case: 3_student.jsp)
        // This class is needed only to be able to return both Views and ModelMap
        // First arg is view(page name), second and third are key and value of ModelMap(see _1_helloWorld)
        // Name(key) of the ModelMap attribute(2nd) has to correspond to 'commandName' of <form:form> in 3_student.jsp, or exception
        return new ModelAndView("3_student", "command123", new Student());
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    // @ModelAttribute here refers to a property of the Model object(value in key-value pair of ModelMap))
    // @ModelAttribute is an all-encompassing annotation, combining functionality of several:
    // https://stackoverflow.com/questions/3423262/what-is-modelattribute-in-spring-mvc
    // google @ModelAttribute more
    public String addStudent(@ModelAttribute("Lala") Student student, ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());
        return "3_result";
    }
}
