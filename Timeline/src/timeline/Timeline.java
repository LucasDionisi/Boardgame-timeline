/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import timeline.game.Board;
import timeline.game.Player;
import timeline.game.material.Card;
import timeline.game.tools.Position;

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
        this.player1 = new Player("Lucas", Position.TOP);
        this.player2 = new Player("Margot", Position.BOTTOM);
        
        Board board = Board.getInstance();
        
        for (int i = 0; i < Board.NB_CARDS_PER_PLAYER; i++) {
            player1.drawCard(board.drawCard());
            player2.drawCard(board.drawCard());
        }
        
        board.playCard(0, board.drawCard());
    }
    
    public void drawHand(Group root, Player player, boolean isPlayer1Turn) {
        float margin = WIDTH - (CARD_WIDTH * player.getHand().size()) - (CARD_MARGIN_HORIZONTAL * (player.getHand().size() - 1));
        
        Text text = new Text(10, player.isPositionTop() ? 40 : HEIGHT - 40, "Player: " +player.getPseudo());
        text.setFont(new Font(20));
        if (isPlayer1Turn && player.isPositionTop())
            text.setFill(Color.RED);
        root.getChildren().add(text);
        
        int index = 0;
        for (Card card : player.getHand()) {
            card.getRectangle().setX((margin / 2) + index *(CARD_WIDTH + CARD_MARGIN_HORIZONTAL));
            card.getRectangle().setY(player.isPositionTop() ? CARD_MARGIN_VERTICAL : HEIGHT - CARD_MARGIN_VERTICAL - CARD_HEIGHT);
            card.getRectangle().setWidth(CARD_WIDTH);
            card.getRectangle().setHeight(CARD_HEIGHT);
            
            Text date = new Text(card.getRectangle().getX(), card.getRectangle().getY() + CARD_HEIGHT - 5, Integer.toString(card.getDate()));
            date.setFont(new Font(20));
            date.setFill(Color.WHITE);
           
            card.getRectangle().setOnMouseClicked(handleMouseClick);
            root.getChildren().add(card.getRectangle());
            root.getChildren().add(date);
            index++;
        }
    }
   
    public void drawPlayedCards(Group root) {
        List<Card> playedCards = Board.getInstance().getPlayedCards();
        float margin = WIDTH - (CARD_WIDTH * playedCards.size()) - (CARD_MARGIN_HORIZONTAL * (playedCards.size() - 1));
        
        int index = 0;
        for (Card card : playedCards) {
            card.getRectangle().setX((margin / 2) + index * (CARD_WIDTH + CARD_MARGIN_HORIZONTAL));
            card.getRectangle().setY((HEIGHT / 2) - (CARD_HEIGHT / 2));
            card.getRectangle().setWidth(CARD_WIDTH);
            card.getRectangle().setHeight(CARD_HEIGHT);
            
            Text date = new Text(card.getRectangle().getX(), card.getRectangle().getY() + CARD_HEIGHT - 5, Integer.toString(playedCards.get(index).getDate()));
            date.setFont(new Font(20));
            date.setFill(Color.WHITE);
            
            card.getRectangle().setOnMouseClicked(handleMouseClick);
            
            root.getChildren().add(card.getRectangle());
            root.getChildren().add(date);
        }
    }
    
    private Card getCardClicked(double x, double y, List<Card> cards) {
        
        for (Card card : cards) {
            double cardX = card.getRectangle().getX();
            double cardY = card.getRectangle().getY();
            
            if ((x >= cardX && x <= (cardX + CARD_WIDTH)) && (y >= cardY && y <= (cardY + CARD_HEIGHT))) {
                return card;
            }
        }
        
        return null;
    }
    
    private Player getPlayerByCard(Card card, Player... players) {
        if (card == null || players == null || players.length <= 0) 
            return null;
        
        for (Player player : players) {
            for (Card pCard : player.getHand()) {
                if (pCard.equals(card)) return player;
            }
        }
        
        return null;
    }
    
    private Card getCardClicked(double x, double y) {
        if (y < HEIGHT/3) {
            return getCardClicked(x, y, player1.getHand());
        } else if (y < HEIGHT/3*2) {
            return getCardClicked(x, y, Board.getInstance().getPlayedCards());
        } else if (y < HEIGHT) {
            return getCardClicked(x, y, player2.getHand());
        }
        
        return null;
    }
    
    EventHandler<MouseEvent> handleMouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Card card = getCardClicked(mouseEvent.getX(), mouseEvent.getY());
            if (card != null) 
                System.out.println("Card: " +card.getDate());
            
            Player player = getPlayerByCard(card, player1, player2);
            if (player != null) 
                System.out.println("Player: " +player.getPseudo());
        }
    };
    
    @Override
    public void start(Stage primaryStage) {
        setup();
        isPlaying = true;
        isPlayer1Turn = true;
        
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        drawHand(root, player1, isPlayer1Turn);
        drawHand(root, player2, isPlayer1Turn);
        
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