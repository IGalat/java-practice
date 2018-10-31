package tutorialsPoint._3_lifeCycle;

/**
 * methods are called because of the  init-method="myInitMethod" destroy-method="myDestroyMethod"  config in .xml
 */
public class InitDestroyXMLed {
    private String message;

    public void printMessage() {
        System.out.println("Your message: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void myInitMethod() {
        System.out.println("Bean (XMLed) is going through init");
    }

    public void myDestroyMethod() {
        System.out.println("Bean (XMLed) will destroy now");
    }
}
