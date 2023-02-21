import java.util.ArrayList;
import java.util.Random;
class Enfermero implements Runnable {
    private Hospital hospital;
    private String[] nombres = {"Juan", "Pedro", "Luisa", "Marta", "Ana"};

    public Enfermero(Hospital hospital) {
        this.hospital = hospital;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String paciente = nombres[random.nextInt(nombres.length)];
            hospital.agregarPaciente(paciente);
            try {
                Thread.sleep(1000); // Espera aleatoria entre pacientes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}