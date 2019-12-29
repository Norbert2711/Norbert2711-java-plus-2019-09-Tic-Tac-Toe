import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventFiltersExample extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Circle
        Circle circle = new Circle();

        //Setting the position of the circle
        circle.setCenterX(300.0f);          //polozenie kola
        circle.setCenterY(135.0f);

        //Setting the radius of the circle
        circle.setRadius(25.0f);  //rozmiar kola

        //Setting the color of the circle
        circle.setFill(Color.YELLOW);     //poczatkowy kolor kola

        //Ustawianie szerokości obrysu koła
        circle.setStrokeWidth(20);

        //Setting the text
        Text text = new Text("Click on the circle to change its color. Moj dopisek");    //text wwyswietlajacy sie nad kolem

        //Setting the font of the text
        text.setFont(Font.font(null, FontWeight.BOLD, 15)); //czcionka w/w tekstu

        //Setting the color of the text
        text.setFill(Color.BLUE);   //kolor czcionki

        //setting the position of the text
        text.setX(150); //pozycja tekstu
        text.setY(50);

        //Creating the mouse event handler
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Hello World");
                circle.setFill(Color.RED);      //zmiana koloru po kliku
            }
        };
        //Registering the event filter
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        //Creating a Group object
        Group root = new Group(circle, text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting the fill color to the scene
        scene.setFill(Color.LAVENDER);

        stage.setTitle("Tic Tac Toe - Game");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String args[]){
        launch(args);
    }
}