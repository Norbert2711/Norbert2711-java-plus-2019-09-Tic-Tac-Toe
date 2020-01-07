import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class ProbaTicTac extends Application {              //dziedziczy z Application

    private char playersTurn = 'X';        //zmienna pomocnicza okresla, ktory gracz ma teraz ruch
    private char pcMove = 'O';
    private Image imageback = new Image("paper.png");       //zdjecie tla - background
    private Cell[][] ourBoard = new Cell[3][3];     //Cell - komorki javafx
    private Label label = new Label();          //label torzy napis, pod plansza
    // private Label label1 = new Label();
    Button restartButton = new Button("RESTART GAME");   //restart button
    GridPane pane = new GridPane();
    TranslateTransition anim = new TranslateTransition();
    BorderPane borderPane = new BorderPane();


    @Override
    public void start(Stage primaryStage) throws Exception {

        // ChoiceBox choseGame1 = new ChoiceBox();
//       // ChoiceBox choseGame2 = new ChoiceBox();
        //choseGame1.setItems(FXCollections.observableArrayList("Player VS Computer"));
//       // choseGame2.setItems(FXCollections.observableArrayList("Player VS Player"));
        // borderPane.setLeft(choseGame1);
//       // borderPane.setLeft(choseGame2);


        //caly background
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);


        for (int i = 0; i < 3; i++)                 //petla tworzaca plansze z kolumnami 3x3
            for (int j = 0; j < 3; j++)              //dzieki gridPane mozna stworzyc kolumny, wiersze itd
                pane.add(ourBoard[i][j] = new Cell(), j, i);

        borderPane.setCenter(pane);
        borderPane.setBottom(label);
        borderPane.setRight(restartButton);
        borderPane.setRight(restartButton);
        Scene scene = new Scene(borderPane, 900, 900);      //rozmian czalej sceny (okna gry)
        primaryStage.setTitle("TicTacToeGame"); //napis na pasku sceny, tytul programu
        primaryStage.setScene(scene);            // scena umieszczona w podstaoej scenie
        primaryStage.show();                 // wyswietl scene
        borderPane.setBackground(background);

        //ponizej ustawienia Buttona do restartu gry (polozenie, wymiary)

        restartButton.setPrefHeight(50);
        restartButton.setPrefWidth(250);
        restartButton.setTranslateY(733);
        restartButton.setTranslateX(650);

        //event po kliku resetujacy scene a nastepnie tworzacy nowa scene

        restartButton.setOnAction(__ ->
        {
            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new ProbaTicTac().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });

        pane.getChildren().add(restartButton);      //dodanie buttona do children

        //ponizej ustawienia Labela pod plansza

        label.setMinWidth(650);
        label.setMinHeight(50);
        label.setTextFill(Color.DARKRED);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setText("This is my first simple project! Have fun! :)");
        label.setFont(new Font("Times New Roman", 22));
        label.setStyle("-fx-background-color:rgb(6,230,222)");

    }

    private void winnerAnimation(Text t) {          //animacja wygranej

        t.setFont(Font.font("Times New Roman", 20));
        t.setFill(Color.BLUE);


        anim.setDuration(Duration.seconds(4));
        anim.setAutoReverse(true);
        anim.setCycleCount(10);
        anim.setToY(550);
        anim.setToX(550);
        anim.setNode(t);
        anim.play();
        pane.getChildren().add(t);

    }

    private void drawAnimation() {          //animacja remisu

        Text t2 = new Text("Ops.. we have DRAW!");
        t2.setFont(Font.font("Times New Roman", 20));
        t2.setFill(Color.BLUE);

        TranslateTransition anim = new TranslateTransition();
        anim.setDuration(Duration.seconds(4));
        anim.setAutoReverse(true);
        anim.setCycleCount(10);
        anim.setToY(550);
        anim.setToX(550);
        anim.setNode(t2);
        anim.play();
        pane.getChildren().add(t2);
    }

    public boolean boardIsFullHaveDraw() {           //metoda spradzajaca czy plansza jest pelna.    spradza czy jakas
        // kolorka ('char' -tablica) z planszy jest pusta.
        // jesli nie jest, mozna wykonac ruch, jesli wartosc jest TRUE - czyli komorki sa pelne to gra
        // ozanajmnia, ze jest rememis (szystkie pola zapelnione)
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (ourBoard[i][j].getSign() == ' ')
                    return false;
        drawAnimation();
        return true;

    }
    //  [0,0] [0,1] [0,2]           uklad planszy
    //  [1,0] [1,1] [1,2]
    //  [2,0] [2,1] [2,2]

    //metoda sprawdza kto zostal zyciezca w danej rozgrywce .jesli za 3 takie same znaki to zraca TRUE

    public boolean weHaveWinner(char contentsOfTheCell) {       //PIONOWO [wartosc, 0], [wartosc, 1], [wartosc, 2]
        for (int i = 0; i < 3; i++)
            if (ourBoard[i][0].getSign() == contentsOfTheCell && ourBoard[i][1].getSign() == contentsOfTheCell && ourBoard[i][2].getSign() == contentsOfTheCell) {
                return true;
            }

        for (int j = 0; j < 3; j++)     //POZIOMO od 0 do 1 do 2 poziomo
            if (ourBoard[0][j].getSign() == contentsOfTheCell && ourBoard[1][j].getSign() == contentsOfTheCell && ourBoard[2][j].getSign() == contentsOfTheCell) {
                return true;
            }

        //SKOS od lewej gory do praego dolu
        if (ourBoard[0][0].getSign() == contentsOfTheCell && ourBoard[1][1].getSign() == contentsOfTheCell && ourBoard[2][2].getSign() == contentsOfTheCell) {
            return true;
        }

        //SKOS od prawej gory do lewego dolu
        if (ourBoard[0][2].getSign() == contentsOfTheCell && ourBoard[1][1].getSign() == contentsOfTheCell && ourBoard[2][0].getSign() == contentsOfTheCell) {
            return true;
        }
        return false;       //jesli false to petla dziala od nowa.
    }


    public class Cell extends Pane {      //klasa odpowiadajaca za komorke (kwadrat na planszy) daje kolor i wymiary kazdej z 3x3

        private char contentsOfTheCell = ' ';  //zawartosc komorki, to co jest X czy O -- jak narazie jest pusto.

        public Cell() {

            setStyle("-fx-border-color:black");


            //TU MOZE NASTAPIC WYBOR TRYBU GRY


            //:::java tlumaczy::: * Defines a function to be

            this.setPrefSize(2000, 2000);             // called when a
            this.setOnMouseClicked(e -> playerTurn());                      // mouse button has been clicked
            // this.setOnMouseClicked(e-> handleMouseClick());              //* (pressed and released) on this {@code Node}.
        }

        public char getSign() {                 //getter dla zawartosci komorki
            return contentsOfTheCell;
        }

        public void setSign(char signs) {

            contentsOfTheCell = signs;

            //parametry znaku X w komorce. Jezeli w komoece jest char X to z 2 lini
            //tworzy sie X o podanych wymiarach i polozeniu wzgledem komorki.

            if (contentsOfTheCell == 'X') {

                Line line1 = new Line(10, 10,
                        this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                line1.setStrokeWidth(5);    //grubosc
                line1.setStroke(Color.YELLOWGREEN); //kolor

                Line line2 = new Line(10, this.getHeight() - 10,
                        this.getWidth() - 10, 10);
                line2.startYProperty().bind(
                        this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));
                line2.setStrokeWidth(6);        //grubosc
                line2.setStroke(Color.DIMGREY); //kolor


                this.getChildren().addAll(line1, line2);        //dodanie w/w 2 lini do children Pane.
            }

            //parametry znaku O. jezeli nie ma X ale w komorce planszy znajduje sie O
            // to tworzy sie kolo, elipsa o podanych wymiarach

            else if (contentsOfTheCell == 'O') {

                Ellipse ellipse = new Ellipse(this.getWidth() / 2,
                        this.getHeight() / 2, this.getWidth() / 2 - 10,
                        this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(
                        this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(
                        this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(
                        this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(
                        this.heightProperty().divide(2).subtract(10));
                ellipse.setStrokeWidth(6);                  //grubosc kola
                ellipse.setStroke(Color.DARKRED);       //kolor obdodu kola
                ellipse.setFill(Color.TRANSPARENT);     //kolor wewnatrz kola

                getChildren().add(ellipse);     //dodanie w/w elipsy, kola do children Pane.
            }
        }

        //metoda odpowiadajaca za event przy kliknieciu mysza

        //WARUEK: jesli komorka nie ma zawartosci (pusta: ' ') lub gra nie zakonczyla sie czyli
        //playersTurn jest = true, to wtedy w komorce narusuje sie dany znak (X lub O).

        //OPCJA DLA 2 GRACZY, BEZ KOMPUTERA

        private void handleMouseClick() {
            if (contentsOfTheCell == ' ' && playersTurn != ' ') {
                setSign(playersTurn);

                // sprawdza sie status gry. jesli mamy zwyciezce, czyli zostana spelniony warunek 3 takich samych znakow
                //w poziomie, pionie lub skosie, to zostanie wyswietlony tekts w Label, z informacja ktory gracz wygral


                if (weHaveWinner(playersTurn)) {
                    Text t = new Text("* " + playersTurn + " *" + " " + "Has Won! GAME OVER!");
                    label.setText(playersTurn + " is win! Game Over! Now, you can restart the game ------------");
                    winnerAnimation(t);
                }

                //status gey - remis. podobna sytuacja j/w. tylko w tym momencie spelnia sie warunek metody, ktora
                //odowiada za remis w grze.

                else if (boardIsFullHaveDraw()) {
                    label.setText("We have DRAW. Game Over! Now, you can restart the game ------------");
                    drawAnimation();
                } else {
                    //jesli w/w warunki nie sa spelnione, brak wygranej, brak remisu, gra toczy sie dalej a gracze zamieniaja
                    //sie turami.
                    //tura gracza = jesli gracz X wykona ruch, to po nim kolej na ruch gracza O, jesli
                    // false to wykona ruch gracz X     (warunek ? jeśli true : jeśli false;)

                    playersTurn = (playersTurn == 'X') ? 'O' : 'X';

                }
            }
        }


//pomysl na metode: ustalenie wartosci min i max = sa to wartosci max i min, ktore sa losowane przez math random

        //petla while dziala nastepujaco: dziala dopoki loop jest = 0, losowane sa 2 liczby i oraz j od 0 do 2 przez Random
        //jesli nasza plansza w [i][j] ma znaj ' ' -- jest pusta, to nasza plansza we wzesniej wylosowanych [i][j]
        //wpisze znak O - czyli znak jakiego uzywa PC, potem petla sie przerwie, bo loop = 0 !=1.


        private void compTurn() {
            int loop = 0;
            while (loop == 0) {
                Random coordinate = new Random();
                int i = coordinate.nextInt(3);
                int j = coordinate.nextInt(3);
                if (ourBoard[i][j].getSign() == ' ') {
                    ourBoard[i][j].setSign(pcMove);
                    loop = 1;
                }
                if (weHaveWinner(pcMove)) {

                    label.setText(pcMove + " is win! Game Over! Now, you can restart the game ------------");
                    Text t = new Text("* " + pcMove + " *" + " " + "Has Won! GAME OVER!");
                    winnerAnimation(t);
                } else if (boardIsFullHaveDraw()) {
                    label.setText("We have DRAW. Game Over! Now, you can restart the game ------------");
                }
            }
        }


        private void playerTurn() {
            //jesli zawartosc komorki jest pusta ' ' lub/i gra nie jest skonczona / nie ruch gracza
            if (contentsOfTheCell == ' ' && playersTurn != ' ') {
                setSign(playersTurn);               // ustawienie znaku X w komorce

                //jesli warunki metody weHave winner spelnia gracz X to on wgrywa
                if (weHaveWinner(playersTurn)) {

                    label.setText(playersTurn + " is win! Game Over! Now, you can restart the game ------------");
                    Text t = new Text("* " + playersTurn + " *" + " " + "Has Won! GAME OVER!");
                    winnerAnimation(t);
                } else if (boardIsFullHaveDraw()) {
                    label.setText("We have DRAW. Game Over! Now, you can restart the game ------------");
                } else {
                    compTurn();     //gry gracz wykonal ruch, kolej na PC

                }
            }
        }
    }

    public static void main(String[] args) {        //klasa main, ktora "startuje" program z launch
        launch(args);
    }
}