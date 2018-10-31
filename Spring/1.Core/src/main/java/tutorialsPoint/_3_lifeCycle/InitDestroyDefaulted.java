package tutorialsPoint._3_lifeCycle;

/**
 * methods are called because of the  default-init-method="defaultInit" default-destroy-method="defaultDestroy"  config in .xml
 */
public class InitDestroyDefaulted {
    private String message;

    public void printMessage() {
        System.out.println("Your message: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void defaultInit() {
        System.out.println("Bean (defaulted) is going through init");
    }

    public void defaultDestroy() {
        System.out.println("Bean (defaulted) will destroy now");
    }
}
