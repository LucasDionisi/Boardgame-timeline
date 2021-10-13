/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeline;

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
public class Timeline extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*Button btn = new Button();
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
        primaryStage.show();*/
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);

        Player player1, player2;
        player1 = new Player("Toto");
        player2 = new Player("Tata");
        
        Board board = Board.getInstance();
        System.out.println("Number of cards: " +board.getNumberOfCardsLeft());
        Card card = board.drawCard();
        System.out.println("Card: " +card.getDate());
        System.out.println("Number of cards: " +board.getNumberOfCardsLeft());
    }
    
}
