import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class sender {
    public static String MENSAJE="mira\n";
   
    public static void main (String [] agrs){
        String IP=agrs[0];
        int port=Integer.parseInt(agrs[1]);
        String MENSAJE=agrs[2];
        try {
            // 放IP
            Socket con = new Socket(IP,port);
            BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
            //读取 写的句子 发送给 服务器
            // java sender.java  句子
            
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
