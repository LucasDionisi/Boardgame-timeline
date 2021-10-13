package timeline.game;

import java.util.ArrayList;
import java.util.List;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Board {
    public final static int NB_CARDS_IN_DECK = 50;
    
    private static Board instance;
    private List<Card> deck;
    
    private Board() {
        if (deck == null)
            deck = new ArrayList<>();
        
        for (int i = 0; i < NB_CARDS_IN_DECK; i++) {
            this.deck.add(new Card("This is a description", 1950+i));
        }
    }
    
    public Board getInstance() {
        if (this.instance == null) 
            instance = new Board();
        
        return this.instance;
    }
}
