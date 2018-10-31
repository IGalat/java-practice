package junit.basic._2_assertsAndSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * JUnit4-style test suite. (JUnit3-style isn't shown in this package)
 *
 * @author Ilya Galatyuk
 */


@RunWith(Suite.class) // needed? dunno
@Suite.SuiteClasses({ // list of classes to be tested
        AssertShowcase.class
})
public class MyTestSuite {
}
