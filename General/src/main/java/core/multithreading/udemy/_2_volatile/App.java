package core.multithreading.udemy._2_volatile;

import java.util.Scanner;

/**
 * volatile keyword
 * <p/>
 * threads can(rarely) cache variables, and work incorrectly as a result.
 * volatile:
 * makes a variable not-caching, read and write to memory directly
 * <p/>
 * morale: always 'volatile' if variable can be used multi-thread
 */
public class App {
    public static void main(String[] args) {
        Processor p1 = new Processor();
        p1.start();

        System.out.println("Press enter to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        p1.shutdown();
    }
}

class Processor extends Thread {
    private volatile boolean running = true;


    @Override
    public void run() {
        while(running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
