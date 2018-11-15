package tutorialsPoint._5_inheritanceInXML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Inheritance through (Beans).xml file
 * helloIndia inherits helloWorld_5, and overrides message1. message2 is displayed from helloWorld_5
 * helloIndia2 inherits abstract bean
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans5_.xml");

        System.out.println("WORLD -----------------------------");
        HelloWorld objA = (HelloWorld) context.getBean("helloWorld_5");
        objA.printMessage1();
        objA.printMessage2();

        System.out.println("INDIA (inherits 2nd) --------------");
        HelloIndia objB = (HelloIndia) context.getBean("helloIndia");
        objB.printMessage1();
        objB.printMessage2();
        objB.printMessage3();

        //abstract inheritance: parent(template)
        System.out.println("ABSTRACT --------------------------");
        HelloIndia objC = (HelloIndia) context.getBean("helloIndia2");
        objC.printMessage1();
        objC.printMessage2();
        objC.printMessage3();
    }
}
