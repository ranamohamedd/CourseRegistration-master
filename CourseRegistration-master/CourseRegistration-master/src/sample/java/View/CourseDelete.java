package sample.java.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.java.db.CourseDBOperations;
import sample.java.db.DBManagerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static javafx.geometry.HPos.RIGHT;

public class CourseDelete extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Course Delete Page");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //grid.setAlignment(Pos.TOP_LEFT);
        Button btnExit = new Button("Logout");
        btnExit.setMaxWidth(Double.MAX_VALUE);
        btnExit.setMinWidth(60);
        btnExit.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBt = new HBox(10);
        hbBt.setAlignment(Pos.TOP_RIGHT);
        hbBt.getChildren().add(btnExit);
        grid.add(hbBt, 0, 1, 30, 3 );

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

        Button btnHome1 = new Button("Course");
        btnHome1.setMaxWidth(Double.MAX_VALUE);
        btnHome1.setMinWidth(60);
        btnHome1.setMinHeight(30);
        // btnExit.setStyle("-fx-background-color: crimson;");
        HBox hbBth1 = new HBox(10);
        hbBth1.setAlignment(Pos.TOP_RIGHT);
        hbBth1.getChildren().add(btnHome1);
        grid.add(hbBth1, 0, 6, 30, 3 );

        btnHome1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                CourseHome l = new CourseHome();
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
        try
        {

            String strQuery = "SELECT courseid FROM app_course order by courseid";
            con = DBManagerFactory.getDBConnection();
            stm = con.createStatement();
            rs = stm.executeQuery(strQuery);

            while(rs.next())
            {
                cbox.getItems().add(rs.getString(1));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                stm.close();
                con.close();
            }
            catch(Exception e)
            {
            }
        }

        grid.add(cbox, 0, 6);

        Button btn = new Button("Delete Details");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 8, 1, 1 );
        //grid.add(hbBtn, 1, 8);

        Text actiontarget = new Text();
        grid.add(actiontarget, 0, 10);
        grid.setColumnSpan(actiontarget, 2);
        grid.setHalignment(actiontarget, RIGHT);
        actiontarget.setId("actiontarget");


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                if((String)cbox.getValue() == null)
                {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Please select a value to delete");
                    return;
                }

                CourseDBOperations aObj = new CourseDBOperations();
                String strStatus = aObj.deleteOperation((String)cbox.getValue());

                if("success".equalsIgnoreCase(strStatus))
                {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("Course deleted");
                }
                else
                {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Error occured in delete operation");
                }

                cbox.getItems().clear();

                Connection con = null;
                Statement stm = null;
                ResultSet rs = null;

                try
                {

                    String strQuery = "SELECT courseid FROM app_course order by courseid";
                    con = DBManagerFactory.getDBConnection();
                    stm = con.createStatement();
                    rs = stm.executeQuery(strQuery);

                    while(rs.next())
                    {
                        cbox.getItems().add(rs.getString(1));
                    }

                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
                finally
                {
                    try
                    {
                        rs.close();
                        stm.close();
                        con.close();
                    }
                    catch(Exception e2)
                    {
                    }
                }
            }
        });

        primaryStage.setTitle("Delete Course Page");
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
