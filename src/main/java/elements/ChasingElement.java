package elements;

import game.PlayerState;
import utils.EnumsForSprites;
import utils.Point2D;


public class ChasingElement extends MovableElement implements Interactable{

    private ChasingElementModifier mod;

    /**
     * A constructor for the MovableElement class.
     *
     * @param sprite   the element's representation
     * @param pos      the element's initial position
     * @param bound    the element's movement boundary
     * @param max_tick the number of frame ticks before the next movement
     * @param velocity the movement per tick, represented by a pair of integers on our tile-based game board.
     */
    public ChasingElement(EnumsForSprites sprite, Point2D pos, int bound, int max_tick, Point2D velocity) {
        super(sprite, pos, bound, max_tick, velocity);
        this.mod = new ChasingElementModifier();
    }


    /**
     * Returns the ChasingElement's modifier, to modify elsewhere
     * @param playerState the Player's current playerState
     * @return the ChasingElement's modifier
     */
    @Override
    public ChasingElementModifier Modify(PlayerState playerState) {
        return this.mod;
    }

}
