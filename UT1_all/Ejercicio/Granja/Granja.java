import java.util.concurrent.Semaphore;

public class Granja {
    public static void main(String[] args) {
        // Creamos un semáforo para controlar el acceso al recipiente de comida
        Semaphore semaforo = new Semaphore(1);

        // Creamos los animales como threads
        animales[] animales = { new animales("Gallina", semaforo), new animales("Cerdo", semaforo),
                              new animales("Vaca", semaforo), new animales("Oveja", semaforo) };

        // Iniciamos cada animal
        for (animales animal : animales) {
            animal.start();
        }
    }
}

class animales extends Thread {
    private String nombre;
    private Semaphore semaforo;

    public animales(String nombre, Semaphore semaforo) {
        this.nombre = nombre;
        this.semaforo = semaforo;
    }

    public void run() {
        System.out.println(nombre + " ha nacido");

        for (int i = 1; i <= 5; i++) {
            mover();
            hacerSonido();
        }

        System.out.println(nombre + " tiene hambre");

        // Intentamos acceder al recipiente de comida
        try {
            semaforo.acquire();
            comer();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            semaforo.release();
        }

        System.out.println(nombre + " se va a dormir");
    }

    private void mover() {
        System.out.println(nombre + " se está moviendo");
    }

    private void hacerSonido() {
        System.out.println(nombre + " está haciendo ruido");
    }

    private void comer() {
        System.out.println(nombre + " está comiendo");
    }
}
