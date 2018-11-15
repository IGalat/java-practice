package tutorialsPoint._13_customEvents;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Custom events
 * 1) Create class that extends ApplicationEvent (CustomEvent here)
 * 2) Make publisher-class for it, that implements ApplicationEventPublisherAware. Declare it in config as bean
 * 3) Make an event-handler class that implements ApplicationListener<T>  ( ApplicationListener<CustomEvent> here). Declare it as bean
 * 4) Use ConfigurableApplicationContext, not usual ApplicationContext
 */

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans13.xml");

        CustomEventPublisher cvp = (CustomEventPublisher) context.getBean("customEventPublisher");

        cvp.publish();
        cvp.publish();
    }
}