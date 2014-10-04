package net;


import com.sun.media.jfxmedia.control.VideoFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lilium on 13/07/27.
 */
public class WebPage {
    private int pageID;
    private String title;
    private String date;
    private String source;
    private String introduction;
    private String content;
    private String sn;
    private Comment comment;
    private int topic;
    private int view;
    private Date updateDate;
    private boolean storeImg = false;
    private String author;
    public static final ParallelManager manager = new ParallelManager();

    private String baseURI = "http://www.cnbeta.com/articles/";
    private BufferedReader bufferedReader;

    public WebPage(int pageID){
        this.pageID = pageID;
        try {
            Document html = Jsoup.parse(new URL(baseURI + pageID + ".htm"),3000);
            extractData(html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractData(Document html) {
        title = html.select("h2[id=news_title]").first().ownText();
        date = html.select("span[class=date]").first().ownText();
        topic = Integer.parseInt(html.select("a[id=sign]").first().attr("alt"));
        Element where = html.select("span[class=where]").first();
        if(where.children().size() == 1){
            source = where.child(0).toString();
        } else {
            source = where.ownText().substring(where.ownText().indexOf("：")+1);
        }
        introduction = html.select("div[class=introduction]").first().select("p").first().html();
        if(storeImg)
            parseContent(html.select("div[class=content]").get(1));
        String htmlStr = html.toString();
        int startLinePoint = htmlStr.indexOf("SN:\"")+4;
        sn = htmlStr.substring(startLinePoint,startLinePoint + 5);
        comment = new Comment(pageID,sn);
        manager.submit(comment);
    }

    private void parseContent(Element content) {
        Elements imgs = content.select("img[src]");
        for(Element img : imgs){
            String url = img.attr("src");
            String extension = url.substring(url.lastIndexOf('.'));
            UUID uuid = UUID.randomUUID();
            String dst = pageID + "_" + uuid.toString() + extension;
            manager.submit(url,dst);
            img.attr("src",   "/img/" + dst);
        }
        this.content = content.toString();
    }

    public void storeData(){

    }
}
