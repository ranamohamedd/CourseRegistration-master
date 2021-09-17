package sample.java.View;

import javafx.application.Application;
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
import sample.java.Model.Course;
import sample.java.db.CourseDBOperations;
import sample.java.db.DBManagerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static javafx.geometry.HPos.RIGHT;

public class CoursePage extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Add Course Page");
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
        grid.add(hbBt, 0, 0, 30, 3);

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
        HBox hbBth = new HBox(10);
        hbBth.setAlignment(Pos.TOP_RIGHT);
        hbBth.getChildren().add(btnHome);
        grid.add(hbBth, 0, 1, 30, 3);

        btnHome.setOnAction(e -> {
            WelcomePage l = new WelcomePage();
            try {
                l.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnHome1 = new Button("Course");
        btnHome1.setMaxWidth(Double.MAX_VALUE);
        btnHome1.setMinWidth(60);
        btnHome1.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBth1 = new HBox(10);
        hbBth1.setAlignment(Pos.TOP_RIGHT);
        hbBth1.getChildren().add(btnHome1);
        grid.add(hbBth1, 0, 2, 30, 3);

        btnHome1.setOnAction(e -> {
            CourseHome l = new CourseHome();
            l.start(primaryStage);
        });

        grid.setAlignment(Pos.TOP_CENTER);

        Label sName = new Label("Course ID:");
        grid.add(sName, 0, 1);

        TextField courseIdTextField = new TextField();
        grid.add(courseIdTextField, 1, 1);

        Label sid = new Label("Course Name:");
        grid.add(sid, 0, 2);

        TextField courseNameTextField = new TextField();
        grid.add(courseNameTextField, 1, 2);

        Label sEmail = new Label("Term:");
        grid.add(sEmail, 0, 3);

        TextField termTextField = new TextField();
        grid.add(termTextField, 1, 3);

        Label sPhone = new Label("Professor:");
        grid.add(sPhone, 0, 4);


        ChoiceBox professorChoiceBox = new ChoiceBox();

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;

        try {

            String strQuery = "SELECT professorid FROM app_professor order by professorid";
            con = DBManagerFactory.getDBConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(strQuery);

            while (rs.next()) {
                professorChoiceBox.getItems().add(rs.getString(1));
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

        grid.add(professorChoiceBox, 1, 4);

        Label sAddress = new Label("TA:");
        grid.add(sAddress, 0, 5);


        ChoiceBox cbox1 = new ChoiceBox();

        Connection con1 = null;
        Statement stm1 = null;
        ResultSet rs1 = null;

        try {

            String strQuery1 = "SELECT taid FROM app_ta order by taid";
            con1 = DBManagerFactory.getDBConnection();
            stm1 = con1.createStatement();
            rs1 = stm1.executeQuery(strQuery1);

            while (rs1.next()) {
                cbox1.getItems().add(rs1.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                stm1.close();
                con1.close();
            } catch (Exception e) {
            }
        }

        grid.add(cbox1, 1, 5);

        Button saveBtn = new Button("Save Details");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(saveBtn);
        grid.add(hbBtn, 0, 8, 2, 1);

        Text actiontarget = new Text();
        grid.add(actiontarget, 0, 10);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        saveBtn.setOnAction(e -> {

            String str1 = courseIdTextField.getText();
            String str2 = courseNameTextField.getText();
            String str3 = termTextField.getText();
            String str4 = (String) professorChoiceBox.getValue();
            String str5 = (String) cbox1.getValue();

            if (null == str5 || null == str4 || "".equalsIgnoreCase(str3) || "".equalsIgnoreCase(str2) || "".equalsIgnoreCase(str1)) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Please provide all values");
                return;
            }

            Course boObj = new Course();

            boObj.setCourseId(str1);
            boObj.setCourseName(str2);
            boObj.setTerm(str3);
            boObj.setProfessorId(str4);
            boObj.setTaId(str5);

            CourseDBOperations obj = new CourseDBOperations();
            String strStatus = obj.addOperation(boObj);

            if ("success".equalsIgnoreCase(strStatus)) {
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Data saved successfully!");
            } else {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Error in save operation");
            }
        });


        primaryStage.setTitle("Add Course Page");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
