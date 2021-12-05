package elements;

import game.PlayerState;

import java.io.Serializable;

public class GoalModifier extends Modifier implements Serializable {
    /**
     * Makes the player win the game
     * @param playerState the Player's current playerState
     * @return the modified playerState
     */
    @Override
    public PlayerState Modifier(PlayerState playerState) {
        playerState.setWinningState(true);
        return playerState;
    }
}
