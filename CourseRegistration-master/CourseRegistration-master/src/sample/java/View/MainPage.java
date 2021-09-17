package sample.java.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.java.Model.SessionSaver;

import java.io.IOException;

public class MainPage extends Application {

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

        Label label = new Label("Who are you");
        label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(label, 0, 1);

        Button adminBtn = new Button("Admin");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(adminBtn);
        grid.add(hbBtn, 0,2);


        Button studentBtn = new Button("Student");
        HBox hbStBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(studentBtn);
        grid.add(hbStBtn, 1,2);

        adminBtn.setOnAction(event -> {
            SessionSaver.setRole("Admin");
            Login s = new Login();
            try {
                s.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        studentBtn.setOnAction(event -> {
            SessionSaver.setRole("Student");
            Login s = new Login();
            try {
                s.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(grid, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
