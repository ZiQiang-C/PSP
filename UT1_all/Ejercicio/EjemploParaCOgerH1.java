import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EjemploParaCOgerH1 {
    public static void main(String[] args) {
        String html = "<html><head><title>Título de la página</title></head><body><h1>Título de la sección</h1><p>Contenido de la sección</p></body></html>";
        Pattern pattern = Pattern.compile("<h1>(.*?)</h1>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String h1 = matcher.group(1);
            System.out.println(h1);
        }
    }
}