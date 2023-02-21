import java.util.ArrayList;
import java.util.List;

public class PrimesCalculator {
    
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 1000;
    private static final int NUM_THREADS = 4;
    
    private static int rangeSize;
    
    public static void main(String[] args) throws InterruptedException {
        
        rangeSize = (UPPER_BOUND - LOWER_BOUND) / NUM_THREADS;
        List<Integer> primes = new ArrayList<>();
        Thread[] threads = new Thread[NUM_THREADS];
        
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = LOWER_BOUND + i * rangeSize;
            int end = start + rangeSize;
            threads[i] = new Thread(new PrimeFinder(start, end, primes));
            threads[i].start();
        }
        
        for (Thread t : threads) {
            t.join();
        }
        
        System.out.println("Prime numbers between 1 and 1000:");
        System.out.println(primes.toString());
    }
    
    private static class PrimeFinder implements Runnable {
        
        private final int start;
        private final int end;
        private final List<Integer> primes;
        
        public PrimeFinder(int start, int end, List<Integer> primes) {
            this.start = start;
            this.end = end;
            this.primes = primes;
        }
        
        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
        }
        
        private boolean isPrime(int n) {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
