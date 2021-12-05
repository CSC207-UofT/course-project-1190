package elements;

import game.PlayerState;

import java.io.Serializable;

public class AlligatorModifier extends Modifier implements Serializable {
    /**
     * Makes the player lose a point if not invincible
     * @param playerState the Player's current playerState
     * @return the modified playerState
     */
    @Override
    public PlayerState Modifier(PlayerState playerState){
        if (!playerState.checkInvincible()) {
            playerState.updatePoints(-1);

            playerState.resetIframes();

        }
        return playerState;
    }
}
