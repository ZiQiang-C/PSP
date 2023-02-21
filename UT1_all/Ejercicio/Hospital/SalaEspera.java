public class SalaEspera {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Thread enfermero = new Thread(new Enfermero(hospital));
        Thread doctor1 = new Thread(new Doctor(hospital));
        Thread doctor2 = new Thread(new Doctor(hospital));
        enfermero.start();
        doctor1.start();
        doctor2.start();
    }
}