package tutorialsPoint._1_helloWorld;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello World example:
 * make (Beans).xml file;
 * create ApplicationContext based on it;
 * call .getBean() method to get object.
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
        //step 2: create an ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans1.xml");
        //step 3: get required bean from context
        App hw1 = (App) context.getBean("HW_1");
        hw1.printMessage();
    }
}

//achtung: need to configure context for Beans1.xml after creation. follow Idea instructions, any context is ok
//Beans1.xml file content:

//<?xml version = "1.0" encoding = "UTF-8"?>
//
//<beans xmlns="http://www.springframework.org/schema/beans"
//        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//        xsi:schemaLocation="http://www.springframework.org/schema/beans
//        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
//
//<bean id="HW_1" class="spring.tutorialspoint._1_helloWorld.App">
//<property name="message" value="Hello World!"/>
//</bean>
//
//</beans>