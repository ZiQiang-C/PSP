package ClienteUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
    public static void main(String[] args){
        try{
            DatagramSocket ds =new DatagramSocket();
            byte buffer[]="Hola mundo jamonnnnnnnnnnnnnn 2\n".getBytes();
            String ip="192.168.20.255";
            ds.setBroadcast(true);
            DatagramPacket p=new DatagramPacket(
                buffer,
                buffer.length,
                InetAddress.getByName(ip),
                4321
            );
            // codigo send es para enviar mensage
            ds.send(p); 
            ds.close();
            //System.out.println(new String(p.getData(),0,p.getLength()));
        }catch(SocketException e){
            e.printStackTrace();
        }catch( UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
