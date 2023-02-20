import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExpleca3 {
    private static String MENSAJE="Bienvenido mira";
   
    public static void main(String [] agrs){
        ServerSocket server;
       int port=Integer.parseInt(agrs[0]);
       String Mensage=agrs[1]+"\n";
        try {
            

            server=new ServerSocket(port);
            //escucha cuando tiene cosa que volver
            Socket connCliente= server.accept();
            /*cuando se conecta un cliente le mandamos el mensaje */
            DataOutputStream out =new DataOutputStream(connCliente.getOutputStream());
                System.out.println("escuchar servidor");
                out.write(Mensage.getBytes());
               out.flush();
            /*leer el mensaje que nos manda y escribirlo por pantalla */
               DataInputStream input=new DataInputStream(connCliente.getInputStream());
          
            System.out.println("Cliente nos manda:"+input.readUTF());
            out.close();
            connCliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
