import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExpleca {
    private static String MENSAJE="Bienvenido mira";
   
    public static void main(String [] agrs){
        ServerSocket server;
       int port=Integer.parseInt(agrs[0]);
       String Mensage=agrs[1];
        try {
            

            server=new ServerSocket(port);
            //escucha cuando tiene cosa que volver
            Socket connCliente= server.accept();

            BufferedOutputStream out =new BufferedOutputStream(connCliente.getOutputStream());
            
                out.write(Mensage.getBytes());
               
              
               
            
            out.close();
            connCliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
