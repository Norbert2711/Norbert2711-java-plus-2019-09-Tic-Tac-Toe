import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;


public class MemoryGame extends Application implements EventHandler<ActionEvent> {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage boardGame) throws Exception {
        boardGame.setTitle("Tic - Toc - Toe GAME");

        StackPane size = new StackPane();

        Button button = new Button("press");
        size.getChildren().add(button);
        button.setOnAction(this::handle);

        Scene scene = new Scene(size, 900, 900);
        boardGame.setScene(scene);
        boardGame.show();

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("rylo");
    }
}