package elements;

import game.PlayerState;

import java.io.Serializable;

public class ChasingElementModifier extends Modifier implements Serializable {
    /**
     * Makes the player lose five points if not invincible
     * @param playerState the Player's current playerState
     * @return the modified playerState
     */
    @Override
    public PlayerState Modifier(PlayerState playerState){
        if (!playerState.checkInvincible()) {
            playerState.updatePoints(-5);

            playerState.resetIframes();

        }
        return playerState;
    }
}
