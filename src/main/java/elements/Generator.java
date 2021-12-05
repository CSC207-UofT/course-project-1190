package elements;

import utils.EnumsForSprites;
import utils.Point2D;

public abstract class Generator extends Element implements Resettable {
    Point2D direction;
    int max_tick;
    int counter;
    int bound;

    /**
     * A constructor for the Generator class.
     * @param sprite the representation of this element
     * @param pos the initial position of this element
     * @param direction the direction in which the generated objects move
     * @param max_tick the number of frame ticks before the next movement
     * @param bound the movement boundary of the generated objects
     */
    public Generator(EnumsForSprites sprite, Point2D pos, Point2D direction, int max_tick, int bound,
                     boolean isPermanent) {
        super(sprite, pos, isPermanent);
        this.direction = direction;
        this.max_tick = max_tick;
        this.counter = 0;
        this.bound = bound;
    }

    abstract MovableElement generateElement(Point2D direction, int bound);

    /**
     * Generates and places newly generated elements.
     * @return a newly generated element, which moves in the direction and within the
     * boundary as specified by the Generator, and which starts moving after a certain number of frame
     * ticks from the position of the Generator.
     */
    public MovableElement placeElement() {
        if (counter < max_tick){
            counter += 1;
            return null;
        }
        else {
            this.counter = 0;
            MovableElement genElement = generateElement(direction, this.bound);
            genElement.setPos(Point2D.add(super.getPos(), direction));

            return genElement;
        }
    }

    @Override
    public void reset() {
        this.counter = 0;
    }}

