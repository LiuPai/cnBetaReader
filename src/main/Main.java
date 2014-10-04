package main;

import local.Database;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lilium on 13/08/01.
 */
public class Main {
    private static final String DATABASE_NAME = "data.db";
    public static final String HOME = System.getProperties().getProperty("user.home") + "/.local/share/cnBeta";

    public static void main(String... args){
        new Main();
    }
    public Main(){
        getDB();

    }
    private int getLastPageID(){
        int lastPageID = 0;
        try {
            Document doc = Jsoup.parse(new URL("http://www.cnbeta.com"), 3000);
            lastPageID = Integer.valueOf(doc.select("dl[data-sid").first().attr("data-sid"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastPageID;
    }

    private Database getDB(){
        File home = new File(HOME);
        Path homePath = Paths.get(HOME);

        if(!home.exists())
            try {
                Files.createDirectories(homePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        File database = new File(home,DATABASE_NAME);
        Database db;
        if(!database.exists()){
            db = new Database(database.getPath());
        }else {
            db = new Database(database.getPath());
        }
        return db;
    }

}
