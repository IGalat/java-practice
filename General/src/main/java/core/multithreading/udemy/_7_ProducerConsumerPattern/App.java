package core.multithreading.udemy._7_ProducerConsumerPattern;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer-Consumer pattern
 * <p/>
 * BlockingQueue class: data structure, FIFO, thread-safe
 * <p/>
 * pattern allows creating thread-safe FIFO? no
 */
public class App {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    private static void producer() throws InterruptedException {
        Random random = new Random();

        while(true) {
            queue.put(random.nextInt(100)); // 0-99
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();

        while(true) {
            Thread.sleep(100);

            if(random.nextInt(10) == 0) {
                Integer value = queue.take(); //  .put/.take wait if queue full/empty

                System.out.println("Taking value " + value + "; Queue size is " + queue.size());
            }
        }
    }
}
