package core.multithreading.udemy._6_CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch class
 * allows countdown for multiple threads, is thread-safe. Apparently very useful?
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 6; i++) {
            executorService.submit(new Processor(latch));
        }

        latch.await();

        System.out.println("Completed");
    }
}

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started");

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
    }
}
