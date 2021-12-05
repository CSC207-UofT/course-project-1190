package game;

import utils.Point2D;

public class GameSeeder {
    private Game game;

    public GameSeeder(Game g) {
        this.game = g;
    }

    public void addGoal (Point2D pos) {
        this.game.getObjectManager().addGoal(pos);
    }

    public void addDownAlligatorDen(Point2D pos) {
        this.game.getObjectManager().addDownAlligatorDen(pos);
    }

    public void addRightAlligatorDen(Point2D pos) {
        this.game.getObjectManager().addRightAlligatorDen(pos);
    }

    public void addChasingElement(Point2D pos, int max_tick) {
        this.game.getObjectManager().addChasingElement(pos, max_tick);
    }

    //TODO:  more directions for Movable

    public void addPortal(Point2D pos) {
        this.game.getObjectManager().addPortal(pos);
    }

    public void addRock(Point2D pos) {
        this.game.getObjectManager().addRock(pos);
    }



}
