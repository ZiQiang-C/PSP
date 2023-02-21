package com.example;

/**
 * Hello world!
 *
 */
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
public class App 
{
    
    public static final int NOMBRE_AUTH=0;
    public static final int NOMBREP_AUTH=1;
    public static final int CORREO_AUTH=2;
    public static Pattern pattern = Pattern.compile("<h1.*?>(.*?)</h1>");
    public static Matcher matcher;
    public static String MENSAGE="";
    public static String todo="";
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner dato=new Scanner(System.in);
         //String Host=args[0];
         //String CORREO=args[1];
         System.out.println("una direcion web");
        String Host=dato.next();
        dato.nextLine();
        System.out.println("una direccion email");
        String CORREO=dato.next();
         try {
            todo=Informa(Host);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            
            //Instanciamos
            Email email = new SimpleEmail();

            //Eliges el sitio, puerto
            email.setHostName("smtp.educa.madrid.org");
            email.setSmtpPort(587);

            //Te autorizas, no se deben cifrar los datos
            //Utilizo la clase Sp para extraer datos de un documento con las credenciales
            //Retorna un ArrayList con los datos
            email.setAuthentication(Sp.getPalabra().get(NOMBRE_AUTH), Sp.getPalabra().get(NOMBREP_AUTH));
            email.setStartTLSEnabled(true);
            System.out.println(Sp.getPalabra().get(NOMBRE_AUTH)+ Sp.getPalabra().get(NOMBREP_AUTH));
            //Correo emisor
            email.setFrom(Sp.getPalabra().get(CORREO_AUTH));
            
            //Subtitulo y mensaje
            
            email.setSubject("test a H1");
            email.setMsg("test a H1"+todo);

            //receptor y método de envío
            email.addTo(CORREO);
            email.send();

            System.out.println("Email sent!");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
    public static String Informa(String Host) throws Exception{
            SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            Socket socket = sslsocketfactory.createSocket(Host, 443);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET / HTTP/1.1");
            //out.println("GET " +DIR+" HTTP/1.1");
            out.println("Host: "+Host);
            out.println();
            out.flush();
            

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    MENSAGE=matcher.group()+"\n";
                }
            }
            
            in.close();
            out.close();
            socket.close();
            return MENSAGE;
    }
}
