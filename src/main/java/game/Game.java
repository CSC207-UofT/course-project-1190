package game;

import elements.*;
import utils.EnumsForSprites;
import utils.Point2D;
import utils.PointImagePair;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;


public class Game implements Serializable {

    private boolean isRunning;
    private Board board;
    private Player player;
    private ObjectManager objectManager;
    private int size;

    /**
     * A constructor for the Game class.
     * Currently, each new Game is hard-coded to have a new Player at starting position (5, 5).
     * @param size the size of the board, which is an arbitrary integer to be chosen by the human user
     */
    public Game (int size) {
        this.board = new Board(size);
        this.objectManager = new ObjectManager(size - 1);
        Point2D pos = new Point2D(5, 5);
        this.player = new Player(pos);

        this.size = size;
        this.isRunning = true;

    }


    /**
     * Moves the Player to a new position.
     * @param move the incremental movement the Player is to make from its current position.
     */
    public void movePlayer(Point2D move) {
        Point2D currentPos = player.getPos();
        Point2D newPos = Point2D.add(currentPos, move);
        boolean traversable = board.isTraversable(newPos);

        if (traversable)  {
            player.setPos(newPos);
        }
        else  {
            System.out.println("Met a wall!");
        }
    }


    /**
     * A wrapper method that calls on the underlying board to update itself.
     */
    public void updateObjects() {
        PlayerState ps = this.player.getPlayerState();
        this.updateObjects(ps);
    }

    /**
     * Updates the Player's PlayerState using the board's feedback based on the Player's current location.
     * Also decreases the Player's temporary invincibility-frame-count by 1.
     */
    public void updatePlayerState() {

        PlayerState currPlayerState = this.player.playerState;
        PlayerState modifiedPlayerState = this.updatePlayerState(currPlayerState);

        this.player.setPlayerState(modifiedPlayerState);
        this.player.decrementIframes();
    }


    /**
     * @return  a mapping between each location contained in the board's objectManager and its String representation.
     */
    public ArrayList<PointImagePair> getMovableObjectPointImgPairs() {
        return this.objectManager.getPointImagePairs();
    }

    /**
     * @param playerState player's current playerState
     * @return the new playerState after an object in objectManager interacts with the player
     */
    public PlayerState updatePlayerState(PlayerState playerState) {
        ArrayList<Modifier> list = this.objectManager.modifyPlayerState(playerState);
        for (Modifier modifier : list){
            modifier.Modifier(playerState);
        }
        return playerState;
    }


    /**
     * Call on the board's objectManager to update the status of every object it contains .
     */
    public void updateObjects(PlayerState ps){
        this.objectManager.updateObjects(ps);
    }


    /**
     * @return the objectManager of the board.
     */
    public ObjectManager getObjectManager() {
        return objectManager;
    }

    /**
     * Getters and Setters for game status.
     */

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public Board getBoard() {return board;}

    public ArrayList<PointImagePair> getBoardTiles() {
        return this.board.getBoardSprites();
    }

    public ArrayList<PointImagePair> getBoardMovableObjects() {
        return this.getMovableObjectPointImgPairs();
    }

    public EnumsForSprites getPlayerSprite() {
        return this.player.getSprite();
    }

    public Point2D getPlayerPosition() {
        return this.player.getPos();
    }

    public PlayerState getPlayerState() {return this.player.getPlayerState();}

    public boolean checkPlayerWon() {
        return this.player.getPlayerState().getWinningState();
    }

    public int getSize(){return this.size;}
}
