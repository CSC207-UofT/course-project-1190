package graphics;

import game.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utils.Point2D;

import java.util.HashSet;

/**
 * This class encapsulates a Canvas instance that renders the game. This class in theory makes the Canvas
 * easier to manipulate within a larger scene graph.
 */
public class RenderPane {

    private Game game;
    private GraphicsLoader gl;
    private Canvas canvas;
    private AnimationTimer timer;
    private long tick;
    private long currentNanoTime;
    private boolean makeMode;

    private HashSet<String> pressedKeys;

    private final Font DEBUG_FONT = new Font("Consolas", 14);

    //TODO: Edit constructor such that we only have to input one size-related parameter.
    /**
     * Constructs a pane with a desired screen size.
     * @param size The desired screen size.
     */
    public RenderPane(Game game, Point2D size) {

        // Build node structure
        canvas = new Canvas(size.getX(), size.getY());

        // Init some other fields
        gl = new GraphicsLoader();
        this.game = game;
        tick = 0;
        currentNanoTime = System.nanoTime();
        pressedKeys = new HashSet<>();
        this.makeMode = false;

        // Attach event listeners
        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);
        canvas.setOnMouseClicked(this::onMouseClicked);

        this.addGoal(new Point2D(17, 17));
        this.game.addDownAlligatorDen(new Point2D(12, 13));
        this.game.addRightAlligatorDen(new Point2D(7,8));
        // Init and start timer
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                tickAndRender(now - currentNanoTime);
                currentNanoTime = now;
            }
        };


    }

    //------------ PUBLIC METHODS ------------//

    /**
     * Starts the game loop.
     */
    public void start() {
        System.out.printf("Game timer started at tick=%d.%n", tick);
        timer.start();
    }

    //------------ RENDERING METHODS ------------//

    /**
     * Processes one tick cycle of the game, which consists of the following steps (in order):
     * 1) Clear the canvas
     * 2) Update game state
     * 3) Draw new game state
     * @param deltaTime Time elapsed (in nanoseconds) since the last call of the function.
     */
    private void tickAndRender(long deltaTime) {

        if (!this.makeMode) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            if (this.game.checkPlayerLose() | this.game.checkPlayerWon()) {
                this.game.resetGameToBaseState();
            }

            // Clears the canvas
        clearCanvas();
        // Updates game state
        game.updateObjects();
        game.updatePlayerState();


            // Draws new game state
            gl.drawBoard(gc, game);
            gl.drawPlayer(gc, game);

            // Draws debug info for new game state
            gl.drawPlayerState(gc, new Point2D(32 * 16, 32), game);
            drawDebugInfo(1000000000.0 / deltaTime, new Point2D(100, 100));

            // Increments tick number
            tick += 1;
        }
    }

    private void drawDebugInfo(double fps, Point2D pos) {
        GraphicsContext gc = getContext();
        gc.setFill(Color.MAGENTA);
        gc.setFont(DEBUG_FONT);

        gc.fillText(String.format("FPS: %.1f", fps),
                pos.getX(), pos.getY() + DEBUG_FONT.getSize());
        gc.fillText(String.format("PressedKeys: %s", pressedKeys.toString()),
                pos.getX(), pos.getY() + 2*DEBUG_FONT.getSize());
        gc.fillText(String.format("Frame: %d", tick),
                pos.getX(), pos.getY() + 3*DEBUG_FONT.getSize());
    }

    public void clearCanvas() {
        GraphicsContext gc = getContext();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    //------------ EVENT METHODS ------------//

    private void onKeyPressed(KeyEvent event) {
        String keyCode = event.getCode().toString();

        // Disables all other keybinds when game is paused.
        if (!makeMode) {
            // Movement-related keybinds
            if (keyCode.equals("W") && !pressedKeys.contains(keyCode)) {
                game.movePlayer(new Point2D(0, -1));
            }
            if (keyCode.equals("S") && !pressedKeys.contains(keyCode)) {
                game.movePlayer(new Point2D(0, 1));
            }
            if (keyCode.equals("A") && !pressedKeys.contains(keyCode)) {
                game.movePlayer(new Point2D(-1, 0));
            }
            if (keyCode.equals("D") && !pressedKeys.contains(keyCode)) {
                game.movePlayer(new Point2D(1, 0));
            }

            pressedKeys.add(event.getCode().toString());
        }
    }

    private void onKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode().toString());
    }

    private void onMouseClicked(MouseEvent event) {
        canvas.requestFocus();

        if (this.makeMode){
            Point2D mousePoint = new Point2D((int) Math.floor(event.getX()/32), (int) Math.floor(event.getY()/32));
            if (this.checkClickedPoint(mousePoint)) {
                this.game.addGoal(mousePoint);
                GraphicsContext gc = canvas.getGraphicsContext2D();

                // Clears the canvas
                clearCanvas();
                // Draws new game state
                gl.drawBoard(gc, game);
                gl.drawPlayer(gc, game);
            }
        }
    }

    private boolean checkClickedPoint(Point2D point) {
        boolean boundCondition = (( 1 <= point.getX()  && point.getX() < this.game.getSize())
                                    && ( 1 <= point.getY()  && point.getY() < this.game.getSize()));

        boolean noOverlapCondition = game.checkOverlap(point);

        return boundCondition && noOverlapCondition;
    }

    //------------ GETTERS AND SETTERS ------------//

    /**
     * A shortcut for getCanvas().getGraphicsContext2D().
     * @return The Canvas' GraphicsContext.
     */
    public GraphicsContext getContext() {
        return canvas.getGraphicsContext2D();
    }

    /**
     * Gets the Canvas associated with this instance.
     * @return The Canvas instance.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Gets the Game associated with this instance.
     * @return The Game instance.
     */
    public Game getGame() {
        return game;
    }

    public void changeGameState() {
        if (!this.makeMode) {
            this.resetGameToBaseState();
            GraphicsContext gc = canvas.getGraphicsContext2D();

            // Clears the canvas
            clearCanvas();
            // Draws new game state
            gl.drawBoard(gc, game);
            gl.drawPlayer(gc, game);
        }
        this.makeMode = !this.makeMode;
    }

    public void resetGameToBaseState() {
        this.game.resetGameToBaseState();
    }

    //-----------------Board Element Adders--------------//
    // Object-specific add methods to be called by gameMaker

    public void addGoal(Point2D pos) {
        this.game.addGoal(pos);
    }

}
