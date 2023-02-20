import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HolaServer {
    
    private static String MENSAJE="Bienvenido mira";
   
    public static void main(String [] agrs){
       
        try {
            

            ServerSocket server=new ServerSocket(8080);
            //escucha cuando tiene cosa que volver
            Socket connCliente= server.accept();

            BufferedOutputStream out =new BufferedOutputStream(connCliente.getOutputStream());
            
                out.write(MENSAJE.getBytes());
               
            
            out.close();
            connCliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
