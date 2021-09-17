package sample.java.View;

import static javafx.geometry.HPos.RIGHT;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.java.Model.Student;
import sample.java.db.StudentDBOperations;

import java.io.IOException;

public class StudentPage extends Application  {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Add Student Page");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Button btnExit = new Button("Logout");
        btnExit.setMaxWidth(Double.MAX_VALUE);
        btnExit.setMinWidth(60);
        btnExit.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBt = new HBox(10);
        hbBt.setAlignment(Pos.TOP_RIGHT);
        hbBt.getChildren().add(btnExit);
        grid.add(hbBt, 0, 0, 30, 3 );

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
        grid.add(hbBth, 0, 1, 30, 3 );

        btnHome.setOnAction(e -> {
            WelcomePage l = new WelcomePage();
            try {
                l.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button btnHome1 = new Button("Student");
        btnHome1.setMaxWidth(Double.MAX_VALUE);
        btnHome1.setMinWidth(60);
        btnHome1.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBth1 = new HBox(10);
        hbBth1.setAlignment(Pos.TOP_RIGHT);
        hbBth1.getChildren().add(btnHome1);
        grid.add(hbBth1, 0, 2, 30, 3 );

        btnHome1.setOnAction(e -> {
            StudentHome l = new StudentHome();
            l.start(primaryStage);
        });


        grid.setAlignment(Pos.TOP_CENTER);

        Label sName = new Label("Student Name:");
        grid.add(sName, 0, 1);

        TextField studentNameTF = new TextField();
        grid.add(studentNameTF, 1, 1);

        Label sid = new Label("Student ID:");
        grid.add(sid, 0, 2);

        TextField studentIDTF = new TextField();
        grid.add(studentIDTF, 1, 2);

        Label sEmail = new Label("Email:");
        grid.add(sEmail, 0, 3);

        TextField emailTF = new TextField();
        grid.add(emailTF, 1, 3);

        Label sPhone = new Label("Phone No:");
        grid.add(sPhone, 0, 4);

        TextField phoneNoTF = new TextField();
        grid.add(phoneNoTF, 1, 4);

        Label sAddress = new Label("Address:");
        grid.add(sAddress, 0, 5);

        TextField addressTF = new TextField();
        grid.add(addressTF, 1, 5);

        Label userID = new Label("User ID:");
        grid.add(userID, 0, 6);

        TextField userIDTF = new TextField();
        grid.add(userIDTF, 1, 6);

        Label pass = new Label("Password:");
        grid.add(pass, 0, 7);

        TextField passwordTF = new TextField();
        grid.add(passwordTF, 1, 7);

        Text actionTarget = new Text();
        grid.add(actionTarget, 0, 10);
        grid.setColumnSpan(actionTarget, 2);
        grid.setHalignment(actionTarget, RIGHT);
        actionTarget.setId("actionTarget");

        Button saveBtn = new Button("Save Details");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(saveBtn);
        grid.add(hbBtn, 0, 8, 2, 1 );
        //grid.add(hbBtn, 1, 8);

        saveBtn.setOnAction(e -> {

            String studentName = studentNameTF.getText();
            String studentID = studentIDTF.getText();
            String emailString = emailTF.getText();
            String phoneNo = phoneNoTF.getText();
            String addressString = addressTF.getText();
            String userIDString = userIDTF.getText();
            String passwordString = passwordTF.getText();

            //System.out.println("This is is user id "+userIDString);

            if ("".equalsIgnoreCase(passwordString) || "".equalsIgnoreCase(userIDString) || "".equalsIgnoreCase(addressString) || "".equalsIgnoreCase(phoneNo) || "".equalsIgnoreCase(emailString) || "".equalsIgnoreCase(studentID) || "".equalsIgnoreCase(studentName)) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Please provide all values");
                return;
            }

            Student boObj = new Student();

            boObj.setStudentname(studentName);
            boObj.setStudentid(studentID);
            boObj.setEmail(emailString);
            boObj.setPhone(phoneNo);
            boObj.setAddress(addressString);
            boObj.setLoginid(studentID);
            boObj.setPassword(passwordString);

            StudentDBOperations obj = new StudentDBOperations();
            String strStatus = obj.addOperation(boObj);

            if ("success".equalsIgnoreCase(strStatus)) {
                actionTarget.setFill(Color.GREEN);
                actionTarget.setText("Data saved successfully!");
            } else {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Error in save operation");
            }
        });


        primaryStage.setTitle("Add Student Page");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
