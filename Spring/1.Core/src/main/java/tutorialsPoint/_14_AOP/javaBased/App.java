package tutorialsPoint._14_AOP.javaBased;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * skipped documenting
 */

public class App {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans14java.xml");

        Student student = (Student) context.getBean("student");
        student.getName();
        student.getAge();

        Thread.sleep(500);
        student.printThrowException();
    }
}