package tutorialsPoint._12_eventsHandling;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Handling events with beans
 * event handling is single-threaded!
 * 1) create class(es) that implement corresponding interface(s):
 * - ContextRefreshedEvent - This event is published when the ApplicationContext is either initialized or refreshed. This can also be raised using the refresh() method on the ConfigurableApplicationContext interface.
 * - ContextStartedEvent - This event is published when the ApplicationContext is started using the start() method on the ConfigurableApplicationContext interface. You can poll your database or you can restart any stopped application after receiving this event.
 * - ContextStoppedEvent - This event is published when the ApplicationContext is stopped using the stop() method on the ConfigurableApplicationContext interface. You can do required housekeep work after receiving this event.
 * - ContextClosedEvent - This event is published when the ApplicationContext is closed using the close() method on the ConfigurableApplicationContext interface. A closed context reaches its end of life; it cannot be refreshed or restarted.
 * - RequestHandledEvent - This is a web-specific event telling all beans that an HTTP request has been serviced.
 * 2) add beans of these classes in config
 * 3) use ConfigurableApplicationContext, not usual ApplicationContext
 * 4) call methods listed where needed
 * <p/>
 * -> they are global within config, not bound to beans
 */

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans12.xml");

        // Let us raise a start event.
        context.start();

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.printMessage();

        // Let us raise a stop event.
        context.stop();
    }
}