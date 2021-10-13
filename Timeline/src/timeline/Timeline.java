/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timeline.game.Board;
import timeline.game.Player;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Timeline extends Application {
    
    private static final int HEIGHT = 720;
    private static final int WIDTH = 1280;
    
    private static final int CARD_HEIGHT = 100;
    private static final int CARD_WIDTH = 70;
    
    private static final int CARD_MARGIN_HORIZONTAL = 40;
    private static final int CARD_MARGIN_VERTICAL = 20;
    
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
    
    public void drawHands(Group root) {
        float marginP1 = WIDTH - (CARD_WIDTH * player1.getHand().size()) - (CARD_MARGIN_HORIZONTAL * (player1.getHand().size() - 1));
        float marginP2 = WIDTH - (CARD_WIDTH * player2.getHand().size()) - (CARD_MARGIN_HORIZONTAL * (player2.getHand().size() - 1));
        
        Text textP1 = new Text(10, 40, "Player: " +player1.getPseudo());
        textP1.setFont(new Font(20));
        root.getChildren().add(textP1);
        for (int i = 0; i < player1.getHand().size(); i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX((marginP1 / 2) + i *(CARD_WIDTH + CARD_MARGIN_HORIZONTAL));
            rectangle.setY(CARD_MARGIN_VERTICAL);
            rectangle.setWidth(CARD_WIDTH);
            rectangle.setHeight(CARD_HEIGHT);
            
            root.getChildren().add(rectangle);
        }
        
        Text textP2 = new Text(10, HEIGHT - 40, "Player: " +player2.getPseudo());
        textP2.setFont(new Font(20));
        root.getChildren().add(textP2);
        for (int i = 0; i < player2.getHand().size(); i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX((marginP2 / 2) + i *(CARD_WIDTH + CARD_MARGIN_HORIZONTAL));
            rectangle.setY(HEIGHT - CARD_MARGIN_VERTICAL - CARD_HEIGHT);
            rectangle.setWidth(CARD_WIDTH);
            rectangle.setHeight(CARD_HEIGHT);
            
            root.getChildren().add(rectangle);
        }
    }
    
    public void drawPlayedCards(Group root) {
        List<Card> playedCards = Board.getInstance().getPlayedCards();
        float margin = WIDTH - (CARD_WIDTH * playedCards.size()) - (CARD_MARGIN_HORIZONTAL * (playedCards.size() - 1));
        
        for (int i = 0; i < playedCards.size(); i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setX((margin / 2) + i * (CARD_WIDTH + CARD_MARGIN_HORIZONTAL));
            rectangle.setY((HEIGHT / 2) - (CARD_HEIGHT / 2));
            rectangle.setWidth(CARD_WIDTH);
            rectangle.setHeight(CARD_HEIGHT);
            
            root.getChildren().add(rectangle);
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        setup();
        isPlaying = true;
        isPlayer1Turn = true;
        
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        drawHands(root);
        drawPlayedCards(root);
        
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
