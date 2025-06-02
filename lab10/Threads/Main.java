class SumThread extends Thread {
    private int[] array;
    private int start;
    private int end;
    private long sum;
    
    public SumThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }
    
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }
    
    public long getSum() {
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1; 
        }
        
        SumThread[] threads = new SumThread[10];
        
        for (int i = 0; i < 10; i++) {
            int start = i * 1000;
            int end = start + 1000;
            threads[i] = new SumThread(array, start, end);
            threads[i].start();
        }
        
        long totalSum = 0;
        try {
            for (int i = 0; i < 10; i++) {
                threads[i].join();
                totalSum += threads[i].getSum();
                System.out.println("Thread " + i + " sum: " + threads[i].getSum());
            }
        } catch (InterruptedException e) {
            System.err.println("thread interrupted: " + e.getMessage());
        }
        
        System.out.println("Total sum: " + totalSum);
    }
}