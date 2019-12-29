
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Grafika2D extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Użyjemy węzła Group
        Group grupa = new Group();

        Scene scene = new Scene(grupa, 800, 800);

        int koniecX = 100;
        int koniecY = 200;
        for (int startX = 10; startX <= koniecX; startX++) {
            for (int startY = 20; startY < koniecY; startY++) {

                // Współrzędne początku i końca linii ustawiane za pomocą konstruktora
                Line linia1 = new Line(startX, startY, koniecX, koniecY);
                grupa.getChildren().addAll(linia1);
            }
        }
        // Druga linia
        int koniecX1 = 400;
        int koniecY1 = 200;
        for (int startX = 10; startX <= koniecX1; startX++) {
            for (int startY = 20; startY < koniecY1; startY++) {

                Line linia2 = new Line(startX, startY, koniecX, koniecY);
                grupa.getChildren().addAll(linia2);
            }
        }
                primaryStage.setTitle("Tic Tac Toe - The Game");
                primaryStage.setScene(scene);
                primaryStage.show();
            }

        }


