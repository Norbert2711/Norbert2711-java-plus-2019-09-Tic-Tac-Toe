import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeBoard extends Application {

    private boolean playerTurnX = true;
    private boolean playable = true;            //zmienna pomocnicza , okresla czy mozna grac dalej
    private Plate[][] stage = new Plate[3][3];
    private List<CheckingTheWinner> winner = new ArrayList<>();
          //listy przechowujace


    private GridPane board = new GridPane();

    public Parent createBoard() {            //metoda tworzy plansze 900 /900.      parent czyt.

        board.setPrefSize(910, 910);

        for (int i = 0; i < 3; i++) {                    //petla for dodaje 3 rzedy nastepnie kolejne 3 rzedy prostokatow
            for (int j = 0; j < 3; j++) {
                Plate rectangle = new Plate();
                rectangle.setTranslateX(j * 300);
                rectangle.setTranslateY(i * 300);
                board.getChildren().add(rectangle);
                stage[j][i] = rectangle;


            }
        }
        for (int y = 0; y < 3; y++) {       //zwyciezca, gdy znaki sa POZIOMO
            winner.add(new CheckingTheWinner(stage[0][y], stage[1][y], stage[2][y]));

        }
        for (int x = 0; x < 3; x++) {       //zwyciezca, gdy znaki sa PIONOWO
            winner.add(new CheckingTheWinner(stage[x][0], stage[x][1], stage[x][2]));
        }
        //zwyciezca, gdy znaki sa SKOS od lewej gory
        winner.add(new CheckingTheWinner(stage[0][0], stage[1][1], stage[2][2]));
        //zwyciezca, gdy znaki sa SKOS od pawej gory
        winner.add(new CheckingTheWinner(stage[2][0], stage[1][1], stage[0][2]));

//        //mozliwe remisy z udzialem O - kolko
//        draw.add(new CheckingTheWinner(stage[0][0],stage[1][1],stage[1][2],stage[2][1])); //v1
//        draw.add(new CheckingTheWinner(stage[0][1],stage[1][1],stage[2][0],stage[2][2])); //v2
//        draw.add(new CheckingTheWinner(stage[0][0],stage[0][2],stage[1][1],stage[2][1])); //v3
//        draw.add(new CheckingTheWinner(stage[0][2],stage[1][0],stage[1][1],stage[2][1])); //v4
//        draw.add(new CheckingTheWinner(stage[0][0],stage[1][1],stage[1][2],stage[2][0])); //v5
//        draw.add(new CheckingTheWinner(stage[0][0],stage[1][1],stage[1][2],stage[2][0])); //v6
//        draw.add(new CheckingTheWinner(stage[0][2],stage[1][0],stage[1][1],stage[2][2])); //v7
//        draw.add(new CheckingTheWinner(stage[0][1],stage[1][1],stage[1][2],stage[2][0])); //v8

        return board;

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Tic - Toc - Toe GAME");
        primaryStage.setScene(new Scene(createBoard()));        //wywolanie metody tworzacej scene
        primaryStage.show();                        //show wyswietlenie sceny

    }

    private void gameStatus() {          //metoda
        for (CheckingTheWinner win : winner) {
            if (win.weHaveWiner()) {
                playable = false;
                winnerAnimation(win);

                        break;

                }
            }
        }


    private void winnerAnimation(CheckingTheWinner win) {          //animacja wygranej

        Text t = new Text("GAME OVER!");
        t.setFont(Font.font(50));
        t.setFill(Color.RED);

        TranslateTransition anim = new TranslateTransition();
        anim.setDuration(Duration.seconds(4));
        anim.setAutoReverse(true);
        anim.setCycleCount(10);
        anim.setToY(450);
        anim.setToX(600);
        anim.setNode(t);
        anim.play();
        board.getChildren().add(t);
        System.out.println(" ***** GAME OVER! ***** ");
        System.out.println(" ***** You can Try again ***** ");
    }

    private void drawAnimation(CheckingTheWinner draw) {

        Text t1 = new Text("DRAW!");
        t1.setFont(Font.font(50));
        t1.setFill(Color.YELLOW);

        TranslateTransition anim1 = new TranslateTransition();

        anim1.setDuration(Duration.seconds(4));
        anim1.setAutoReverse(true);
        anim1.setCycleCount(10);
        anim1.setToY(450);
        anim1.setToX(600);
        anim1.setNode(t1);
        anim1.play();
        board.getChildren().add(t1);
        System.out.println("DRAW");

    }

    public class CheckingTheWinner {           //metoda spadza pion, poziom, skos czy sa 3 takie same znaki (eqals)
        private Plate[] plates;

        public CheckingTheWinner(Plate... plates) {
            this.plates = plates;
        }

        public boolean weHaveWiner() {          //metoda z wygranym
            if (plates[0].getValue().isEmpty())
                return false;
            return plates[0].getValue().equals(plates[1].getValue()) && plates[0].getValue().equals(plates[2].getValue());

        }

//        public boolean isDraw() {
//            if (plates[0].getValue().isEmpty())
//                return true;
//            return !plates[0].getValue().equals(plates[1].getValue()) && plates[0].getValue().equals(plates[2].getValue());
//
//        }
    }

    private class Plate extends StackPane { //czyt.

        private Text text = new Text();     //obiekt z billioteli text

        public Plate() {
            Rectangle circuit = new Rectangle(300, 300);
            circuit.setFill(null);            //setfill czyt.
            circuit.setStroke(Color.FORESTGREEN);                //kolor obwodu prostokatow planszy(RAMKA)
            circuit.setStrokeWidth(10);                      //grubosc lini planszy 3x3

            text.setFont(Font.font(200));                //wielkosc czcionki, znaczka "X"
            setAlignment(Pos.CENTER);
            getChildren().addAll(circuit, text);      //dodanie ramki kwadratow oraz obietku text(klikacza) do children

            setOnMouseClicked(event -> {
                if (!playable)
                    return;
                if (event.getButton() == MouseButton.PRIMARY) {

                    if (!playerTurnX)
                        return;
                    System.out.println("Move X");

                    drawX();

                    playerTurnX = false;
                    gameStatus();           //animacja przy wgraniu X

                } else if (event.getButton() == MouseButton.SECONDARY) {

                    if (playerTurnX)
                        return;
                    System.out.println("Move O");
                    drawO();

                    playerTurnX = true;
                    gameStatus(); //animacja przy wgraniu O

                }
            });
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            text.setText("X");
        }                               //2 metody wypisujace X lub O.

        private void drawO() {
            text.setText("O");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

