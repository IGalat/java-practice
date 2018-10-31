package junit.basic._1_HW;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unnecessary class.
 * duplicate of class HelloUtilTest(in test folder) just for the sake of tutorial following
 * <p/>
 * However: The Maven build system (via its surefire plug-in) automatically includes
 * classes with "*Test" name in its test scope.
 *
 * @author Ilya Galatyuk
 */
public class HelloUtilTestMisplaced {
    String message = "Hello world!";
    HelloUtil helloUtil;

    @Test
    public void testGetMessage() {
        helloUtil = new HelloUtil(message);
        assertEquals(message, helloUtil.getMessage());
    }


    //Maven doesn't run this test on test phase, so failure doesn't matter

    @Test
    public void testGetMessageFail() {
        helloUtil = new HelloUtil(message);
        assertEquals("This test should fail!", helloUtil.getMessage());
    }
}
