package junit.basic._5_categories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Ilya Galatyuk
 */

public class One {
    @Test
    public void a() {
        Assert.fail();
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
    }
}
