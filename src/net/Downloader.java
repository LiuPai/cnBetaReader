package net;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Stream;

/**
 * Created by lilium on 13/07/28.
 */

public class Downloader implements Runnable {
    private URLConnection con;
    private URL url;
    private String dst;
    private InputStream is;
    private OutputStream os;
    private int timeoutMillis = 3000;

    public Downloader(String url,String dst){
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.dst = dst;
    }
    @Override
    public void run() {
        try {
            con = url.openConnection();
            con.setConnectTimeout(timeoutMillis);
            is = con.getInputStream();
            os = new FileOutputStream(new File("img/" + dst));
            byte[] buffer = new byte[2048];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}