import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DrawingSample extends Application {
    public void start(Stage stage) {
        FlowPane flowPane = new FlowPane();
        Canvas canvas = new Canvas(900, 900);
        flowPane.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 300, 300);

        canvas.setOnMouseDragged((event) -> {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(event.getX(), event.getY(), 8, 8);
        });

        stage.setScene(new Scene(flowPane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(DrawingSample.class);
    }
}