import java.util.Scanner;

public class MessageCommunication {
    public static void main(String[] args) {
        // Creamos el objeto MessageQueue compartido por los threads
        MessageQueue queue = new MessageQueue();
        
        // Creamos y ejecutamos el thread de envío
        SenderThread sender = new SenderThread(queue);
        sender.start();
        
        // Creamos y ejecutamos el thread de recepción
        ReceiverThread receiver = new ReceiverThread(queue);
        receiver.start();
    }
}

// Clase que representa la cola de mensajes compartida por los threads
class MessageQueue {
    private String message;
    private boolean hasMessage;
    
    public synchronized void putMessage(String message) {
        while (hasMessage) {
            try {
                wait(); // Esperamos hasta que el thread de recepción procese el mensaje anterior
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.message = message;
        hasMessage = true;
        notifyAll(); // Notificamos al thread de recepción que hay un mensaje disponible
    }
    
    public synchronized String getMessage() {
        while (!hasMessage) {
            try {
                wait(); // Esperamos hasta que el thread de envío envíe un mensaje
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String message = this.message;
        hasMessage = false;
        notifyAll(); // Notificamos al thread de envío que podemos enviar otro mensaje
        return message;
    }
}

// Clase que representa el thread de envío
class SenderThread extends Thread {
    private MessageQueue queue;
    
    public SenderThread(MessageQueue queue) {
        this.queue = queue;
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Escribe un mensaje: ");
            String message = scanner.nextLine();
            queue.putMessage(message);
        }
    }
}

// Clase que representa el thread de recepción
class ReceiverThread extends Thread {
    private MessageQueue queue;
    
    public ReceiverThread(MessageQueue queue) {
        this.queue = queue;
    }
    
    public void run() {
        while (true) {
            String message = queue.getMessage();
            System.out.println("Mensaje recibido: " + message);
        }
    }
}
