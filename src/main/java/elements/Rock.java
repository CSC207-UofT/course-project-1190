package elements;

import utils.EnumsForSprites;
import utils.Point2D;

public class Rock extends Element{
    /**
     * A constructor for the Rock class, inherited from its parent class Element.
     * @param sprite the element's representation
     * @param pos the element's initial position
     */
    public Rock(EnumsForSprites sprite, Point2D pos, boolean isPermanent) {
        super(sprite, pos, isPermanent);
    }
}
