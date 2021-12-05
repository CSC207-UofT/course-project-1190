package game;

import elements.Element;
import elements.MovableElement;
import elements.Tile;
import utils.Point2D;
import utils.PointImagePair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Board implements Serializable {
    private final int size;
    private final Tile[][] board;

    /**
     * A constructor for class Board.
     * @param size an integer chosen by the User to set up the size of the board.
     */
    public Board(int size) {
        this.size = size;
        this.board = new Tile[size][size];
        // this.objectManager = new ObjectManager(size - 1);

        this.fillBoard();
        this.fillEdges();
    }

    /**
     * @return the size of the board.
     */
    public int getSize() {
        return this.size;
    }


    /**
     * @return a mapping between each location on the board and its String representation.
     */
    public ArrayList<PointImagePair> getBoardSprites() {
        ArrayList<PointImagePair> pointImagePairs = new ArrayList<>();

        for (int i = 0; i < size; i ++) {
            for (int j = 0; j < size; j ++) {
                pointImagePairs.add(new PointImagePair(new Point2D(i, j), this.board[i][j].getSprite()));
            }
        }

        return pointImagePairs;
    }

    /**
     * @param point a location on the board
     * @return a boolean that indicates whether the Player can move onto that position. Currently, only edges of the
     * board or "walls" are non-traversable.
     */
    public boolean isTraversable(Point2D point) {
        int x = point.getX();
        int y = point.getY();

        return this.board[x][y].isTraversable();
    }

    /**
     * Pave the entire board with basic tiles.
     */
    public void fillBoard() {
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                this.board[i][j] = new Tile(true);
            }
        }
    }

    /**
     * Add non-traversable walls to the four edges of the board.
     */
    public void fillEdges() {
        for (int i = 0; i < this.getSize(); i ++) {

            this.board[0][i] = new Tile(false);
            this.board[this.getSize() - 1][i] = new Tile(false);
        }
        for (int i = 1; i < this.getSize() - 1; i ++) {

            this.board[i][0] = new Tile(false);
            this.board[i][this.getSize() - 1] = new Tile( false);
        }
    }
}

