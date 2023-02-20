import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Parametros {
    public static String MENSAJE="mira\n";
    public static void main (String [] agrs){
       for(String s: agrs){
        System.out.print(String.format("\tParametro: %s", s));
       }   
    }
}
