package timeline.game;

/**
 *
 * @author lucas
 */
public class Board {
    private static Board instance;
    
    private Board() {
        //TODO
    }
    
    public Board getInstance() {
        if (this.instance == null) 
            instance = new Board();
        
        return this.instance;
    }
}
