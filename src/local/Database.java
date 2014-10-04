package local;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

/**
 * Created by lilium on 13/08/01.
 */
public class Database {
    private static String driverName = "org.sqlite.JDBC";
    private static String dbms = "jdbc:sqlite:";
    private String dbPath;
    private Connection conn;
    private Statement stmt;
    private DatabaseMetaData dbmd;
    private static final String TABLE_PAGE = "page";
    private static final String TABLE_COMMENT = "comment";
    private static final String TABLE_PAGE_VAR = "page_var";
    private static final String TABLE_COMMENT_VAR = "comment_var";
    private static final String TABLE_SYSTEM = "system";
    private static Database database;

    public Database(String dbPath) {
        this.dbPath = dbPath;
        startConnection();
    }

    public Database getInstance(){
        if(database == null){
            database = new Database("./database");
        }
        return database;
    }

    public DatabaseMetaData getMeteData(){
        return dbmd;
    }

    public void startConnection() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver: " + driverName + " was not available");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(dbms + dbPath);
            stmt = conn.createStatement();
            dbmd = conn.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        if (stmt!=null) { try { stmt.close(); } catch (Exception ignore) {} }
        if (conn!=null) { try { conn.close(); } catch (Exception ignore) {} }
    }

    public boolean hasTable(String table){
        try {
            ResultSet rs = dbmd.getTables(null,null,table,null);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JdbcRowSet excuteQuery(String sql,int size,int timeout){
        JdbcRowSet jrs = null;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            jrs = new JdbcRowSetImpl(rs);
            jrs.setFetchSize(size);
            jrs.setQueryTimeout(timeout);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jrs;
    }

    public void excute(String sql){
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("SQL_Instruction: " + sql + " was faulted.");
            e.printStackTrace();
        }
    }

    public void createTables(){
        String createSystem = "";

        String createPage = "CREATE TABLE " + TABLE_PAGE + " ( sid INT UNSIGNED,title VARCHAR(255),"
                +"date DATETIME,introduction VARCHAR(255),content TEXT,sn CHARACTER(5))";

        String createPageVar = "CREATE TABLE " + TABLE_PAGE_VAR + " ( sid INTEGER,comment_num SMALLINT UNSIGNED,"
                +"mark BOOLEAN,score SMALLINT,";

        /*String createComment = TABLE_COMMENT + " ( tid INT UNSIGNED,pid INT UNSIGNED,sid INT UNSIGNED,"
                +"parent INT UNSIGNED,thread INT UNSIGNED,date DATETIME,name VARCHAR(60),host_name VARCHAR(255),"
                +"comment TEXT,userid INT UNSIGNED,icon TEXT";

        String createCommentVar = "CREATE TABLE " + TABLE_COMMENT_VAR + "( score SMALLINT UNSIGNED,reason SMALLINT UNSIGNED)";
*/
        excute(createPage);

    }
}
