class Doctor implements Runnable {
    private Hospital hospital;

    public Doctor(Hospital hospital) {
        this.hospital = hospital;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                String paciente = hospital.atenderPaciente();
                Thread.sleep(2000); // Tiempo de atención aleatorio
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}