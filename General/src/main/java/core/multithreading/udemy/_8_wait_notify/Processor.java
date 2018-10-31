package core.multithreading.udemy._8_wait_notify;

import java.util.Scanner;

/**
 * wait() and notify()  use
 * <p/>
 * when notify() tells other thread to wake up, control of the mutex still need to be acquired
 * <p/>
 * they have to be in synchronised block
 */
public class Processor {
    public void produce() throws InterruptedException {
        synchronized(this) {
            System.out.println("Producer thread running ...");
            wait();   // this gives away control of the lock(mutex) in 'synchronised' block
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized(this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed");

            notify();
            Thread.sleep(5000); //just to show that control is NOT relinquished, just other thread's 'wait' is notified
        }
    }
}
