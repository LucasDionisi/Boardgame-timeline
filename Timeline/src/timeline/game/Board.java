package timeline.game;

import java.util.List;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Board {
    private static Board instance;
    private List<Card> deck;
    
    private Board() {
        //TODO
    }
    
    public Board getInstance() {
        if (this.instance == null) 
            instance = new Board();
        
        return this.instance;
    }
}
