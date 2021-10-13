package timeline.game.material;

/**
 *
 * @author lucas
 */
public class Card {
    private String description;
    private int date;
    
    public Card(String descriprtion, int date) {
        this.description = descriprtion;
        this.date = date;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDate(int date) {
        this.date = date;
    }
    
    public int getDate() {
        return this.date;
    }
}
