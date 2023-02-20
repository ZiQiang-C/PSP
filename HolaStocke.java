import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HolaStocke {
    
    private static String MENSAJE="Bienvenido mira";
   
    public static void main(String [] agrs){
       String ip =agrs[0];
        int port=Integer.parseInt(agrs[1]);
        String msg= agrs[2]+"\n";
        try {
            


            //escucha cuando tiene cosa que volver
            Socket connCliente= new Socket(ip,port);

            DataOutputStream out =new DataOutputStream(connCliente.getOutputStream());
            DataInputStream input =new DataInputStream(connCliente.getInputStream());
                out.writeUTF(msg);
                out.flush();
                System.out.println("Cliente nos manda:"+input.readUTF());
                
            input.close();
            out.close();
            connCliente.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
