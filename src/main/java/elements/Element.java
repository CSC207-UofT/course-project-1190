package elements;

import utils.EnumsForSprites;
import utils.Point2D;
import utils.PointImagePair;

import java.io.Serializable;

public class Element implements Serializable {
    // This is the base class for all objects to be seeded on the board.
    private EnumsForSprites sprite;
    private Point2D pos;


    /**
     * A constructor fo the Element class. This is the base class for all elements on the board.
     * @param sprite the element's representation; currently set to String, but will be mapped to an
     * image by the GraphicsLoader.
     * @param pos the element's position on the board, represented by a pair of integer coordinates.
     */
    public Element(EnumsForSprites sprite, Point2D pos){
        this.sprite = sprite;
        this.pos = pos;
    }

    /**
     * @return the mapping between the location of this element and its representation.
     */
    public PointImagePair getPointImagePair() {
        return new PointImagePair(this.pos, this.sprite);
    }

    /**
     * @return the current position of the element
     */
    public Point2D getPos() {
        return this.pos;
    }

    /**
     * Sets the element to a new position on the board.
     * @param newPos new location of the element
     */
    public void setPos(Point2D newPos) {
        this.pos = newPos;
    }
}
