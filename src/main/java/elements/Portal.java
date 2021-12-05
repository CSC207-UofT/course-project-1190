package elements;
import game.PlayerState;
import utils.EnumsForSprites;
import utils.Point2D;

public class Portal extends Element implements Interactable{
    private PortalModifier mod;

    /**
     * A constructor for the Portal class, inherited from its parent the Element class.
     * @param sprite the element's representation
     * @param pos the element's initial position
     * @param teleportPoint The portal's teleporation coordinates
     */
    public Portal(EnumsForSprites sprite, Point2D pos, Point2D teleportPoint) {
        super(sprite, pos);
        this.mod = new PortalModifier(teleportPoint);
    }

    /**
     * Changes Portal's teleportation coordinates
     * @param pos the new teleporation coordinates
     */
    public void changeTeleportPoint(Point2D pos) {
        this.mod.changeTeleportPoint(pos);
    }

    /**
     * Returns the Portal's modifier, to modify elsewhere
     * @param playerState the Player's current playerState
     * @return the Portal's modifier
     */
    @Override
    public PortalModifier Modify(PlayerState playerState) {
        return this.mod;
    }

}