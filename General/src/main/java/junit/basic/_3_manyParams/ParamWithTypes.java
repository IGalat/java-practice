package junit.basic._3_manyParams;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * this allows to only do tests on corresponding types. Downside:
 * Much boilerplate code!
 * Much "ignored" in console thanks to failed Assume
 *
 * @author Ilya Galatyuk
 */

@RunWith(Parameterized.class)
public class ParamWithTypes {
    enum Type {ADD, MULTIPLY}

    @Parameterized.Parameter(0)
    public Type type;
    @Parameterized.Parameter(1)
    public int a;
    @Parameterized.Parameter(2)
    public int b;
    @Parameterized.Parameter(3)
    public int expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Type.MULTIPLY, 3, 2, 6},
                {Type.ADD, 23, 5, 28}
        });
    }

    @Test
    public void testAdd() {
        Assume.assumeTrue(type == Type.ADD);
        Assert.assertEquals(expected, Calc.add(a, b));
    }

    @Test
    public void testMultiply() {
        Assume.assumeTrue(type == Type.MULTIPLY);
        Assert.assertEquals(expected, Calc.multiply(a, b));
    }

    static class Calc {
        public static int add(int a, int b) {
            return a + b;
        }

        public static int multiply(int a, int b) {
            return a * b;
        }
    }
}
