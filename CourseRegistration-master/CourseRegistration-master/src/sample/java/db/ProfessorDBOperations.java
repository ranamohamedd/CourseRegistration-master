package sample.java.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.java.Model.Professor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProfessorDBOperations implements DBOperations {
    public Professor fetchProfessor(String key) {
        Connection con = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        Professor bo = new Professor();

        try {
            con = DBManagerFactory.getDBConnection();
            System.out.println("Suscessfully connected");

            stmt1 = con.createStatement();
            String strQuery1 = "select professorid, professorname, email, phone from app_professor "
                    + "where professorid = '" + key + "' and rownum = 1";

            rs = stmt1.executeQuery(strQuery1);

            while (rs.next()) {
                bo.setProfessorid(rs.getString(1));
                bo.setProfessorname(rs.getString(2));
                bo.setEmail(rs.getString(3));
                bo.setPhone(rs.getString(4));

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
        // TODO Auto-generated method stub
        Connection con = null;
        Statement stmt1 = null;
        String strStatus = "error";

        try {
            Professor bo = (Professor) obj;

            con = DBManagerFactory.getDBConnection();

            stmt1 = con.createStatement();
            String strQuery1 = "insert into app_professor (professorname, email, phone) values "
                    + "('" + bo.getProfessorname() + "','" + bo.getEmail() + "','" + bo.getPhone() + "')";

            stmt1.executeUpdate(strQuery1);

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
                stmt1.close();
                con.close();
            } catch (Exception e) {
                //e.printStackTrace();
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
        try {
            String strQuery = "select professorid, PROFESSORNAME, email, phone from app_professor order by professorid";
            con = DBManagerFactory.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(strQuery);

            while (rs.next()) {
                Map<String, String> dataRow = new HashMap<>();
                dataRow.put("ProfessorID", rs.getString(1));
                dataRow.put("ProfessorName", rs.getString(2));
                dataRow.put("Phone", rs.getString(3));
                dataRow.put("Email", rs.getString(4));

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
        Statement stmt1 = null;

        String strStatus = "fail";

        try {
            con = DBManagerFactory.getDBConnection();

            stmt1 = con.createStatement();
            String strQuery1 = "DELETE FROM app_professor WHERE professorid = '" + key + "'";
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
                con.close();
            } catch (Exception e) {
            }
        }
        return strStatus;
    }

    @Override
    public String updateOperation(Object obj) {
        Connection con = null;
        Statement stmt1 = null;
        String strStatus = "error";

        try {
            Professor bo = (Professor) obj;

            con = DBManagerFactory.getDBConnection();

            stmt1 = con.createStatement();
            String strQuery1 = "UPDATE app_professor SET professorname = '" + bo.getProfessorname() + "' , email = '" + bo.getEmail() + "' , phone = '" + bo.getPhone() + "' "
                    + "WHERE professorid = '" + bo.getProfessorid() + "'";

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
                stmt1.close();
                con.close();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return strStatus;
    }
}
