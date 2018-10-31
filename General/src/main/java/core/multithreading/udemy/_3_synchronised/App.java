package core.multithreading.udemy._3_synchronised;

/**
 * synchronised keyword
 * <p/>
 * every method has an intrinsic lock(or monitor lock, or mutex)
 * without 'synchronised' method can be run concurrently by threads
 * with 'synchronised' intrinsic lock of method is owned by thread that runs it, and after is released
 * if method is run by thread1 already, and thread2 wants to run it, it will have to wait for thread1 to finish executing method
 * 'synchronised' also makes threads see real(not cached) state of variables that are used in method, = volatile
 * <p/>
 * morale: 'synchronised' = concurrent running of method by multiple threads is impossible + all variables in method as if 'volatile'
 */
public class App {
    private int count = 0;

    public static void main(String[] args) {
        App app = new App();
        app.doWork();


    }

    private synchronized void incrementCount() {
        count++;
    }

    private void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000000; i++) {
                    incrementCount();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000000; i++) {
                    incrementCount();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();                //IMPORTANT: join makes parent thread wait until child thread is finished
            t2.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is " + count);
    }
}
