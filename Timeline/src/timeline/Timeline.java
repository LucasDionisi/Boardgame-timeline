/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import timeline.game.Board;
import timeline.game.Player;
/**
 *
 * @author lucas
 */
public class Timeline extends Application {
    
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1280;
    
    Player player1, player2;
    boolean isPlaying, isPlayer1Turn;
    
    public void setup() {
        this.player1 = new Player("toto");
        this.player2 = new Player("tata");
        
        Board board = Board.getInstance();
        
        for (int i = 0; i < Board.NB_CARDS_PER_PLAYER; i++) {
            player1.drawCard(board.drawCard());
            player2.drawCard(board.drawCard());
        }
        
        board.playCard(0, board.drawCard());
    }
    
    @Override
    public void start(Stage primaryStage) {
        setup();
        isPlaying = true;
        isPlayer1Turn = true;
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Timeline");
        
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        /*
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
        */
    }
    
}
