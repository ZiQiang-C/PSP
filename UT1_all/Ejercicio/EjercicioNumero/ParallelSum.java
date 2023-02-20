
import java.util.Random;

public class ParallelSum {

    public static void main(String[] args) throws InterruptedException {
        int[] array = generateArray(20);
        int numThreads = 4;
        int chunkSize = array.length / numThreads;

        Thread[] threads = new Thread[numThreads];
        SumThread[] sumThreads = new SumThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            sumThreads[i] = new SumThread(array, i * chunkSize, (i + 1) * chunkSize);
            threads[i] = new Thread(sumThreads[i]);
            threads[i].start();
        }

        // threads todos arrays que ya ejecutar 
        for (int i = 0; i < numThreads; i++) {
            
            threads[i].join();
        }
        //suma numero
        int totalSum = 0;
        for (int i = 0; i < numThreads; i++) {
            totalSum += sumThreads[i].getSum();
            System.out.println(sumThreads[i].getSum());
        }

        System.out.println("Total sum: " + totalSum);
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(5);
        }
        return array;
    }
}
