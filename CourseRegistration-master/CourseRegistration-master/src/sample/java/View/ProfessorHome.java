package sample.java.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProfessorHome extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Professor Home Page");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 10, 10);

        //grid.setAlignment(Pos.TOP_LEFT);
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
        grid.add(hbBth, 0, 4, 30, 3 );

        btnHome.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                WelcomePage l = new WelcomePage();
                try {
                    l.start(primaryStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        grid.setAlignment(Pos.TOP_CENTER);


        Button btn = new Button("View Professors");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMinWidth(200);
        btn.setMinHeight(70);
        btn.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn = new HBox(10);
        // hbBtn.setAlignment(Pos.TOP_CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 14, 10, 3 );

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ProfessorView s = new ProfessorView();
                s.start(primaryStage);
            }
        });

        Button btn1 = new Button("Add Professor");
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn1.setMinWidth(200);
        btn1.setMinHeight(70);
        btn1.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn1 = new HBox(10);
        //hbBtn1.setAlignment(Pos.TOP_CENTER);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 0, 17, 10, 3 );

        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ProfessorPage p = new ProfessorPage();
                try {
                    p.start(primaryStage);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button btn3 = new Button("Update Professor");
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn3.setMinWidth(200);
        btn3.setMinHeight(70);
        btn3.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn3 = new HBox(10);
        //hbBtn.setAlignment(Pos.TOP_CENTER);
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 0, 20, 10, 3 );

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ProfessorUpdate c = new ProfessorUpdate();
                c.start(primaryStage);
            }
        });

        Button btn4 = new Button("Delete Professor");
        btn4.setMaxWidth(Double.MAX_VALUE);
        btn4.setMinWidth(200);
        btn4.setMinHeight(70);
        btn4.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn4 = new HBox(10);
        //hbBtn4.setAlignment(Pos.TOP_CENTER);
        hbBtn4.getChildren().add(btn4);
        grid.add(hbBtn4, 0, 23, 10, 3 );

        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ProfessorDelete t = new ProfessorDelete();
                t.start(primaryStage);
            }
        });



        primaryStage.setTitle("Welcome to Student Application!!");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
