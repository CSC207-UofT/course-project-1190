package utils;

import java.io.Serializable;

public class PlayerState implements Serializable {
    int points;
    int iFrames;
    boolean hasWon;

    /**
     * A constructor for the PlayerState class, which takes in an initial points and sets its invincibility frame count to 60 and winning state to false.
     * @param points the PlayerState's current points
     */
    public PlayerState(int points) {
        this.points = points;
        this.iFrames = 60;
        this.hasWon = false;
    }

    /**
     * Empty constructor for playerstate, sets points to default (100)
     */
    public PlayerState() {
        this.points = 100;
        this.iFrames = 60;
        this.hasWon = false;
    }

    /**
     * Updates the PlayerState's points by an increment
     * @param change the change increment
     */
    public void updatePoints(int change) {
        this.points += change;
    }

    /**
     * Checks whether the Player's invincibility frame count has decreased to 0. If not, the Player's PlayerState cannot be changed.
     * @return whether the Player's invincibility frame count has decreased to 0.
     */
    public boolean checkInvincible() {
        return (iFrames > 0);
    }

    /**
     * Decreases the PlayerState's invincibility frame count by 1.
     */
    public void decrementIframes() {
        if (this.iFrames > 0) {
            this.iFrames -= 1;
        }
    }


    /**
     * Getters and (re)Setters for PlayerState's attributes.
     */
    public void resetIframes() {
        this.iFrames = 60;
    }
    public int getPoints(){
        return points;
    }
    public int getiFrames() {
        return iFrames;
    }
    public void setWinningState(boolean hasWon) {this.hasWon = hasWon; }
    public boolean getWinningState() {return this.hasWon;}
}
