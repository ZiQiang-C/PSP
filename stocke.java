import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.BufferOverflowException;

public class stocke{
    public static String MENSAJE="mira\n";
    public static void main (String [] agrs){
        try {
            // 放IP
            Socket con = new Socket("192.168.20.202",8080);
            BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());

            out.write(MENSAJE.getBytes());
            out.close();
            con.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }    
    }
}