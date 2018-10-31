package tutorialsPoint._3_lifeCycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * issues:
 * AbstractApplicationContext instead of ApplicationContext is required for context.registerShutdownHook();
 * context.registerShutdownHook(); is required for calling destroy methods
 */
public class App {
    public static void main(String[] args) {
        //wtf is AbstractApplicationContext?
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring/tutorialspoint/Beans3.xml");

        System.out.println("Example 3.1: through implemented interfaces ------");
        InitDestroyImplemented hw1 = (InitDestroyImplemented) context.getBean("HW_3.1");
        hw1.printMessage();

        System.out.println("Example 3.2: through xml config ------------------");
        InitDestroyXMLed hw2 = (InitDestroyXMLed) context.getBean("HW_3.2");
        hw2.printMessage();

        System.out.println("Example 3.3: through xml defaults ----------------");
        InitDestroyDefaulted hw3 = (InitDestroyDefaulted) context.getBean("HW_3.3");
        hw3.printMessage();

        //default methods aren't called, but both XMled and interfaced are
        System.out.println("Example 3.4: every type (conflict?) --------------");
        InitDestroyConflicted hw4 = (InitDestroyConflicted) context.getBean("HW_3.4");
        hw4.printMessage();

        //method exists only on Abstract version of ApplicationContext. is required for destroy methods to be called!
        context.registerShutdownHook();
    }
}
