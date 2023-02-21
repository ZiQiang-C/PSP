import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class PrintJob implements Runnable {
    private Printer printer;
    private String message;

    public PrintJob(Printer printer, String message) {
        this.printer = printer;
        this.message = message;
    }

    public void run() {
        while (true) {
            try {
                printer.print(message);
                Thread.sleep(new Random().nextInt(1000)); // Espera aleatoria
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
