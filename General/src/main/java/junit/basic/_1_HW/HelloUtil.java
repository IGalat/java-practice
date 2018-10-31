package junit.basic._1_HW;

/**
 * Tested class.
 *
 * @author Ilya Galatyuk
 */
public class HelloUtil {
    private String message;

    public HelloUtil(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
