package timeline.game.material;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author lucas
 */
public class Card {
    private String description;
    private int date;
    private Rectangle rectangle;
    
    public Card(String descriprtion, int date) {
        this(descriprtion, date, new Rectangle());
    }
    
    public Card(String descriprtion, int date, Rectangle rectangle) {
        this.rectangle = rectangle;
        this.description = descriprtion;
        this.date = date;
    }
    
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    
    public void setRectangleX(double x) {
        this.rectangle.setX(x);
    }
    
    public void setRectangleY(double y) {
        this.rectangle.setY(y);
    }
    
    public void setRectangleWidth(double width) {
        this.rectangle.setWidth(width);
    }
    
    public void setRectangleHeight(double height) {
        this.rectangle.setHeight(height);
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
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("Description: ");
        stringBuilder.append(this.description);
        stringBuilder.append("\n");
        stringBuilder.append("Date: ");
        stringBuilder.append(this.date);
        stringBuilder.append("\n");
        
        return stringBuilder.toString();
    }
}
