public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();

        // Crear threads para enviar trabajos de impresión
        Thread t1 = new Thread(new PrintJob(printer, "Trabajo 1"));
        Thread t2 = new Thread(new PrintJob(printer, "Trabajo 2"));
        Thread t3 = new Thread(new PrintJob(printer, "Trabajo 3"));

        // Iniciar threads
        t1.start();
        t2.start();
        t3.start();
    }
}