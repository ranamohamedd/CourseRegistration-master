package sample.java.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceGenerator {


    public static int getNextValue(String sequenceName) {
        int iSeqVal = 0;

        Connection conn = null;

        try {
            conn = DBManagerFactory.getDBConnection();
            String sql = "select " + sequenceName + " from app_users";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                iSeqVal = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "here 1");
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return iSeqVal;
    }
}
