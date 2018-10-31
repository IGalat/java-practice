package core.multithreading.udemy._4_multipleLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * multiple locks
 * <p/>
 * 'synchronised' can be used on a fragment of code with object in it(best practise - use object created just for this)
 * then only this part of code will be locked when thread1 runs it, not all of the object, and thread2 can access another part of object
 */
public class Worker {
    private static Random random = new Random();

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private static List<Integer> list1 = new ArrayList<Integer>();
    private static List<Integer> list2 = new ArrayList<Integer>();

    private static void stage1() {
        synchronized(lock1) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(random.nextInt(100));
        }
    }

    private static void stage2() {
        synchronized(lock2) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public static void process() {
        for(int i = 0; i < 1000; i++) {
            stage1();
            stage2();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting:");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + ", List2: " + list1.size());
    }
}
