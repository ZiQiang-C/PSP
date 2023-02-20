public class Cifrador {
    private static String abecedario = "abcdefghijklmnopqrstuvwxyz";
    public static final int ASCII_MAX=122;
    public static final int ASCII_MIN=96;

    public static void main(String[] args) {

        String cadena = args[0];
        int pasos = Integer.parseInt(args[1]);

        //String cadena = "pedro ";
        //int pasos=4;
        
        System.out.println("Cadenada recibida: "+cadena+" | El número de pasos es: "+pasos);

        
        
        //Utilizando variable abecedario y con bucles
        cifrarCadenaBucles(cadena, pasos);
        
        //Utilizando ASCII
        cifrarCadenaASCII(cadena, pasos);

    }

    public static void cifrarCadenaBucles (String cadena, int pasos) {
        
        String cadenaCifrada="";
        int usoPasos=0;

        for (int i = 0;i<cadena.length();i++) {
            usoPasos=pasos;

            for (int h = 0;h<abecedario.length();h++) {

                if (cadena.charAt(i)==abecedario.charAt(h)) {

                    for (int j = h;usoPasos>=0;j++) {
                        
                        if (j>=abecedario.length()) {
                            j=0;
                        }

                        if (usoPasos==0) {
                            cadenaCifrada+=abecedario.charAt(j);
                        }
                        
                        usoPasos--;
                    }

                }
            }

        }
        System.out.println("Cadena cifrada con bucles: "+cadenaCifrada);
    }

    public static void cifrarCadenaASCII(String cadena, int pasos) {

        String cadenaCifrada="";
        for (int i=0;i<cadena.length();i++) {
            if (cadena.charAt(i)+pasos>ASCII_MAX) {
                cadenaCifrada+=(char)(((cadena.charAt(i)+pasos)-ASCII_MAX)+ASCII_MIN);
            } else {
                cadenaCifrada+=(char)(cadena.charAt(i)+pasos);
            }
        }
        System.out.println("Cadena cifrada con bucles: "+cadenaCifrada);
    }


}
