class MyThread implements Runnable {
    public void run() {
        System.out.println("Thread running");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread.getName());
        System.out.println(mainThread.getPriority());
        
        mainThread.setName("main wala thread");
        System.out.println(mainThread.getName());
        System.out.println(mainThread.getPriority());
        
        try {
            mainThread.setDaemon(true);
            System.out.println(mainThread.isDaemon());
        } catch (IllegalThreadStateException e) {
            System.out.println("Cannot set main thread to daemon ");
        }
        
        Thread t = new Thread(new MyThread());
        System.out.println(t.getName());
        System.out.println(t.getPriority());
        
        t.setName("thread 1");
        t.setPriority(3);
        System.out.println(t.getName());
        System.out.println(t.getPriority());
        t.start();
        
        Thread t1 = new Thread(new MyThread());
        System.out.println(t1.getName());
        System.out.println(t1.getPriority());
        
        t1.setName("thread 2");
        t1.setPriority(6);
        System.out.println(t1.getName());
        System.out.println(t1.getPriority());
        System.out.println(t1.isAlive());
        System.out.println(t1.isDaemon());
        t1.setDaemon(true);
        System.out.println(t1.isDaemon());
        t1.start();
        System.out.println("alive " + t1.isAlive());

    }
}