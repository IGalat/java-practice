package junit.basic._5_categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Ilya Galatyuk
 */

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Suite.SuiteClasses({One.class, Two.class})
public class SlowTestSuite {
    // will run One.b and Two.c, but not One.a
}
