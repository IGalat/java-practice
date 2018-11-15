package tutorialsPoint._2_scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * scopes: singleton, prototype  showcase (config for object in .xml)
 * "singleton" is default setting for objects -> example1 = example2 here
 */
public class App {
    private String message;

    public void printMessage() {
        System.out.println("Your message: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans2.xml");

        // no "singleton" scope in Beans5_.xml, using default
        System.out.println("Example 2.1 ---------------");
        App hw1a = (App) context.getBean("HW_2.1");
        hw1a.setMessage("I'm hw1a");
        hw1a.printMessage();

        App hw1b = (App) context.getBean("HW_2.1");
        hw1b.printMessage();

        // "singleton" scope in Beans5_.xml
        System.out.println("Example 2.2 ---------------");
        App hw2a = (App) context.getBean("HW_2.2");
        hw2a.setMessage("I'm hw2a");
        hw2a.printMessage();

        App hw2b = (App) context.getBean("HW_2.2");
        hw2b.printMessage();

        // "prototype" scope
        System.out.println("Example 2.3 ---------------");
        App hw3a = (App) context.getBean("HW_2.3");
        hw3a.setMessage("I'm hw3a");
        hw3a.printMessage();

        App hw3b = (App) context.getBean("HW_2.3");
        hw3b.printMessage(); //out goes the default value. if not configured, out "null" obviously
    }
}
