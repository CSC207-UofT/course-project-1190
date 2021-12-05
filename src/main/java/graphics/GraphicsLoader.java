package graphics;

import game.Game;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import game.PlayerState;
import utils.Point2D;
import utils.PointImagePair;
import utils.EnumsForSprites;
import java.util.ArrayList;
import java.util.HashMap;


//TODO: Rename this class to better reflect its current purpose
/**
 * Contains methods for drawing the game state on a Canvas.
 */
public class GraphicsLoader {

    // Represents the amount of space (in pixels) that a single tile takes up.
    // Added tileSize because we may not want to hardcode this value in the future.
    private int tileSize;

    private HashMap<EnumsForSprites, Image> strMapping = new HashMap<EnumsForSprites, Image>();

    /**
     * Constructs an instance with the default tile size.
     */
    public GraphicsLoader() {

        this.tileSize = 32;
        this.strMapping.put(EnumsForSprites.ISTRASVERSABLE,
                new Image("file:src/main/assets/tiles/cobble_blood1.png"));
        this.strMapping.put(EnumsForSprites.NOTTRASVERSABLE,
                new Image("file:src/main/assets/tiles/torch1.png"));
        this.strMapping.put(EnumsForSprites.PLAYER,
                new Image("file:src/main/assets/player/deep_elf_blademaster.png"));
        this.strMapping.put(EnumsForSprites.ALLIGATOR,
                new Image("file:src/main/assets/player/animals/alligator.png"));
        this.strMapping.put(EnumsForSprites.ALLIGATORDEN,
                new Image("file:src/main/assets/tiles/dngn_entrance.png"));
        this.strMapping.put(EnumsForSprites.GOAL,
                new Image("file:src/main/assets/player/statues/guardian-eyeopen-flame3.png"));
        this.strMapping.put(EnumsForSprites.CHASER,
                new Image("file:src/main/assets/player/nonliving/molten_gargoyle.png"));
        this.strMapping.put(EnumsForSprites.PORTAL, new Image("file:src/main/assets/tiles/dngn_portal.png"));
        this.strMapping.put(EnumsForSprites.ROCK, new Image("file:src/main/assets/tiles/crystal_floor5.png"));

    }

    /**
     * Constructs an instance with a specified tile size.
     * @param tileSize The tile size.
     */
    public GraphicsLoader(int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Draws the current state of the board on a GraphicsContext instance.
     * @param gc The GraphicsContext to draw on.
     * @param game The specified Game instance to draw from.
     */
    public void drawBoard(GraphicsContext gc, Game game) {

        ArrayList<PointImagePair> tiles = game.getBoardTiles();
        ArrayList<PointImagePair> objects = game.getBoardMovableObjects();

        // Render tiles
        for (PointImagePair tile: tiles) {
            Point2D point = tile.getPoint();
            gc.drawImage(strMapping.get(tile.getImg()), tileSize * point.getX(), tileSize * point.getY());
        }


       // Render elements
        for (PointImagePair obj: objects) {
            Point2D point = obj.getPoint();
            gc.drawImage(strMapping.get(obj.getImg()), tileSize * point.getX(), tileSize * point.getY());
        }

    }

    /**
     * Draws the player on a GraphicsContext instance.
     * @param gc The GraphicsContext to draw on.
     * @param game The specified Game instance to draw from.
     */
    public void drawPlayer(GraphicsContext gc, Game game) {
        Point2D point = game.getPlayerPosition();
        gc.drawImage(strMapping.get(game.getPlayerSprite()), tileSize * point.getX(), tileSize * point.getY());
    }

    public void drawPlayerState(GraphicsContext gc, Point2D point, Game game) {
        PlayerState currState = game.getPlayerState();

        Font debugFont = new Font("Consolas", 12);
        gc.setFont(debugFont);
        gc.setFill(Color.MAGENTA);
        gc.fillText("Points: " + currState.getPoints(), point.getX(), point.getY() + 12);
        gc.fillText("Invincible: " + currState.checkInvincible(), point.getX(), point.getY() + 24);
        gc.fillText("Iframes: " + currState.getiFrames(), point.getX(), point.getY() + 36);
        if (game.checkPlayerWon()) {
            gc.fillText("Won the game", point.getX(), point.getY() + 48);
        }

    }
}

