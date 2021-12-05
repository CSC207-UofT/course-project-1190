package utils;

import java.io.Serializable;

/**
 * Class that represents an immutable 2-tuple of integer coordinates (that is, a point (x, y)).
 */
public class Point2D implements Serializable {

    private final int x;
    private final int y;

    /**
     * Constructs a Point2D from 2 ints.
     * @param x The first coordinate.
     * @param y The second coordinate.
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a Point2D from an int[2] array.
     * @param pair The integer array.
     */
    public Point2D(int[] pair) {
        if (pair == null || pair.length != 2) {
            throw new IllegalArgumentException("Must pass in two integers to create Point2D object");
        }

        this.x = pair[0];
        this.y = pair[1];
    }

    /**
     * Gets the first component.
     * @return The instance's first component.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the second component.
     * @return The instance's second component.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets an int[] representation of the 2 components.
     * @return An int[] of length 2, whose elements are the first and second component.
     */
    public int[] getPair() {
        return new int[]{this.x, this.y};
    }

    /**
     * Performs vector addition on 2 Point2D instances.
     * @param p1 The first point.
     * @param p2 The second point.
     * @return A new Point2D instance with value (p1 + p2).
     */
    public static Point2D add(Point2D p1, Point2D p2) {
        return new Point2D(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }

    /**
     * Checks if two Point2D instances are equal.
     * @param p1 the first point
     * @param p2 the second point
     * @return whether the two points are equal
     */
    public static boolean equals(Point2D p1, Point2D p2) {
        return (p1.getX() == p2.getX()) & (p1.getY() == p2.getY());
    }

    /**
     * A method to get the horizontal distance between two points.
     * @param p1 the first point
     * @param p2 the second point
     * @return the horizontal distance between the two points
     */
    public static int xDistance(Point2D p1, Point2D p2) {
        return (p1.getX()-p2.getX());
    }

    /**
     * A method to get the vertical distance between two points.
     * @param p1 the first point
     * @param p2 the second point
     * @return the vertical distance between the two points
     */
    public static int yDistance(Point2D p1, Point2D p2) {
        return (p1.getY() -p2.getY());
    }


    /**
     * A method to get the linear distance between two points.
     * @param p1 the first point
     * @param p2 the second point
     * @return the linear distance between the two points
     */
    public static double linearDistance(Point2D p1, Point2D p2) {
        return (Math.sqrt(Math.pow(xDistance(p1, p2), 2) + Math.pow(yDistance(p1, p2), 2)));
    }

    /**
     * A method to print out the location of a point to human-readable String.
     * @return a String that contains the (X, Y) coordinates of a Point2D object
     */
    @Override
    public String toString(){
        return ("(" + this.getX() + " , " + this.getY()+ ")");
    }

}


