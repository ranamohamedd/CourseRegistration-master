package sample.java.View;


import javafx.application.Application;
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

public class TAHome extends Application  {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("TA Home Page");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 10, 10);

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

        btnHome.setOnAction(e -> {
            WelcomePage l = new WelcomePage();
            try {
                l.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        grid.setAlignment(Pos.TOP_CENTER);


        Button viewBtn = new Button("View TAs");
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        viewBtn.setMinWidth(200);
        viewBtn.setMinHeight(70);
        viewBtn.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn = new HBox(10);
        // hbBtn.setAlignment(Pos.TOP_CENTER);
        hbBtn.getChildren().add(viewBtn);
        grid.add(hbBtn, 0, 14, 10, 3 );

        viewBtn.setOnAction(e -> {
            TAView s = new TAView();
            s.start(primaryStage);
        });

        Button addBtn = new Button("Add TA");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setMinWidth(200);
        addBtn.setMinHeight(70);
        addBtn.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn1 = new HBox(10);
        //hbBtn1.setAlignment(Pos.TOP_CENTER);
        hbBtn1.getChildren().add(addBtn);
        grid.add(hbBtn1, 0, 17, 10, 3 );

        addBtn.setOnAction(e -> {
            TAPage s = new TAPage();
            s.start(primaryStage);
        });

        Button btn3 = new Button("Update TA");
        btn3.setMaxWidth(Double.MAX_VALUE);
        btn3.setMinWidth(200);
        btn3.setMinHeight(70);
        btn3.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 0, 20, 10, 3 );

        btn3.setOnAction(e -> {
            TAUpdate c = new TAUpdate();
            c.start(primaryStage);
        });

        Button deleteBtn = new Button("Delete TA");
        deleteBtn.setMaxWidth(Double.MAX_VALUE);
        deleteBtn.setMinWidth(200);
        deleteBtn.setMinHeight(70);
        deleteBtn.setStyle("-fx-font-size: 15pt;");
        HBox hbBtn4 = new HBox(10);
        //hbBtn4.setAlignment(Pos.TOP_CENTER);
        hbBtn4.getChildren().add(deleteBtn);
        grid.add(hbBtn4, 0, 23, 10, 3 );

        deleteBtn.setOnAction(e -> {
            TADelete t = new TADelete();
            t.start(primaryStage);
        });



        primaryStage.setTitle("Welcome to Student Application!!");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
