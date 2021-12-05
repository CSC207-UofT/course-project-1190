package elements;

import game.PlayerState;
import utils.Point2D;

import java.io.Serializable;

public class PortalModifier extends Modifier implements Serializable {
    private Point2D teleportPoint;

    /**
     * PortalModifier constructor
     * @param point the teleportPoint
     */
    public PortalModifier(Point2D point){
        this.teleportPoint = point;
    }

    /**
     * Makes the player move to the teleportPoint
     * @param playerState the Player's current playerState
     * @return the modified playerState
     */
    @Override
    public PlayerState Modifier(PlayerState playerState){
        playerState.setPos(this.teleportPoint);
        // To check if player actually stepping on portal is registered
        playerState.updatePoints(-1);
        return playerState;
    }

    /**
     * Makes the player move to the teleportPoint
     * @param newPos the new point to teleport to
     */
    public void changeTeleportPoint(Point2D newPos) {
        this.teleportPoint = newPos;
    }
}
