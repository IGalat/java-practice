package _1_scheduler_and_Initializer;

import org.springframework.boot.SpringApplication;

/**
 * @author Ilya Galatyuk
 */

//not as servlet
public class App {
    public static void main(String[] args) {
        //this class requires Spring boot
        SpringApplication.run(ScheduledTask.class, args);
    }
}
