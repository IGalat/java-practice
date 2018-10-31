package junit.basic._5_categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Ilya Galatyuk
 */

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Categories.ExcludeCategory(FastTests.class)
@Suite.SuiteClasses({One.class, Two.class})
public class SlowTestsOnlySuite {
    //Will run One.b, but not One.a or Two.c
}
