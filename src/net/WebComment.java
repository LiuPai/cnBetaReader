package net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by lilium on 13/07/31.
 */
public class WebComment implements Runnable {

    private String status;
    private Result result;
    private int pageID;
    private String sn;

    public WebComment(int pageID, String sn) {
        this.pageID = pageID;
        this.sn = sn;
    }

    public void run(){
        WebComment wc = getWebComment();
        if(wc.getStatus() == "success"){
        }
    }
    public WebComment getWebComment(){
        WebComment wc = null;
        try {
            URL commentURL = new URL("http://www.cnbeta.com/cmt?jsoncallback=1_0jQuery&op=info&page=1&sid=" + pageID + "&sn=" + sn);
            BufferedReader br = new BufferedReader(new InputStreamReader(commentURL.openStream()));
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            wc = gson.fromJson(br, WebComment.class);
        } catch (IOException e) {
            System.err.println("Error on geting Comment " + pageID + ".");
            e.printStackTrace();
        }
        return wc;
    }
    public WebComment(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }


    static class Result {
        private HashMap<String, ArrayList<ThreadIndex>> cmntdict;
        private ArrayList<CommentIndex> hotlist;
        private ArrayList<CommentIndex> cmntlist;
        private HashMap<String, Comment> cmntstore;
        private int comment_num;
        private int join_num;
        private String token;
        private int view_num;
        private int page;
        private int sid;
        private Collection u;

        public Result(HashMap<String, ArrayList<ThreadIndex>> cmntdict, ArrayList<CommentIndex> hotlist,
                      ArrayList<CommentIndex> cmntlist, HashMap<String, Comment> cmntstore, int comment_num, int join_num,
                      String token, int view_num, int page, int sid, Collection u) {
            System.out.println("parse Result");
            this.cmntdict = cmntdict;
            this.hotlist = hotlist;
            this.cmntlist = cmntlist;
            this.cmntstore = cmntstore;
            this.comment_num = comment_num;
            this.join_num = join_num;
            this.token = token;
            this.view_num = view_num;
            this.page = page;
            this.sid = sid;
            this.u = u;
        }

        static class ThreadIndex {
            private int tid;
            private int pid;
            private int sid;

            public ThreadIndex(int tid, int pid, int sid) {
                this.tid = tid;
                this.pid = pid;
                this.sid = sid;
            }
        }

        static class CommentIndex {
            private int tid;
            private int pid;
            private int sid;
            private String parent;
            private String thread;

            public CommentIndex(int tid, int pid, int sid, String parent, String thread) {
                this.tid = tid;
                this.pid = pid;
                this.sid = sid;
                this.parent = parent;
                this.thread = thread;
            }
        }

        static class Comment {
            private int tid;
            private int pid;
            private int sid;
            private String date;
            private String name;
            private String host_name;
            private String comment;
            private int score;
            private int reason;
            private int userid;
            private String icon;

            public Comment(int tid, int pid, int sid, String date, String name, String host_name, String comment, int score,
                           int reason, int userid, String icon) {
                this.tid = tid;
                this.pid = pid;
                this.sid = sid;
                this.date = date;
                this.name = name;
                this.host_name = host_name;
                this.comment = comment;
                this.score = score;
                this.reason = reason;
                this.userid = userid;
                this.icon = icon;
            }
        }
    }
}

