package tutorialsPoint._3_lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * all methods applied. default gets overrided, implemented and XMLed are executed
 */
public class InitDestroyConflicted implements InitializingBean, DisposableBean {
    private String message;

    public void printMessage() {
        System.out.println("Your message: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void defaultInit() {
        System.out.println("Bean (conflicted) is going through (defaulted) init");
    }

    public void defaultDestroy() {
        System.out.println("Bean (conflicted) will (defaulted) destroy now");
    }

    public void myInitMethod() {
        System.out.println("Bean (conflicted) is going through (XMLed) init");
    }

    public void myDestroyMethod() {
        System.out.println("Bean (conflicted) will (XMLed) destroy now");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean (conflicted) is going through (interfaced) init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Bean (conflicted) will (interfaced) destroy now");
    }
}
