package net;


import java.sql.ResultSet;
import java.util.concurrent.*;

/**
 * Created by lilium on 13/07/28.
 */
public class ParallelManager {
    private static ExecutorService executorService;
    private static int threadPoolNum = 10;
    private int timeout;
    private boolean shutDown = false;
    public ParallelManager(){
        executorService = Executors.newFixedThreadPool(threadPoolNum);
    }
    public static void submit(String url, String dst){
        executorService.submit(new Downloader(url,dst));
    }
    public static void submit(Runnable task){
        executorService.submit(task);
    }
}