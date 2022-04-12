package com.example.snakenladders;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class DiceRollSnake extends Application {

    //10 rows and 10 columns. A tile is given the side length of 80
    public static final int Tile_size = 60 ;
    public static final int Tile_width = 10 ;
    public static final int Tile_height = 10 ;

    public Label Dice_label = new Label("");
    public Label turn_name = new Label("Start");

    public boolean player1_unlocked = false ;
    public boolean player2_unlocked = false ;

    public Circle Player1;
    public Circle Player2;

    public static int alternate = 1;

    public static int player1_X_coordinate = 30;     //making sure that the circle is in the center of the tile so 60/2 = 30
    public static int player1_Y_coordinate = 620;    //we have to start from the bottonmost leftmost tile so -> 60*10 - 30

    public static int player2_X_coordinate = 60;     //making sure that the circle is in the center of the tile so 60/2 = 30
    public static int player2_Y_coordinate = 620;    //we have to start from the bottonmost leftmost tile so -> 60*10 - 30

    Button Dice = new Button("Dice" ) ;

    private Group game = new Group() ;
    public Parent Mainfunction() {
        Pane root = new Pane() ;
         root.getChildren().addAll(game) ;

         //Creating the table here
         for (int i = 0; i< Tile_height; i++) {
             for (int j = 0; j< Tile_width; j++) {
                Tile tile = new Tile(Tile_size,Tile_size) ;
                tile.setTranslateX(j * Tile_size);
                tile.setTranslateY(i * Tile_size);
                game.getChildren().add(tile) ;
             }
         }

         Player1 = new Circle(25) ;
         Player1.setId("Player1") ;
         Player1.setFill(Color.RED);
//         Player1.getStyleClass().add("D:\\SnakeNLadders\\src\\main\\resources\\com\\example\\snakenladders\\style.css") ;
         Player1.setTranslateX(player1_X_coordinate) ;
         Player1.setTranslateY(player1_Y_coordinate) ;

         Player2 = new Circle(25) ;
         Player2.setId("Player2") ;
         Player2.setFill(Color.BLUE);
//         Player2.getStyleClass().add("D:\\SnakeNLadders\\src\\main\\resources\\com\\example\\snakenladders\\style.css") ;
         Player2.setTranslateX(player2_X_coordinate) ;
         Player2.setTranslateY(player2_Y_coordinate) ;
        Dice.setTranslateX(300) ;
        Dice.setTranslateY(620) ;

        Dice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    int val = getDiceValue() ;
                    Dice_label.setText(String.valueOf(val)) ;
                    if (alternate%2==1) {
                        turn_name.setText("Player 1 turn");
                    }
                    else
                        turn_name.setText("Player 2 turn");

                    if (alternate%2==1 && val == 1 && player1_unlocked == false) {
                        player1_unlocked = true ;
                        player1_X_coordinate = -30 ;
                        player1_Y_coordinate = 570 ;
                    }
                    if (alternate%2==0 && val == 1 && player2_unlocked == false) {
                        player2_unlocked = true ;
                        player2_X_coordinate = -30 ;
                        player2_Y_coordinate = 570 ;
                    }
                    if (alternate%2==1 && player1_unlocked) {
                        FinalPostion_P1(val) ;
                        MoveToken(player1_X_coordinate, player1_Y_coordinate, Player1) ;

                        if (player1_X_coordinate <=30 && player1_Y_coordinate <=30){ //Winning COndition
                            AlertBox.display("Player 1 won", "Congratulations" ) ;
                        }
                        //Ladders
                        if (player1_X_coordinate == 150 && player1_Y_coordinate == 570) {  // 3 -> 21
                            MoveToken(player1_X_coordinate = 30, player1_Y_coordinate = 450, Player1) ;
                        }
                        if (player1_X_coordinate == 450 && player1_Y_coordinate == 570) {  // 8 -> 46
                            MoveToken(player1_X_coordinate = 330, player1_Y_coordinate = 330, Player1);
                        }
                        if (player1_X_coordinate == 270 && player1_Y_coordinate ==  510) { //16 -> 26
                            MoveToken(player1_X_coordinate = 330, player1_Y_coordinate = 450, Player1) ;
                        }
                        if (player1_X_coordinate == 510 && player1_Y_coordinate == 450) {  //29 -> 33
                            MoveToken(player1_X_coordinate = 450, player1_Y_coordinate = 390, Player1) ;
                        }
                        if (player1_X_coordinate == 210 && player1_Y_coordinate == 390) { //37 -> 65
                            MoveToken(player1_X_coordinate = 270, player1_Y_coordinate = 210, Player1) ;
                        }
                        if (player1_X_coordinate == 570 && player1_Y_coordinate == 330) {  //50 -> 70
                            MoveToken(player1_X_coordinate = 570, player1_Y_coordinate = 210, Player1) ;
                        }
                        if (player1_X_coordinate == 30 && player1_Y_coordinate == 210) { //61 -> 82
                            MoveToken(player1_X_coordinate = 90, player1_Y_coordinate = 90, Player1) ;
                        }
                        if (player1_X_coordinate == 210 && player1_Y_coordinate == 210) { //64 -> 77
                            MoveToken(player1_X_coordinate = 210, player1_Y_coordinate = 150, Player1) ;
                        }
                        if (player1_X_coordinate == 270 && player1_Y_coordinate == 150) { //76 -> 95
                            MoveToken(player1_X_coordinate = 330, player1_Y_coordinate = 30, Player1) ;
                        }
                        if (player1_X_coordinate == 510 && player1_Y_coordinate == 90) { // 89 -> 91
                            MoveToken(player1_X_coordinate = 570, player1_Y_coordinate = 30, Player1) ;
                        }

                        //Snakes
                        if (player1_X_coordinate == 210 && player1_Y_coordinate == 450) { // 24->5
                            MoveToken(player1_X_coordinate = 270, player1_Y_coordinate = 570, Player1) ;
                        }
                        if (player1_X_coordinate == 150 && player1_Y_coordinate == 330) { // 43 -> 22
                            MoveToken(player1_X_coordinate = 90, player1_Y_coordinate = 450, Player1) ;
                        }
                        if (player1_X_coordinate == 270 && player1_Y_coordinate == 270) { //56 -> 25
                            MoveToken(player1_X_coordinate = 270, player1_Y_coordinate = 450, Player1) ;
                        }
                        if (player1_X_coordinate == 30 && player1_Y_coordinate == 270) { //60 -> 42
                            MoveToken(player1_X_coordinate = 90, player1_Y_coordinate = 330, Player1) ;
                        }
                        if (player1_X_coordinate == 510 && player1_Y_coordinate == 210) { // 69 -> 48
                            MoveToken(player1_X_coordinate = 450, player1_Y_coordinate = 330, Player1) ;
                        }
                        if (player1_X_coordinate == 330 && player1_Y_coordinate == 90) { //86 -> 53
                            MoveToken(player1_X_coordinate = 450, player1_Y_coordinate = 270, Player1) ;
                        }
                        if (player1_X_coordinate == 570 && player1_Y_coordinate == 90) { //90 -> 72
                            MoveToken(player1_X_coordinate = 510, player1_Y_coordinate = 150, Player1) ;
                        }
                        if (player1_X_coordinate == 390 && player1_Y_coordinate == 30) {  //94 -> 73
                            MoveToken(player1_X_coordinate = 450, player1_Y_coordinate = 150, Player1) ;
                        }
                        if (player1_X_coordinate == 270 && player1_Y_coordinate == 30) { //96 -> 84
                            MoveToken(player1_X_coordinate = 210, player1_Y_coordinate = 90, Player1) ;
                        }
                        if (player1_X_coordinate == 150 && player1_Y_coordinate == 30) { //98 -> 58
                            MoveToken(player1_X_coordinate = 150, player1_Y_coordinate = 270, Player1) ;
                        }
                    }
                    if (alternate%2==0 && player2_unlocked) {
                        FinalPostion_P2(val) ;
                        MoveToken(player2_X_coordinate, player2_Y_coordinate, Player2) ;
                        if (player2_X_coordinate <=30 && player2_Y_coordinate <=30){ //Winning COndition
                            AlertBox.display("Player 2 won", "Congratulations" ) ;
                        }
                        //Ladders
                        if (player2_X_coordinate == 150 && player2_Y_coordinate == 570) {  // 3 -> 21
                            MoveToken(player2_X_coordinate = 30, player2_Y_coordinate = 450, Player2) ;
                        }
                        if (player2_X_coordinate == 450 && player2_Y_coordinate == 570) {  // 8 -> 46
                            MoveToken(player2_X_coordinate = 330, player2_Y_coordinate = 330, Player2);
                        }
                        if (player2_X_coordinate == 270 && player2_Y_coordinate ==  510) { //16 -> 26
                            MoveToken(player2_X_coordinate = 330, player2_Y_coordinate = 450, Player2) ;
                        }
                        if (player2_X_coordinate == 510 && player2_Y_coordinate == 450) {  //29 -> 33
                            MoveToken(player2_X_coordinate = 450, player2_Y_coordinate = 390, Player2) ;
                        }
                        if (player2_X_coordinate == 210 && player2_Y_coordinate == 390) { //37 -> 65
                            MoveToken(player2_X_coordinate = 270, player2_Y_coordinate = 210, Player2) ;
                        }
                        if (player2_X_coordinate == 570 && player2_Y_coordinate == 330) {  //50 -> 70
                            MoveToken(player2_X_coordinate = 570, player2_Y_coordinate = 210, Player2) ;
                        }
                        if (player2_X_coordinate == 30 && player2_Y_coordinate == 210) { //61 -> 82
                            MoveToken(player2_X_coordinate = 90, player2_Y_coordinate = 90, Player2) ;
                        }
                        if (player2_X_coordinate == 210 && player2_Y_coordinate == 210) { //64 -> 77
                            MoveToken(player2_X_coordinate = 210, player2_Y_coordinate = 150, Player2) ;
                        }
                        if (player2_X_coordinate == 270 && player2_Y_coordinate == 150) { //76 -> 95
                            MoveToken(player2_X_coordinate = 330, player2_Y_coordinate = 30, Player2) ;
                        }
                        if (player2_X_coordinate == 510 && player2_Y_coordinate == 90) { // 89 -> 91
                            MoveToken(player2_X_coordinate = 570, player2_Y_coordinate = 30, Player2) ;
                        }

                        //Snakes
                        if (player2_X_coordinate == 210 && player2_Y_coordinate == 450) { // 24->5
                            MoveToken(player2_X_coordinate = 270, player2_Y_coordinate = 570, Player2) ;
                        }
                        if (player2_X_coordinate == 150 && player2_Y_coordinate == 330) { // 43 -> 22
                            MoveToken(player2_X_coordinate = 90, player2_Y_coordinate = 450, Player2) ;
                        }
                        if (player2_X_coordinate == 270 && player2_Y_coordinate == 270) { //56 -> 25
                            MoveToken(player2_X_coordinate = 270, player2_Y_coordinate = 450, Player2) ;
                        }
                        if (player2_X_coordinate == 30 && player2_Y_coordinate == 270) { //60 -> 42
                            MoveToken(player2_X_coordinate = 90, player2_Y_coordinate = 330, Player2) ;
                        }
                        if (player2_X_coordinate == 510 && player2_Y_coordinate == 210) { // 69 -> 48
                            MoveToken(player2_X_coordinate = 450, player2_Y_coordinate = 330, Player2) ;
                        }
                        if (player2_X_coordinate == 330 && player2_Y_coordinate == 90) { //86 -> 53
                            MoveToken(player2_X_coordinate = 450, player2_Y_coordinate = 270, Player2) ;
                        }
                        if (player2_X_coordinate == 570 && player2_Y_coordinate == 90) { //90 -> 72
                            MoveToken(player2_X_coordinate = 510, player2_Y_coordinate = 150, Player2) ;
                        }
                        if (player2_X_coordinate == 390 && player2_Y_coordinate == 30) {  //94 -> 73
                            MoveToken(player2_X_coordinate = 450, player2_Y_coordinate = 150, Player2) ;
                        }
                        if (player2_X_coordinate == 270 && player2_Y_coordinate == 30) { //96 -> 84
                            MoveToken(player2_X_coordinate = 210, player2_Y_coordinate = 90, Player2) ;
                        }
                        if (player2_X_coordinate == 150 && player2_Y_coordinate == 30) { //98 -> 58
                            MoveToken(player2_X_coordinate = 150, player2_Y_coordinate = 270, Player2) ;
                        }
                    }
                alternate+=1 ;
            }
        });

         turn_name.setTranslateX(400) ;
         turn_name.setTranslateY(620) ;
         Dice_label.setTranslateX(210);
         Dice_label.setTranslateY(620);
         Image img = new Image("D:\\SnakeNLadders\\src\\main\\resources\\com\\example\\snakenladders\\board.jpeg") ;
         ImageView bgimage = new ImageView() ;
         bgimage.setImage(img) ;
         bgimage.setFitHeight(600);
         bgimage.setFitWidth(600);
         game.getChildren().addAll(turn_name,Dice,bgimage, Player1, Player2, Dice_label) ;
         return root ;
    }

    public int getDiceValue() {
        return 1 + (int) (Math.random()*6) ;
    }

    public void FinalPostion_P1(int diceval) {
        int var = 0 ;
        while (var<diceval) {
            if ( player1_Y_coordinate == 90 || player1_Y_coordinate == 210 || player1_Y_coordinate == 330 || player1_Y_coordinate == 450 || player1_Y_coordinate == 570) {
                player1_X_coordinate += 60 ;
            }
            else
                player1_X_coordinate -= 60 ;
            if (player1_X_coordinate <=30 && player1_Y_coordinate <= 30) {
                player1_X_coordinate = 30 ;
                player1_Y_coordinate = 30 ;
                Dice_label.setText("Player 1 Won" ) ;
                Dice.setDisable(true) ;
            }
            else if (player1_X_coordinate > 570) {   // to move to the upper row from right side
                player1_X_coordinate -= 60 ;
                player1_Y_coordinate -= 60 ;
            }
            else if (player1_X_coordinate < 30) {    // to move to upper row from left side
                player1_Y_coordinate -= 60 ;
                player1_X_coordinate += 60 ;
            }
            var++ ;
        }
    }


    public void FinalPostion_P2(int diceval) {
        int var = 0 ;
        while (var<diceval) {
            if ( player2_Y_coordinate == 90 || player2_Y_coordinate == 210 || player2_Y_coordinate == 330 || player2_Y_coordinate == 450 || player2_Y_coordinate == 570) {
                player2_X_coordinate += 60 ;
            }
            else
                player2_X_coordinate -= 60 ;

            if (player2_X_coordinate <=30 && player2_Y_coordinate <= 30) {
                player2_X_coordinate = 30 ;
                player2_Y_coordinate = 30 ;
                Dice_label.setText("Player 2 Won" ) ;
                Dice.setDisable(true) ;
            }
            else if (player2_X_coordinate < 30) { // to move from left side
                player2_Y_coordinate -= 60 ;
                player2_X_coordinate += 60 ;
            }
            else if (player2_X_coordinate > 570) { // to move from right side
                player2_X_coordinate -= 60 ;
                player2_Y_coordinate -= 60 ;
            }
            var++ ;
        }
    }

    private void MoveToken(int x, int y, Circle cir) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(500),cir) ;
        animate.setToX(x) ;
        animate.setToY(y) ;
        animate.setAutoReverse(false);
        animate.play() ;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(Mainfunction());
        stage.setTitle("Snake & Ladders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}