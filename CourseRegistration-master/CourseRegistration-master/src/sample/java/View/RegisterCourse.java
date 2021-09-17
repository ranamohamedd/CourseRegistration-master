package sample.java.View;

import static javafx.geometry.HPos.RIGHT;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sample.java.Model.Course;
import sample.java.db.CourseDBOperations;
import sample.java.db.DBManagerFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RegisterCourse extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Course Registration Page");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Button btnExit = new Button("Logout");
        btnExit.setMaxWidth(Double.MAX_VALUE);
        btnExit.setMinWidth(60);
        btnExit.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBt = new HBox(10);
        hbBt.setAlignment(Pos.TOP_RIGHT);
        hbBt.getChildren().add(btnExit);
        grid.add(hbBt, 0, 1, 30, 3);

        btnExit.setOnAction(e -> {
            Login l = new Login();
            try {
                l.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        Button btnHome = new Button("Home");
        btnHome.setMaxWidth(Double.MAX_VALUE);
        btnHome.setMinWidth(60);
        btnHome.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBth = new HBox(10);
        hbBth.setAlignment(Pos.TOP_RIGHT);
        hbBth.getChildren().add(btnHome);
        grid.add(hbBth, 0, 4, 30, 3);

        btnHome.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                NormalWelcome l = new NormalWelcome();
                l.start(primaryStage);
            }
        });

        grid.setAlignment(Pos.TOP_CENTER);

        Text delLabel = new Text("Course ID:");
        grid.add(delLabel, 0, 5);

        ChoiceBox cbox = new ChoiceBox();
        //cbox.getItems().addAll   ("Admin1", "Admin2", "Admin3", "Admin4", "Admin5");

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {

            String strQuery = "SELECT courseid FROM app_course order by courseid";
            con = DBManagerFactory.getDBConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(strQuery);

            while (rs.next()) {
                cbox.getItems().add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stm.close();
                con.close();
            } catch (Exception e) {
            }
        }

        grid.add(cbox, 0, 6);

        Button viewDetailsBtn = new Button("View Course Details");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(viewDetailsBtn);
        grid.add(hbBtn, 0, 8, 1, 1);
        //grid.add(hbBtn, 1, 8);

        Text actiontarget1 = new Text();
        grid.add(actiontarget1, 0, 10);
        grid.setColumnSpan(actiontarget1, 2);
        grid.setHalignment(actiontarget1, RIGHT);
        actiontarget1.setId("actiontarget");


        viewDetailsBtn.setOnAction(e -> {


            if (cbox.getValue() == null) {
                actiontarget1.setFill(Color.FIREBRICK);
                actiontarget1.setText("Please select a value to view");
                return;
            }

            actiontarget1.setText("");

            CourseDBOperations aObj = new CourseDBOperations();
            Course boObj = aObj.fetchCourseStudent((String) cbox.getValue());

            Label sName = new Label("Course ID:");
            grid.add(sName, 0, 11);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 11);
            userTextField.setText(boObj.getCourseId());
            userTextField.setEditable(false);

            Label sid = new Label("Course Name:");
            grid.add(sid, 0, 12);

            TextField userTextField1 = new TextField();
            grid.add(userTextField1, 1, 12);
            userTextField1.setText(boObj.getCourseName());
            userTextField1.setEditable(false);

            Label sEmail = new Label("Term:");
            grid.add(sEmail, 0, 13);

            TextField userTextField2 = new TextField();
            grid.add(userTextField2, 1, 13);
            userTextField2.setText(boObj.getTerm());
            userTextField2.setEditable(false);

            Label sPhone = new Label("Professor:");
            grid.add(sPhone, 0, 14);

            TextField userTextField3 = new TextField();
            grid.add(userTextField3, 1, 14);
            userTextField3.setText(boObj.getProfessorId());
            userTextField3.setEditable(false);

            Label sAddress = new Label("TA:");
            grid.add(sAddress, 0, 15);

            TextField userTextField4 = new TextField();
            grid.add(userTextField4, 1, 15);
            userTextField4.setText(boObj.getTaId());
            userTextField4.setEditable(false);


            Button registerBtn = new Button("Register Course");
            HBox hbBtn1 = new HBox(10);
            hbBtn1.setAlignment(Pos.BOTTOM_CENTER);
            hbBtn1.getChildren().add(registerBtn);
            grid.add(hbBtn1, 0, 18, 2, 1);

            Text actiontarget = new Text();
            grid.add(actiontarget, 0, 20);
            grid.setColumnSpan(actiontarget, 2);
            grid.setHalignment(actiontarget, RIGHT);
            actiontarget.setId("actiontarget");

            registerBtn.setOnAction(e13 -> {

                String courseid = userTextField.getText();
                String strStudentid = "1";

                Connection con1 = null;
                Statement stmt1 = null;
                String strStatus = "error";

                try {
                    con1 = DBManagerFactory.getDBConnection();

                    stmt1 = con1.createStatement();
                    String strQuery1 = "insert into APP_STUDENTCOURSES (studentcourseid, courseid, studentid) values "
                            + "('" + (courseid.concat(strStudentid)) + "','" + courseid + "','" + strStudentid + "')";

                    stmt1.executeUpdate(strQuery1);

                    con1.commit();

                    strStatus = "success";
                } catch (Exception e1) {
                    try {
                        con1.rollback();
                    } catch (SQLException e11) {
                        // TODO Auto-generated catch block
                        //e1.printStackTrace();
                    }
                    e1.printStackTrace();
                } finally {
                    try {
                        stmt1.close();
                        con1.close();
                    } catch (Exception e12) {
                        //e.printStackTrace();
                    }
                }

                if ("success".equalsIgnoreCase(strStatus)) {
                    RegisterCourse s = new RegisterCourse();
                    s.start(primaryStage);
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Error in course registration or student is already registered for this course");
                }

            });
        });

        primaryStage.setTitle("Course Registration Page");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
