package timeline.game;

import java.util.ArrayList;
import java.util.List;
import timeline.game.material.Card;

/**
 *
 * @author lucas
 */
public class Player {
    private String pseudo;
    private List<Card> hand;
    
    public Player(String pseudo) {
        this.pseudo = pseudo;
        
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
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("Player: ");
        stringBuilder.append(this.pseudo);
        stringBuilder.append("\n");
        
        for (Card card : this.hand) {
            stringBuilder.append(card.toString());
            stringBuilder.append("----------------\n");
        }
        
        return stringBuilder.toString();
    }
}
