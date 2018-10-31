package junit.basic._3_manyParams;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * not using Parametrized runner, just cycle through params. IMO better than Parametrized
 *
 * @author Ilya Galatyuk
 */
public class Programmatic {
    private Calc calc;

    @Before
    public void init() {
        calc = new Calc();
    }

    @After
    public void close() {
        calc = null;
    }

    @Test
    public void testAdd() {
        int[][] testSets = new int[][]{
                {1, 2, 3},
                {5, 5, 10},
                {8, 8, 16}
        };

        for(int[] set : testSets)
            Assert.assertEquals(set[2], calc.add(set[0], set[1]));
    }

    @Test
    public void testMultiply() {
        int[][] testSets = new int[][]{
                {1, 2, 2},
                {5, 5, 25},
                {8, 8, 66}
        };

        for(int[] set : testSets) {
            //failMessage helps identify test that failed
            String failMessage = MessageFormat.format("testMultiply: {0}*{1}", set[0], set[1]);
            Assert.assertEquals(failMessage, set[2], calc.multiply(set[0], set[1]));
        }
    }


    class Calc {
        public int add(int a, int b) {
            return a + b;
        }

        public int multiply(int a, int b) {
            return a * b;
        }
    }
}
