package ChatUDP;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ChatUDPServer {

    public static final int MAX_LENGTH = 63535;
    public static final String MENSALE_SALIR = "bye";

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int port = Integer.parseInt(args[0]);

        //Variables información del cliente
        int portCliente;
        InetAddress addressCliente;

        //Buffer utilizados para enviar / recibir mensajes
        byte bufferServer[] = new byte[MAX_LENGTH];
        byte bufferCliente[] = new byte[MAX_LENGTH];

        try (DatagramSocket ds = new DatagramSocket(port)) {

            String msgCliente="",mensajeServer="";
            while (!msgCliente.equalsIgnoreCase(MENSALE_SALIR) || mensajeServer.equalsIgnoreCase(MENSALE_SALIR)) {


                //Recibe el mensaje del cliente y lo muestra en pantalla
                DatagramPacket p = new DatagramPacket(bufferServer,bufferServer.length);
                ds.receive(p);
                msgCliente = new String (p.getData(),0,p.getLength()).replace("\n","");
                System.out.println(msgCliente);
                

                //Envia mensajes al cliente
                mensajeServer = sc.nextLine()+"\n";
                bufferCliente = mensajeServer.getBytes();

                //Recibimos información del cliente
                portCliente = p.getPort();
                addressCliente = p.getAddress();

                //Creamos el nuevo paquete y los enviamos
                p = new DatagramPacket(bufferCliente, bufferCliente.length, addressCliente, portCliente);
                ds.send(p);

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
