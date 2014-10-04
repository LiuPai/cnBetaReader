import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lilium on 13/07/28.
 */
public class Video {
    public static void main(String... args) throws MalformedURLException {
        URL url = new URL("http://player.youku.com/player.php/sid/XNTg4MjIwOTA4/v.swf");
        String filename = url.getFile();
        String extension = filename.substring(filename.lastIndexOf('.'));
        System.out.println(extension);
    }
}
