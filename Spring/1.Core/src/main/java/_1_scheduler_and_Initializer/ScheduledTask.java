package _1_scheduler_and_Initializer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

/**
 * @author Ilya Galatyuk
 */

@EnableScheduling //this is only for launching from main(), not as servlet
public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5 * 1000)
    public void reportCurrentTime() {
        //disabled to not spam. enable for tests
        //System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
