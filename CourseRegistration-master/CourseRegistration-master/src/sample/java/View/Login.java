package sample.java.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.java.Model.SessionSaver;
import sample.java.db.DBManagerFactory;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static javafx.geometry.HPos.RIGHT;

public class Login extends Application {

    private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    String role = "";

    TextField userTextField;
    PasswordField pwBox;
    Text actiontarget;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Course Registration");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Student Application Login");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        userTextField= new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button signInBtn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signInBtn);
        grid.add(hbBtn, 1, 4);

        Button backBtn = new Button("Back");
        HBox hbBackBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBackBtn, 1,2);

        actiontarget = new Text();
        grid.add(actiontarget, 0, 6);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");

        signInBtn.setOnAction(e -> {

            String struserName = userTextField.getText();
            String pwVal = pwBox.getText();

            if (struserName == null || "".equalsIgnoreCase(struserName) || "null".equalsIgnoreCase(struserName) || pwVal == null || "".equalsIgnoreCase(pwVal) || "null".equalsIgnoreCase(pwVal)) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Please provide username and password");
                return;
            }

            if ("admin".equalsIgnoreCase(struserName) && "admin".equalsIgnoreCase(pwVal)) {
                WelcomePage s = new WelcomePage();
                try {
                    s.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if ("student".equalsIgnoreCase(struserName) && "student".equalsIgnoreCase(pwVal)) {
                NormalWelcome s = new NormalWelcome();
                s.start(primaryStage);
            }else {
                try {
                    if(logIn().equals("Success")){
                        System.out.println("This is the role "+role);
                        if(role.equals("Student")){
                            NormalWelcome s = new NormalWelcome();
                            s.start(primaryStage);
                        }else if(role.equals("Admin")){
                            WelcomePage s = new WelcomePage();
                            try {
                                s.start(primaryStage);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    else {
                        actiontarget.setFill(Color.FIREBRICK);
                        actiontarget.setText("Please provide valid user name and password");
                        return;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backBtn.setOnAction(event -> {
            MainPage l = new MainPage();
            try {
                l.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(grid, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String logIn() throws SQLException {
        String username = userTextField.getText();
        String password = pwBox.getText();

        String sql = "select * from my_db.app_users where loginid= ? and password = ? and role = ?";

        role = SessionSaver.getRole();
        //System.out.println("This is the role "+role);

        try {
            con = DBManagerFactory.getDBConnection();
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                userTextField.clear();
                pwBox.clear();
                actiontarget.setText("Please provide valid user name and password");
                System.out.println("Error");
                return "Error";
            } else {
                SessionSaver.setUsername(username);
                System.out.println("Success");
                return "Success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Exception";
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
