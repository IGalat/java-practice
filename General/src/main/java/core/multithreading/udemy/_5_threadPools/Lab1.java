package core.multithreading.udemy._5_threadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Galatyuk Ilya
 */
public class Lab1 {
    private static final int THREADS = 1000;

    public static double min = Double.MAX_VALUE;
    public static double sum = 0;
    public static final double PI = Math.PI;
    public static final double step = 0.000001;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        double[][] intervals = new double[][]{{-Math.PI / 4, -Math.PI / 5}, {-Math.PI / 10, Math.PI / 10}, {Math.PI, Math.PI * 5 / 7}};

        for (int i = 0; i < 2; i++) {
            for (double j = intervals[i][0]; j < intervals[i][1]; j = j + step) {
                executor.submit(new FormulaCalc(j));
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Min : " + min);
        System.out.println("Sum: " + sum);
    }
}

class FormulaCalc implements Runnable {
    private double x;

    FormulaCalc(double x) {
        this.x = x;
    }

    @Override
    public void run() {
        double fx = f(x);
        double stepUp = f(x + Lab1.step);
        double s = Lab1.step * ((fx + stepUp) / 2);

        if (Math.abs(s) < Lab1.min)
            Lab1.min = s;

        Lab1.sum += s;
    }

    private double f(double x) {
        return Math.sin(x);
    }
}
