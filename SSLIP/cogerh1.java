package SSLIP;
	import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class cogerh1 {
  public static String Host="www.trxs.cc";
  public static String DIR="/tongren/";
  public static Pattern pattern = Pattern.compile("<h1.*?>(.*?)</h1>");
  public static Matcher matcher;
  public static void main(String[] args) throws Exception {
    
    SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    Socket socket = sslsocketfactory.createSocket(Host, 443);

    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    //out.println("GET / HTTP/1.1");
    out.println("GET " +DIR+" HTTP/1.1");
    out.println("Host: "+Host);
    out.println();
    out.flush();
    
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null) {
      matcher = pattern.matcher(inputLine);
      while (matcher.find()) {
        System.out.println(matcher.group());
      }
    }
    in.close();
    out.close();
    socket.close();
  }
}
