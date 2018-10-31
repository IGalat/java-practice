package tutorialsPoint._14_AOP.xmlBased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Advices only execute around methods(.getName() and such), not at constructor, not at bean retrieval time.
 * Expression in .xml points to execute around every method, but it's configurable.
 * <p/>
 * after-throwing advice doesn't execute. Because exception is unchecked and halts execution of program?
 */

public class App {
    public static void main(String[] args) {
        System.out.println("---------- POINT 0 ------------ before getting context");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/tutorialspoint/Beans14xml.xml");

        System.out.println("---------- POINT 1 ------------ before getting student bean from context");
        Student student = (Student) context.getBean("student");
        System.out.println("---------- POINT 2 ------------ student bean got, before .getName()");
        student.getName();
        System.out.println("---------- POINT 3 ------------ before .getAge()");
        student.getAge();
        System.out.println("---------- POINT 4 ------------ before mock exception");
        student.printThrowException();
        System.out.println("---------- POINT 5 ------------ after everything");
    }
}