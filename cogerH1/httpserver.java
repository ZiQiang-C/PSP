
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class httpserver {

    public static void main(String[] args) {
        
        int puertoServer = Integer.parseInt(args[0]);
        String rutaNombre = args[1];

        System.out.println("Escuchando el puerto: "+puertoServer);

        try (
                ServerSocket socket = new ServerSocket(puertoServer);
            ) 
        {
            
            while (true) {

                Socket socketCliente = socket.accept();   
                System.out.println("El cliente |"+socketCliente.getInetAddress()+"| ha accedido");

                new Thread (()->{
                    recibePeticion(socketCliente, rutaNombre);
                }).start();
            }

        } catch (IOException x) {
            x.getStackTrace();
        };
    }

    public static void recibePeticion (Socket socketCliente, String rutaNombre) {

        try {
           
            //Instanciamos el objeto para que pueda acceder al archivo
            File rutaWeb = new File(rutaNombre);

            if (rutaWeb.exists()) {
                
                //Instanciamos el objeto capaz de leer lineas
                BufferedReader lineReader = new BufferedReader(new FileReader(rutaWeb));

                //Empezamos a crear el html a partir del archivo encontrado
                StringBuilder htmlConstructor = new StringBuilder();
                String linea;

                //Construirá la petición mientras existan líneas en el archivo
                while ((linea = lineReader.readLine()) != null) {
                    //Añade un salto de línea para que sea legible
                    htmlConstructor.append(linea+"\n");
                }
                lineReader.close();

                //Finalizamos almacenando el html en una variable
                linea = htmlConstructor.toString();

                //Creamos la respuesta con el contenido html
                String respuesta = "HTTP/1.1 200 OK\r\n" +
                                    "Content-type: text/html\r\n" +
                                    "Content-Length: "+linea.length() + "\r\n" +
                                    "\r\n" +
                                    linea;

                //Tras haber construido la respuesta, la devolvemos
                PrintWriter outHTTP = new PrintWriter(socketCliente.getOutputStream(), true);
                outHTTP.println(respuesta);
                

            } else {
                System.out.println("El archivo que ha solicitado el cliente: "+socketCliente.getInetAddress()+" no existe");
            }

            
        } catch (IOException x) {
            x.getStackTrace();
        }

    }

}