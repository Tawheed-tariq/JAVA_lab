class Counter {
    private int c = 0; 

    public synchronized void inc() {
        c++;
    }

    public synchronized int get() {
        return c;
    }
}

class MyThread implements Runnable {
    private Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.inc();
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Counter cnt = new Counter();

        Thread t1 = new Thread(new MyThread(cnt));
        Thread t2 = new Thread(new MyThread(cnt));

        t1.start();
        System.out.println(cnt.get());
        t2.start();

        System.out.println(cnt.get());

        try {
            t1.join();
            System.out.println(cnt.get());
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("exception");
        }

        System.out.println("Counter: " + cnt.get());
    }
}
