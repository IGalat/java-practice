package _1_scheduler_and_Initializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Ilya Galatyuk
 */

//as servlet
@Configuration
@EnableScheduling
public class AppConfig {

    @Bean
    public ScheduledTask task() {
        return new ScheduledTask();
    }
}