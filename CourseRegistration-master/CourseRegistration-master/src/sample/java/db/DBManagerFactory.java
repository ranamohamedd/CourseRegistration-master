package sample.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManagerFactory {

    static Connection conn;

    public static Connection getDBConnection() {

        String strDBUserID = "root";
        String strDBPwd = "root";
        String strHostName = "localhost";
        String strDBSID = "my_db";
        String strPortNo = "3306";
        String strDBType = "mysql";


        try {
            if ("oracle".equalsIgnoreCase(strDBType)) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@" + strHostName + ":" + strPortNo + ":" + strDBSID, strDBUserID, strDBPwd);
            } else if ("mysql".equalsIgnoreCase(strDBType)) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://" + strHostName + ":" + strPortNo + "/" + strDBSID, strDBUserID, strDBPwd);
                System.out.println("Successfully connected");
            }

            conn.setAutoCommit(false);

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            conn = null;
        } catch (SQLException se) {
            se.printStackTrace();
            conn = null;
        } catch (Exception e) {
            e.printStackTrace();
            conn = null;
        }
        return conn;
    }
}
