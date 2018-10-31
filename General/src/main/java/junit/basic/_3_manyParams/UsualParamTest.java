package junit.basic._3_manyParams;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Ilya Galatyuk
 */

@RunWith(Parameterized.class)
public class UsualParamTest {
    // value is index of param. Needed to avoid  java.lang.Exception: @Parameter(0) is used more than once
    //@Parameter is unnecessary? but can be confusing without.
    @Parameterized.Parameter(value = 0)
    public int m1;
    @Parameterized.Parameter(value = 1)
    public int m2;
    @Parameterized.Parameter(2) //works without 'value = '
    public int result;

    //name is optional, should help identify tests. But doesn't?
    @Parameterized.Parameters(name = "{index}: (0)*{1} = {2}")
    //must be public static
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {1, 2, 2},
                {3, 5, 15},
                {121, 4, 484}
        };
        return Arrays.asList(data);
    }

    // single parameter case(not for this class, just as example):
//    @Parameterized.Parameters
//    public static Object[] dataSingleParam(){
//        Object[] data = new Object[]{1, 2, 3, 4};
//        return data;
//    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass");
    }

    @Before //is executed on each test run obviously
    public void before() {
        System.out.println("Before");
    }

    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        Assert.assertEquals(result, calc.multiply(m1, m2));
    }

    class Calculator {
        public int multiply(int a, int b) {
            return a * b;
        }
    }

}
