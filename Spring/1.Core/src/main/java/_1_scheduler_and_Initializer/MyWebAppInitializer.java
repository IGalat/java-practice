package _1_scheduler_and_Initializer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * X implements WebApplicationInitializer : method onStartup() is executed upon servlet load, unlike BeanPostProcessor(see spring.tPoint.4)
 * Usually used instead of web.xml config, but can do anything.
 * Here it was used to initialize AppConfig(which should initialize itself?..)
 *
 * @author Ilya Galatyuk
 */


public class MyWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
