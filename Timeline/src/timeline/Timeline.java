/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import timeline.game.Board;
import timeline.game.Player;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Timeline /*extends Application*/ {
    /*
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);

        Player player1, player2;
        player1 = new Player("Toto");
        player2 = new Player("Tata");
        
        Board board = Board.getInstance();
        
        for (int i = 0; i < Board.NB_CARDS_PER_PLAYER; i++) {
            player1.drawCard(board.drawCard());
            player2.drawCard(board.drawCard());
        }
        
        System.out.println(player1.toString());
        System.out.println(player2.toString());
        
        board.playCard(0, board.drawCard());
        
        boolean isPlaying = true;
        boolean isPlayer1Turn = true;
        
        Scanner scanner = new Scanner(System.in); 

        while (isPlaying) {
           System.out.println(board.playedCardsToString());
           System.out.println("Select a card to play.");
           System.out.println(isPlayer1Turn ? player1 : player2);
           
           int indexOfCard = Integer.parseInt(scanner.nextLine());
           Card cardPlayed = isPlayer1Turn ? player1.playCard(indexOfCard) : player2.playCard(indexOfCard);
           System.out.println(cardPlayed.toString());
           
           System.out.println("Where play the card?");
           int indexToPlay = Integer.parseInt(scanner.nextLine());
           
           if (board.isGoodPlay(indexToPlay, cardPlayed)) {
               board.playCard(indexToPlay, cardPlayed);
           } else {
               board.discardCard(cardPlayed);
               if (isPlayer1Turn) {
                   player1.drawCard(board.drawCard());
               } else  {
                   player2.drawCard(board.drawCard());
               }
           }
           isPlayer1Turn = !isPlayer1Turn;
           
           if (player1.getHand().isEmpty() ||player2.getHand().isEmpty())
               isPlaying = false;
        }
    }
    
}
