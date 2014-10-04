package net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import jdk.nashorn.internal.ir.Node;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.ErrorManager;
import jdk.nashorn.internal.runtime.Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by lilium on 13/07/30.
 */
public class Comment implements Runnable {
    private Date updateDate;
    private String commentStr;
    private URL commentURL;
    public Comment(int pageID, String sn){
        try {
            commentURL = new URL("http://www.cnbeta.com/cmt?jsoncallback=1_0jQuery&op=info&page=1&sid=" + pageID + "&sn=" + sn);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            updateDate = new Date(System.currentTimeMillis());
            BufferedReader br = new BufferedReader(new InputStreamReader(commentURL.openStream()));
            JsonReader jr = new JsonReader(br);
            jr.setLenient(true);
            jr.nextString();
            jr.beginObject();
            jr.nextString();
            jr.beginArray();
            while(jr.hasNext()){
                System.out.println(jr.nextString());
            }
            jr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseComment();
    }

    private void parseComment(){
        Gson gson = new Gson();
        gson.fromJson(commentStr,Object.class);
    }

    public static void main(String... args){
        new Comment(246600,"2398c").run();

    }

}