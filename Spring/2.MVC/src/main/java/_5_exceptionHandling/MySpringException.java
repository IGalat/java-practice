package _5_exceptionHandling;

/**
 * @author Ilya Galatyuk
 */
public class MySpringException extends RuntimeException {
    private String exceptionMsg;

    public MySpringException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
