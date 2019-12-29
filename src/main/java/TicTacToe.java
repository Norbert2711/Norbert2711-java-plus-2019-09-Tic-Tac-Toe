import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public Parent createContent() {

        BorderPane layout = new BorderPane();

        VBox vBox = new VBox();
        for (int i = 0; i < 3; ++i) {
            String blackOrWhite;
            if (i % 2 == 0) {
                blackOrWhite = "Black";
            } else {
                blackOrWhite = "White";
            }
            HBox hBox = new HBox();
            for (int j = 0; j < 3; j++) {
                Label label = new Label();
                label.setMinSize(200, 200);
                if (blackOrWhite.equals("Black")) {
                    label.setStyle("-fx-background-color: rgba(227,88,0,0.12)");
                    blackOrWhite = "White";
                } else {
                    label.setStyle("-fx-background-color: rgba(5,237,255,0.17)");
                    blackOrWhite = "Black";
                }
                hBox.getChildren().add(label);
            }
            vBox.getChildren().add(hBox);
        }

        vBox.setAlignment(Pos.CENTER);
        layout.setCenter(vBox);

        return layout;
    }

    @Override
    public void start(Stage stage) throws Exception {       //scena
        stage.setScene(new Scene(createContent()));
        stage.setWidth(620);        //dlugosc sceny
        stage.setHeight(655);       //wysokosc sceny
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}