import java.util.ArrayList;
import java.util.Random;

class Hospital {
    private ArrayList<String> pacientes;

    public Hospital() {
        pacientes = new ArrayList<String>();
    }

    public synchronized void agregarPaciente(String paciente) {
        pacientes.add(paciente);
        System.out.println(paciente + " ha llegado al hospital.");
        notifyAll();
    }

    public synchronized String atenderPaciente() throws InterruptedException {
        while (pacientes.isEmpty()) {
            wait();
        }
        String paciente = pacientes.remove(0);
        System.out.println("El paciente " + paciente + " está siendo atendido.");
        return paciente;
    }
}

