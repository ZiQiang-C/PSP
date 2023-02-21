import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {
    private Lock lock = new ReentrantLock();

    public void print(String message) {
        lock.lock();
        try {
            System.out.println("Imprimiendo: " + message);
            Thread.sleep(new Random().nextInt(1000)); // Espera aleatoria
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}




