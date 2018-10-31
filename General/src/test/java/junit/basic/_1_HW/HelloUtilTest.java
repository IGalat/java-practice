package junit.basic._1_HW;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Ilya Galatyuk
 */
public class HelloUtilTest {
    String message = "Hello world!";
    HelloUtil helloUtil;

    @Test
    public void testGetMessage() {
        helloUtil = new HelloUtil(message);
        assertEquals(message, helloUtil.getMessage());
    }


    //this is disabled because failed test doesn't allow maven to pack the project

//    @Test
//    public void testGetMessageFail() {
//        helloUtil = new HelloUtil(message);
//        assertEquals("This test should fail!", helloUtil.getMessage());
//    }
}
