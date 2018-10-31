package tutorialsPoint._3_lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * methods are called because of overridden interfaces. Only called if when it's treated like a bean(e.g. present in .xml)
 */
public class InitDestroyImplemented implements InitializingBean, DisposableBean {
    private String message;

    public void printMessage() {
        System.out.println("Your message: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean (interfaced) is going through init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean (interfaced) will destroy now");
    }
}
