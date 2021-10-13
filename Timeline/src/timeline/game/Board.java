package timeline.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Board {
    public final static int NB_CARDS_IN_DECK = 50;
    public final static int NB_CARDS_PER_PLAYER = 4;
    
    private static Board instance;
    private List<Card> deck;
    
    private Board() {
        if (deck == null)
            deck = new ArrayList<>();
        
        for (int i = 0; i < NB_CARDS_IN_DECK; i++) {
            this.deck.add(new Card("This is a description", 1950+i));
        }
        
        shuffleDeck();
    }
    
    public static Board getInstance() {
        if (instance == null) 
            instance = new Board();
        
        return instance;
    }
    
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    public Card drawCard() {
        if (this.deck != null &&  !this.deck.isEmpty())
            return this.deck.remove(0);
        else
            return null;
    }
    
    public int getNumberOfCardsLeft() {
        return this.deck.size();
    }
}
