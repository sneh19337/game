package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.*;
import javafx.scene.web.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.io.*;
import javafx.geometry.*;
import javafx.scene.Group;

import static javafx.scene.paint.Color.GREENYELLOW;

public class Main extends Application {
Stage window;
    Scene scene1,scene2, scene3, scene4,scene5;
    // launch the application
    public void start(Stage stage) {
        window=stage;
        try {

            Button button1 = new Button("START NEW GAME");
            button1.setStyle ("-fx-base: blue");
            Button button2 = new Button("GAME BEGINS");
            button2.setStyle ("-fx-base: green");
            Button button3 = new Button("SAVE");
            button3.setStyle ("-fx-base: orange");
            Button button4 = new Button("BACK");
            button4.setStyle ("-fx-base: brown");
            Button button5 = new Button("END GAME");
            button5.setStyle ("-fx-base: red");
            Button button6 =new Button("PAUSE");
            button6.setStyle ("-fx-base: green");
            Button button7 = new Button("Game is paused");
            button7.setStyle ("-fx-base: orange");
            Button button8 = new Button("OKAY");
            button8.setStyle ("-fx-base: light green");
            Button button9=new Button("RELOAD PREVIOUS GAME");
            button9.setStyle ("-fx-base: blue");
            // set title for the stage
            stage.setTitle("COLORSWITCH2.0");


            // create a label
            Button label = new Button("Name : ");
            Label label1 = new Label("Welcome to colorswitch2.0 ");
            label1.setStyle("-fx-border-color: white;");
            label1.setTextFill(Color.web("#ff0000", 0.8));

            // create a text field
            TextField textfield = new TextField();

            // set preferred column count
            textfield.setPrefColumnCount(10);

            // create a button
            Button button = new Button("OK");
            button.setOnAction(e -> window.setScene(scene2));
            Button ex = new Button("Exit");
            ex.setOnAction(e -> closeProgram());
            button5.setOnAction(e -> closeProgram());
            // add the label, text field and button
            HBox hbox = new HBox(label, textfield, button, ex);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            VBox layout1 = new VBox(20);
            layout1.getChildren().addAll(label1,button1,button9,button3,button5,button6);
            FileInputStream input = new FileInputStream("C:\\Users\\SNEH SUMAN\\Desktop\\color_switch\\colorswitch.jpeg");
            // create a image
            Image image = new Image(input);


            // create a background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            // create Background
            Background background = new Background(backgroundimage);

            // set background
            hbox.setBackground(background);
            layout1.setBackground(background);
           // layout1.setStyle("-fx-background-color: #000000");
            Menu menu = new Menu("Menu 1");

            menu.setOnShowing(e -> { System.out.println("Showing Menu 1"); });
            menu.setOnShown  (e -> { System.out.println("Shown Menu 1"); });
            menu.setOnHiding (e -> { System.out.println("Hiding Menu 1"); });
            menu.setOnHidden (e -> { System.out.println("Hidden Menu 1"); });
            grid.setVgap(10);
            scene2 = new Scene(layout1, 720, 720, GREENYELLOW);
            // create a scene
            grid.setPadding(new Insets(25, 25, 25, 25));

            //Scene scene = new Scene(grid, 300, 275);
            Scene scene1 = new Scene(grid, 300, 275);
            Text scenetitle = new Text("HELLO PLAYER");
            scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(scenetitle, 0, 0, 1, 1);
            button8.setOnAction(e->window.setScene(scene2));
            button2.setOnAction(e -> window.setScene(scene2));
            button4.setOnAction(e -> window.setScene(scene2));
            button7.setOnAction(e ->window.setScene(scene2));

            Scene scene = new Scene(hbox, 720, 720);

            layout1.setAlignment(Pos.BASELINE_CENTER);
            //layout1.getChildren().addAll(label1, scenetitle);
            // create a input stream
            //input image is taken from https://apkpure.com/color-switch/com.colorswitch.switch2 credits to them




            // set the scene 
            stage.setScene(scene);

            stage.show();
            ////////////////////////



            
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }


        public void closeProgram ()
        {
            window.close();
        }

    // Main Method 
    public static void main(String args[])
    {

        // launch the application 
        launch(args);
    }
} 