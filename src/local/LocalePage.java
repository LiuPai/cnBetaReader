package local;

import java.sql.*;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lilium on 13/07/30.
 */
public class LocalePage {
    private int pageID;
    private String title;
    private String date;
    private String source;
    private String introduction;
    private String content;
    private String sn;
    private String comment;
    private int topic;
    private int view;
    private Date updateDate;

    public LocalePage(int pageID){
        this.pageID = pageID;
    }

}
