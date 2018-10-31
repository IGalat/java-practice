package tutorialsPoint._4_beanPostProcessor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanPostProcessor interface usage
 * allows for callback methods that allow to provide your own instantiation logic, dependency-resolution logic, etc.
 * two methods: before and after init config.
 * <p/>
 * how to implement:
 * 1. write a class that implements BeanPostProcessor, in methods fill whatever is needed
 * 2. in (Beans).xml  add bean with corresponding class
 */
public class App {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/tutorialspoint/Beans4.xml");

        HelloWorld obj = (HelloWorld) context.getBean("HW_4");
        obj.printMessage();
        context.registerShutdownHook();
    }

}
