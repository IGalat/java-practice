package junit.basic._2_assertsAndSuit;

import junit.framework.TestCase;

/**
 * WARNING!
 * DEPRECATED: TestCase is used in JUnit3, and left bor compatibility. Don't use.
 * <p/>
 * A test case defines the fixture to run multiple tests.
 *
 * @author Ilya Galatyuk
 */
public class TestCaseShowDEPRECATED extends TestCase {

    // @Test  - annotations is JUnit4 style, while 'extends TestCase' JUnit3
    public void testAdd() {
        //count the number of test cases
        System.out.println("No of Test Case = " + this.countTestCases());

        //test getName
        String name = this.getName();
        System.out.println("Test Case Name = " + name);

        //test setName
        this.setName("testNewAdd");
        String newName = this.getName();
        System.out.println("Updated Test Case Name = " + newName);
    }

}
