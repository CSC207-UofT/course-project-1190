package game;

import elements.*;
import elements.Element;
import utils.Point2D;
import utils.PointImagePair;
import utils.EnumsForSprites;

import java.beans.PropertyChangeEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class ObjectManager implements Serializable {
    private ArrayList<Element> boardObjects;
    private int bound;

    /**
     * A constructor for ObjectManager class.
     *
     * @param bound the size of the board - 1, which is the size of the area where the elements can reside.
     */
    public ObjectManager(int bound) {
        this.boardObjects = new ArrayList<Element>();
        this.bound = bound;

    }

    /**
     * Another constructor for the ObjectManager class, which creates an ObjectManager from a list of existing elements.
     * Used to reload the game.
     *
     * @param boardObjects a list of existing elements
     * @param bound        the size of the board - 1, which is the size of the area where the elements can reside.
     */
    public ObjectManager(ArrayList<Element> boardObjects, int bound) {
        this.boardObjects = boardObjects;
        this.bound = bound;

    }


    public ArrayList<Element> getBoardObjects() {
        return this.boardObjects;
    }


    /**
     * Add new elements to the board, collected and managed by the objectManager.
     *
     * @param object new element to be added
     */
    public void addObject(Element object) {
        boardObjects.add(object);
    }

    // TODO: add for each type of element


    /**
     * A method to place a ChasingElement as a specific location on the board.
     *
     * @param pos where the new ChasingElement is placed on the board.
     */
    public void addChasingElement(Point2D pos, int max_tick) {
        this.addObject(new ChasingElement(EnumsForSprites.CHASER, pos, this.bound, max_tick, new Point2D(0, 0)));
    }

    /**
     * A method to place, specifically, an AlligatorDen that shoots alligators only to the right on the board. This
     * method will link with gameMaker's "elements menu," so that the user can choose a specific type of element to
     * plant at a specific location on the board.
     *
     * @param pos where the new AlligatorDen is placed on the board.
     */
    public void addRightAlligatorDen(Point2D pos) {
        this.addObject(new AlligatorDen(EnumsForSprites.ALLIGATORDEN, pos, new Point2D(1, 0), 120, bound));
    }


    /**
     * A method to place left-shooting AlligatorDens.
     *
     * @param pos where the new AlligatorDen is placed on the board
     */
    public void addLeftAlligatorDen(Point2D pos) {
        this.addObject(new AlligatorDen(EnumsForSprites.ALLIGATORDEN, pos, new Point2D(-1, 0), 120, bound));
    }

    /**
     * A method to place upward-shooting AlligatorDens.
     *
     * @param pos where the new AlligatorDen is placed on the board
     */
    public void addUpAlligatorDen(Point2D pos) {
        this.addObject(new AlligatorDen(EnumsForSprites.ALLIGATORDEN, pos, new Point2D(0, -1), 120, bound));
    }

    /**
     * A method to place downward-shooting AlligatorDens.
     *
     * @param pos where the new AlligatorDen is placed on the board
     */
    public void addDownAlligatorDen(Point2D pos) {
        this.addObject(new AlligatorDen(EnumsForSprites.ALLIGATORDEN, pos, new Point2D(0, 1), 120, bound));
    }

    /**
     * A method to place a Goal element on the board. When Player reaches the Goal, the Player wins the game.
     *
     * @param pos location where the Goal element is placed on the board
     */
    public void addGoal(Point2D pos) {
        this.addObject(new Goal(EnumsForSprites.GOAL, pos));
    }

    /**
     * A method to place a Portal element on the board. When Player reaches the Portal, the player will be teleported
     * to specific coordinates
     * @param pos location where the Portal element is placed on the board
     */
    public void addPortal(Point2D pos) {
        this.addObject(new Portal(EnumsForSprites.PORTAL, pos, pos));
    }

    /**
     * A method that checks if there is a Portal element on the board
     * @return True if there is a Portal element on the board and False is there isn't
     */
    public boolean checkPortals() {
        boolean isPortal = false;
        for (Element element : boardObjects){
            if (element instanceof Portal){
                isPortal = true;
            }
        }
        return isPortal;
    }

    /**
     * A method to place a Rock element on the board.  the player will be teleported to this Rock if they use the Portal
     * @param pos location where the Rock element is placed on the board
     */
    public void addRock(Point2D pos) {
        Rock teleportPoint = new Rock(EnumsForSprites.ROCK, pos);
        this.addObject(teleportPoint);

        for (Element element : boardObjects){
            if (element instanceof Portal){
                ((Portal) element).changeTeleportPoint(teleportPoint.getPos());
            }
        }
    }

    /**
     * A method to remove elements from the board, which is managed by the board's objectManager.
     *
     * @param object the element to be deleted
     */
    public void removeObject(Element object){
        boardObjects.remove(object);
    }


    /**
     * A getter method to collect and access a mapping between the location and the string representation of all objects
     * currently on the board.
     *
     * @return the collection of every object's location and corresponding string representation.
     */
    public ArrayList<PointImagePair> getPointImagePairs() {
        ArrayList<PointImagePair> collector = new ArrayList<>();
        for (Element boardObject : this.boardObjects) {
            collector.add(boardObject.getPointImagePair());
        }
        return collector;
    }

    /**
     * Updates the content of the ObjectManager, removing elements that have gone off the screen and adding elements
     * that have been generated.
     */
    public void updateObjects(PlayerState ps) {
        HashSet<Element> objectsToRemove = new HashSet<>();
        HashSet<Element> objectsToAdd = new HashSet<>();
        Point2D playerPos = ps.getPos();

        for (Element boardObject : boardObjects) {

            if (boardObject instanceof MovableElement) {
                boolean result = ((MovableElement) boardObject).processTick();
                if (!result) {
                    objectsToRemove.add(boardObject);
                }
            } else if (boardObject instanceof Generator) {
                Element elementToAdd = ((Generator) boardObject).placeElement();
                if (elementToAdd != null) {
                    objectsToAdd.add(elementToAdd);
                }

            }
        }

        // Remove objects that have gone offscreen
        for (Element element : objectsToRemove) {
            this.removeObject(element);
        }

        //Add any generated objects
        for (Element element : objectsToAdd) {
            this.addObject(element);
        }


        // Set ChasingElement's velocity in relation to Player position each tick.
        for (Element boardObject : boardObjects) {
            if (boardObject instanceof ChasingElement) {
                ChasingElement ce = (ChasingElement)boardObject;
                int directionX = (int) (Math.signum(Point2D.xDistance(playerPos, ce.getPos())) * 1);
                int directionY = (int) (Math.signum(Point2D.yDistance(playerPos, ce.getPos())) * 1);
                Point2D newVel = new Point2D(directionX, directionY);
                ce.setVelocity(newVel);
            }
        }
    }



    /**
     * If a Player steps onto the same location as an Element, check to see if the Element can affect the Player's
     * PlayerState; if so, create a list of them to be sent to Game
     *
     * @param playerState the Player's playerState, currently including points, temporary invincibility after
     *                    encountering an element, and winning status.
     * @return a list of the modifiers on the tile where player is

     */
    public ArrayList<Modifier> modifyPlayerState(PlayerState playerState) {
        Point2D currPosition = playerState.getPos();
        ArrayList<Modifier> list = new ArrayList<Modifier>();

        for (Element boardObject : boardObjects) {
            if ((boardObject instanceof Interactable) & Point2D.equals(boardObject.getPos(), currPosition)) {
                list.add(((Interactable) boardObject).Modify(playerState));
            }
        }

        return list;
    }



}
