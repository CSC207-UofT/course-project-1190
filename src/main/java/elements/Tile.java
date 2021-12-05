package elements;


import java.io.Serializable;
import utils.EnumsForSprites;

public class Tile implements Serializable {
    boolean isTraversable;
    private EnumsForSprites sprite;

    /**
     * A constructor for the Tile class.
     * @param isTraversable indicates whether a Player can move onto
     * this element's position on the board.
     */
    public Tile(Boolean isTraversable) {
        this.isTraversable = isTraversable;
        if (isTraversable){
            this.sprite = EnumsForSprites.IS_TRAVERSABLE;
        }
        else {
            this.sprite = EnumsForSprites.NOT_TRAVERSABLE;
        }
    }

    /**
     * @return whether a Player can move onto this element's position
     */
    public boolean isTraversable() {
     return this.isTraversable;
    }

    /**
     * @return this element's representation
     */
    public EnumsForSprites getSprite() {
        return this.sprite;
    }
}
