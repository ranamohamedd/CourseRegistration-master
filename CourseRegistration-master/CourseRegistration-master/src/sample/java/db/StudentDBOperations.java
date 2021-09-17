package sample.java.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.java.Model.Student;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StudentDBOperations implements DBOperations {

    public Student fetchStudent(String key) {
        Connection con = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        Student bo = new Student();

        try {
            con = DBManagerFactory.getDBConnection();

            stmt1 = con.createStatement();
            String strQuery1 = "select studentid, studentname, address, email, phone, u.loginid, u.password, u.userid  from app_students a, app_users u "
                    + "where a.userid = u.userid AND studentid = '" + key + "' and rownum = 1";

            rs = stmt1.executeQuery(strQuery1);

            while (rs.next()) {
                bo.setStudentid(rs.getString(1));
                bo.setStudentname(rs.getString(2));
                bo.setAddress(rs.getString(3));
                bo.setEmail(rs.getString(4));
                bo.setPhone(rs.getString(5));
                bo.setLoginid(rs.getString(6));
                bo.setPassword(rs.getString(7));
                bo.setUserid(rs.getString(8));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt1.close();
                con.close();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }


        return bo;
    }


    @Override
    public String addOperation(Object obj) {
        Connection con = null;
        Statement stmt = null;
        Statement stmt1 = null;
        String strStatus = "error";

        try {
            Student bo = (Student) obj;

            con = DBManagerFactory.getDBConnection();

            int seqVal = SequenceGenerator.getNextValue("userid");
            //stmt = con.createStatement();
            String strQuery = "INSERT INTO app_users (userid, loginid, password) VALUES (" + bo.getStudentid() + ", '" + bo.getLoginid() + "','" + bo.getPassword() + "')";

            System.out.println("this is user id " + bo.getUserid());
            System.out.println("this is login id " + bo.getLoginid());
            System.out.println("this is password  " + bo.getPassword());

            PreparedStatement pstmt = con.prepareStatement(strQuery);
            pstmt.executeUpdate();

            stmt1 = con.createStatement();
            String strQuery1 = "insert into my_db.app_students (studentid, studentname, userid, email, phone, address) values "
                    + "('" + bo.getStudentid() + "','" + bo.getStudentname() + "','" + seqVal + "','" + bo.getEmail() + "','" + bo.getPhone() + "','" + bo.getAddress() + "')";

            stmt1.executeUpdate(strQuery1);

            con.commit();

            strStatus = "success";
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                stmt1.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return strStatus;
    }

    @Override
    public ObservableList<Map> viewOperation() {
        // TODO Auto-generated method stub
        ObservableList<Map> allData = FXCollections.observableArrayList();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            String strQuery = "select s.studentid, s.studentname, s.phone, s.email, s.address, u.loginid, u.password  from app_students s, app_users u order by studentid";
            con = DBManagerFactory.getDBConnection();
            //stmt = con.createStatement();
            //rs = stmt.executeQuery(strQuery);

            pst = con.prepareStatement(strQuery);
            rs = pst.executeQuery();

            while (rs.next()) {
                Map<String, String> dataRow = new HashMap<>();
                dataRow.put("StudentID", rs.getString(1));
                dataRow.put("StudentName", rs.getString(2));
                dataRow.put("Phone", rs.getString(3));
                dataRow.put("Email", rs.getString(4));
                dataRow.put("Address", rs.getString(5));
                dataRow.put("UserID", rs.getString(6));
                dataRow.put("password", rs.getString(7));

                allData.add(dataRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
            }
        }


        return allData;
    }

    @Override
    public String deleteOperation(String key) {

        Connection con = null;
        Statement stmt = null;
        Statement stmt1 = null;

        String strStatus = "fail";

        try {
            con = DBManagerFactory.getDBConnection();

            stmt = con.createStatement();
            String strQuery = "DELETE FROM APP_STUDENTCOURSES WHERE studentid =  '" + key + "'";
            stmt.executeUpdate(strQuery);

            stmt1 = con.createStatement();
            String strQuery1 = "DELETE FROM app_students WHERE studentid = '" + key + "'";
            stmt1.executeUpdate(strQuery1);

            con.commit();

            strStatus = "success";
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                stmt1.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return strStatus;
    }

    @Override
    public String updateOperation(Object obj) {

        Connection con = null;
        Statement stmt = null;
        Statement stmt1 = null;
        String strStatus = "error";

        try {
            Student bo = (Student) obj;

            con = DBManagerFactory.getDBConnection();

            stmt = con.createStatement();
            String strQuery = "UPDATE app_users SET loginid = '" + bo.getLoginid() + "' , password = '" + bo.getPassword() + "' WHERE userid =  '" + bo.getUserid() + "'";

            stmt.executeQuery(strQuery);

            stmt1 = con.createStatement();
            String strQuery1 = "UPDATE app_students SET studentname = '" + bo.getStudentname() + "' , email = '" + bo.getEmail() + "' , phone = '" + bo.getPhone() + "' , address = '" + bo.getAddress() + "' "
                    + "WHERE studentid  = '" + bo.getStudentid() + "'";

            stmt1.executeQuery(strQuery1);

            con.commit();

            strStatus = "success";
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                //e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                stmt1.close();
                con.close();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return strStatus;
    }
}
