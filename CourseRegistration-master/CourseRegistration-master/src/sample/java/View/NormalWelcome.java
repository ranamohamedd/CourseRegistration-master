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

public class NormalWelcome extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome Page");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 10, 10);

        //grid.setAlignment(Pos.TOP_LEFT);
        Button btnExit = new Button("Logout");
        btnExit.setMaxWidth(Double.MAX_VALUE);
        btnExit.setMinWidth(30);
        btnExit.setMinHeight(20);
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

        grid.setAlignment(Pos.TOP_CENTER);

        Button btn2 = new Button("View Courses");
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn2.setMinWidth(200);
        btn2.setMinHeight(70);
        btn2.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn2 = new HBox(10);
        // hbBtn2.setAlignment(Pos.TOP_CENTER);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 11, 30, 3 );

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                ViewStudentCourses a = new ViewStudentCourses();
                a.start(primaryStage);
            }
        });

        Button btn = new Button("Register Course");
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
                RegisterCourse s = new RegisterCourse();
                s.start(primaryStage);
            }
        });

        Button btn1 = new Button("Drop Course");
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
                DropCourse p = new DropCourse();
                p.start(primaryStage);
            }
        });


        primaryStage.setTitle("Welcome to Student Application!!");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
