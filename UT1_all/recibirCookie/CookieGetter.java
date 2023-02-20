import java.net.*;
import java.io.*;
import java.util.*;

public class CookieGetter {
    public static void main(String[] args) throws Exception {
        // URL de inicio de sesión y datos de la cuenta
        String loginUrl = "https://aulavirtual33.educa.madrid.org/ies.juandelacierva.madrid/login/index.php";
        String username = "ziqiang.chen";
        String password = "Zq65519432";

        // Configurar la conexión HTTP
        URL url = new URL(loginUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Enviar datos de inicio de sesión
        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        String data = "username=" + username + "&password=" + password;
        writer.write(data);
        writer.flush();
        writer.close();
        outputStream.close();
        
        // Leer las cabeceras de respuesta y buscar las cookies
        List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

        // Imprimir todas las cookies toas
        if (cookies != null) {
            for (String cookie : cookies) {
                System.out.println("Cookie: " + cookie);
            }
        }
    }
}

