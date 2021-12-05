package game;
import utils.Point2D;
import utils.EnumsForSprites;

import java.awt.*;
import java.io.Serializable;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;


public class Player implements Serializable {
    public PlayerState playerState;
    private final EnumsForSprites sprite;
    private final PropertyChangeSupport observable;

    /**
     * A constructor for the Player class, which sets its position on the board, its PlayerState with an initial 100
     * points, and a representation.
     * @param pos the initial position of the Player
     */
    public Player(Point2D pos){
        this.playerState = new PlayerState(100, pos);
        this.sprite =  EnumsForSprites.PLAYER;
        this.observable = new PropertyChangeSupport(this);
    }



    /** Gets the Player's current position.
     * @return the Player's current position, represented by a Point2D object composed of 2 integer coordinates
     */
    public Point2D getPos(){
        return this.playerState.getPos();
    }

    /**
     * Sets the Player's position to a new position and notify its Observers.
     * @param newPos the new position, represented by a Point2D object composed of 2 integer coordinates
     */
    public void setPos(Point2D newPos) {
        // observable.firePropertyChange("playerPos", oldPos, newPos);
        this.playerState.setPos(newPos); 
    }


    /**
     * Sets the Player's playerState.
     * @param playerState the new playerState
     */
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    /**
     * Sets the Player's representation, currently a String and to be mapped to an Image by  the GraphicsLoader.
     * @return the Player's representation
     */
    public EnumsForSprites getSprite() {
        return this.sprite;
    }


    /**
     * Gets the Player's playerState.
     * @return the Player's playerState
     */
    public PlayerState getPlayerState() {return this.playerState;}


    /**
     * A wrapper method that calls on the Player's PlayerState to decrease its invincibility-frame-count.
     */
    public void decrementIframes() {
        this.playerState.decrementIframes();
    }
}
