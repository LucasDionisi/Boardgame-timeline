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
    private List<Card> discard;
    private List<Card> playedCards;
    
    private Board() {
        if (deck == null)
            deck = new ArrayList<>();
        
        if (discard == null)
            discard = new ArrayList<>();
        
        if (playedCards == null)
            playedCards = new ArrayList<>();
        
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
    
    private void shuffleDeck() {
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

    public void discardCard(Card card) {
        if (this.discard != null && card != null)
            this.discard.add(card);
    }
    
    public boolean isGoodPlay(int index, Card card) {
        int indexPrv, indexNxt;
        indexPrv = index - 1;
        indexNxt = index;
        
        if (indexPrv >= 0 && indexPrv < this.playedCards.size()) {
            if (card.getDate() < this.playedCards.get(indexPrv).getDate()) 
                return false;
        }
        
        if (indexNxt >= 0 && indexNxt < this.playedCards.size()) {
            if (card.getDate() > this.playedCards.get(indexNxt).getDate())
                return false;
        }
        
        return true;
    }
    
    public void playCard(int index, Card card) {
        if (this.playedCards != null && card != null)
            this.playedCards.add(index, card);
    }
    
    public List<Card> getPlayedCards() {
        return this.playedCards;
    }
    
    public String playedCardsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Played cards :\n");
        
        for (Card card : this.playedCards) {
            stringBuilder.append(card.toString());
            stringBuilder.append("--------------\n");
        }
        
        return stringBuilder.toString();
    }
}
