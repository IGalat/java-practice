package tutorialsPoint._14_AOP.xmlBased;

public class Logging {

    public void beforeAdvice() {
        System.out.println("beforeAdvice() Going to setup student profile.");
    }

    public void afterAdvice() {
        System.out.println("afterAdvice() Student profile has been setup.");
    }

    /**
     * This is the method which I would like to execute
     * when any method returns.
     */
    public void afterReturningAdvice(Object retVal) {
        System.out.println("afterReturningAdvice(Object retVal) Returning:" + retVal.toString());
    }

    /**
     * This is the method which I would like to execute
     * if there is an exception raised.
     */
    public void afterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("afterThrowingAdvice(IllegalArgumentException ex) There has been an exception: " + ex.toString());
    }
}