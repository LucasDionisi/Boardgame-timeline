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
import javafx.scene.shape.Circle;
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
    
    private static final int STEP_1 = 1; // Select card
    private static final int STEP_2 = 2; // Select position
    private static final int STEP_3 = 3; // End of game
    
    private Group root;
    
    private Player player1, player2;
    private boolean isPlayer1Turn;
    private int step;
    private Card selectedCard;
    
    public void setup() {
        this.player1 = new Player("Lucas", Position.TOP);
        this.player2 = new Player("Margot", Position.BOTTOM);
        
        Board board = Board.getInstance();
        
        for (int i = 0; i < Board.NB_CARDS_PER_PLAYER; i++) {
            player1.drawCard(board.drawCard());
            player2.drawCard(board.drawCard());
        }
        
        this.step = STEP_1;
        board.playCard(0, board.drawCard());
    }
    
    public void drawHand(Player player, boolean isPlayer1Turn) {
        float margin = WIDTH - (CARD_WIDTH * player.getHand().size()) - (CARD_MARGIN_HORIZONTAL * (player.getHand().size() - 1));
        
        Text text = new Text(10, player.isPositionTop() ? 40 : HEIGHT - 40, "Player: " +player.getPseudo());
        text.setFont(new Font(20));
        if (isPlayer1Turn && player.isPositionTop()) text.setFill(Color.RED);
        if (!isPlayer1Turn && !player.isPositionTop()) text.setFill(Color.RED);
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
           
            card.getRectangle().setOnMouseClicked(handleMouseClickRect);
            root.getChildren().add(card.getRectangle());
            root.getChildren().add(date);
            index++;
        }
    }
   
    public void drawPlayedCards() {
        List<Card> playedCards = Board.getInstance().getPlayedCards();
        float margin = WIDTH - (CARD_WIDTH * playedCards.size()) - (CARD_MARGIN_HORIZONTAL * (playedCards.size() - 1));
        
        int index = 0;
        Circle circleBfr = new Circle();
        circleBfr.setCenterX(((margin / 2) + index * (CARD_WIDTH + CARD_MARGIN_HORIZONTAL)) - CARD_MARGIN_HORIZONTAL / 2);
        circleBfr.setCenterY(((HEIGHT / 2) - (CARD_HEIGHT / 2)) + CARD_HEIGHT / 2);
        circleBfr.setRadius(CARD_MARGIN_HORIZONTAL / 4);
        circleBfr.setFill(Color.GREEN);
        
        circleBfr.setOnMouseClicked(handleMouseClickCrcl);
        
        root.getChildren().add(circleBfr);
        
        for (Card card : playedCards) {
            Circle circle = new Circle();
            circle.setCenterX(((margin / 2) + index * (CARD_WIDTH + CARD_MARGIN_HORIZONTAL)) +CARD_WIDTH + CARD_MARGIN_HORIZONTAL/2);
            circle.setCenterY(((HEIGHT / 2) - (CARD_HEIGHT / 2)) + CARD_HEIGHT/2);
            circle.setRadius(CARD_MARGIN_HORIZONTAL/4);
            circle.setFill(Color.GREEN);
            
            card.getRectangle().setX((margin / 2) + index * (CARD_WIDTH + CARD_MARGIN_HORIZONTAL));
            card.getRectangle().setY((HEIGHT / 2) - (CARD_HEIGHT / 2));
            card.getRectangle().setWidth(CARD_WIDTH);
            card.getRectangle().setHeight(CARD_HEIGHT);
            
            Text date = new Text(card.getRectangle().getX(), card.getRectangle().getY() + CARD_HEIGHT - 5, Integer.toString(playedCards.get(index).getDate()));
            date.setFont(new Font(20));
            date.setFill(Color.WHITE);
            
            //card.getRectangle().setOnMouseClicked(handleMouseClickRect);
            circle.setOnMouseClicked(handleMouseClickCrcl);
            
            root.getChildren().add(circle);
            root.getChildren().add(card.getRectangle());
            root.getChildren().add(date);
            index++;
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
    
    private void step1(double x, double y) {
        Card card = getCardClicked(x, y);
        Player player = getPlayerByCard(card, player1, player2);

        if (player != null) {
            if ((isPlayer1Turn && player.equals(player1)) || (!isPlayer1Turn && player.equals(player2))) {
                this.selectedCard = card;
                
                System.out.println("Player: " +player.getPseudo() +", card: " +card.getDate());
                
                this.step = STEP_2;
            }
        }
    }
    
    int getIndexOfPlay(double x, double y, Card card) {
        
        List<Card> playedCards = Board.getInstance().getPlayedCards();
        
        for (int i = 0; i < playedCards.size(); i++) {
            if (x < playedCards.get(i).getRectangle().getX()) return i;
            if ((i == playedCards.size() - 1) && (x > playedCards.get(i).getRectangle().getX())) return (i+1);
        }
        
        return -1;
    }
    
    EventHandler<MouseEvent> handleMouseClickCrcl = new EventHandler<MouseEvent>() {
        @Override
        public void handle (MouseEvent mouseEvent) {
            if (step == STEP_2 && selectedCard != null) {
                int index = getIndexOfPlay(mouseEvent.getX(), mouseEvent.getY(), selectedCard);
                
                Board board = Board.getInstance();
                
                System.out.println("Play " +selectedCard.getDate() +" at index " +index);
                
                if (isPlayer1Turn) selectedCard = player1.playCard(selectedCard);
                else selectedCard = player2.playCard(selectedCard);

                if (selectedCard != null) {
                    if (board.isGoodPlay(index, selectedCard)) {
                        board.playCard(index, selectedCard);
                    } else {
                        System.out.println("This move is bad, draw a new card.");
                        board.discardCard(selectedCard);
                        if (isPlayer1Turn) player1.drawCard(board.drawCard());
                        else player2.drawCard(board.drawCard());
                    }
                }
                
                root.getChildren().clear();
                drawHand(player1, isPlayer1Turn);
                drawHand(player2, isPlayer1Turn);
                drawPlayedCards();
                
                if (player1.getHand().isEmpty() || player2.getHand().isEmpty()) {
                    step = STEP_3;
                    Text text = new Text(isPlayer1Turn ? 10 : HEIGHT - 40, WIDTH/2, "Player " +(isPlayer1Turn  ? "1 wins." : "2 wins."));
                    text.setFont(new Font(20));
                    text.setFill(Color.RED);
                    root.getChildren().add(text);
                }
                
                isPlayer1Turn = !isPlayer1Turn;
                step = STEP_1;
            }
        }
    };
    
    EventHandler<MouseEvent> handleMouseClickRect = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (step == STEP_1)
                    step1(mouseEvent.getX(), mouseEvent.getY());
        }
    };
    
    @Override
    public void start(Stage primaryStage) {
        setup();
        step = STEP_1;
        isPlayer1Turn = true;
        
        this.root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        drawHand(player1, isPlayer1Turn);
        drawHand(player2, isPlayer1Turn);
        
        drawPlayedCards();
        
        primaryStage.setTitle("Timeline");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}