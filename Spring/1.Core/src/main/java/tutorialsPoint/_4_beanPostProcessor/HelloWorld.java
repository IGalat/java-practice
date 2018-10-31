package tutorialsPoint._4_beanPostProcessor;

/**
 * Created by GREGTECH on 17.05.2017.
 */
public class HelloWorld {
    private String message;

    public void printMessage() {
        System.out.println("Your message: " + message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        System.out.println("Bean is going through init.");
    }

    public void destroy() {
        System.out.println("Bean will destroy now.");
    }
}
