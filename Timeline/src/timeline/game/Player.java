package timeline.game;

import java.util.ArrayList;
import java.util.List;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Player {
    private List<Card> hand;
    
    public Player() {
        if (hand == null)
            hand = new ArrayList<>();
    }
    
    public List<Card> getHand() {
        return this.hand;
    }
    
    public void drawCard(Card card) {
        this.hand.add(card);
    }
    
    public Card playCard(int index) {
        Card card;
        try {
          card = this.hand.remove(index);
          return card;
        } catch (Exception exception) {
            return null;
        }
    }
}
