package elements;

import game.Player;
import game.PlayerState;
import utils.EnumsForSprites;
import utils.Point2D;


public class Alligator extends MovableElement implements Interactable{

    private AlligatorModifier mod;
    /**
     * A constructor for the Alligator class, inherited from its parent class MovableElement.
     * @param sprite the element's representation
     * @param pos element's initial position
     * @param bound the movement boundary of this element.
     * @param max_tick number of frame ticks it takes for the next movement to happen
     * @param velocity the movement per tick, represented by a pair of integers on our tile-based game board.
     */
    public Alligator(EnumsForSprites sprite, Point2D pos, int bound, int max_tick, Point2D velocity) {
        super(sprite, pos, bound, max_tick, velocity);
        this.mod = new AlligatorModifier();
    }

    /**
     * Returns the Alligator's modifier, to modify elsewhere
     * @param playerState the Player's current playerState
     * @return the Alligator's modifier
     */
    @Override
    public AlligatorModifier Modify(PlayerState playerState) {
        return this.mod;
    }
}
