package junit.basic._2_assertsAndSuit;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * This class provides a set of assertion methods useful for writing tests. Only failed assertions are recorded.
 * <p/>
 * Assert usually is imported static.
 *
 * @author Ilya Galatyuk
 * @see @Rule - flexible addition/redefinition of the behaviour of each test method in class   https://github.com/junit-team/junit4/wiki/Rules
 * @see @RunWith - use custom <? extends Runner> for a test class.
 */

public class AssertShowcase {
    private int a = 2;
    private int b = 4;
    private int[] arr = new int[]{1, 2, 3};
    private String string;

    @Before
    public void setupPerTest() {
        System.out.println("This is executed before each test. Used for initialization, establishing connections etc.");
    }

    @After
    public void tearDownPerTest() {
        System.out.println("After each test. Used to close the connection or clean up activities.");
    }

    @BeforeClass //method must be static
    public static void setup() {
        System.out.println("This is executed before the first test. ---------");
    }

    @AfterClass //method must be static
    public static void tearDown() {
        System.out.println("After the last test. ---------");
    }


    @Test
    public void testEquality() {
        //message replaces standard AssertionError message. not too useful. not necessary duh
        assertEquals("some message1", 2, a);
        assertNotEquals(a, b);
        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    public void testSame() {
        int c = 4;
        //Checks that both variables refer to the same object/different objects
        assertSame(a, a);
        //assertNotSame(b, c); //with primitives works on values!
    }

    @Test(timeout = 100) // fails if takes longer than 100ms
    public void testTrueFalse() {
        assertTrue(/*"some message4",*/ a + 2 == 4);
        assertFalse("some message5", b == 100);
    }

    @Ignore("My reason for ignore") // test won't be executed with this
    @Test
    public void testNulls() {
        assertNull(string);
        assertNotNull(a);
    }

    @Test
    public void testAssume() {
        //if any assumption fails - test is ignored(but exception is thrown)
        Assume.assumeFalse(false);
        Assume.assumeTrue(true);
        //Assume.assumeNoException(); // dunno how this works
        Assume.assumeNotNull(string); //this causes test to be ignored

        //and after the assumptions test can begin
    }

    // Junit3 Approach for exceptions, don't use. See next method instead
    // Assert.fail() can be a crutch to remind something, but that's bad, mkay kids?
    @Test
    public void testFail() {
        try {
            arr[50] = 50; //this SHOULD throw 'out of bounds exception'
            fail("Program went here, while it shouldn't! (Exception not thrown)");
        } catch(IndexOutOfBoundsException e) {
            // test passed successfully
            // on a plus side, you can catch exceptions
        }
    }

    // this fails if exception isn't thrown during method execution.
    // But imprecise: any statement can throw it and it'll be ok
    // It can't? catch multiple exceptions, and shouldn't: imprecise test is bad test
    // Dunno if it can catch the exception though(previous method can)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        arr[50] = 50;
    }
}
