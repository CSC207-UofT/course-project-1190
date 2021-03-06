package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class Point2DTest {
    Point2D p = new Point2D(5,10);

    @Test
    void getX() {
        assert(p.getX() == 5);
    }

    @Test
    void getY() {
        assert(p.getY() == 10);
    }

    int[] p_pair = new int[]{5, 10};
    Point2D pp = new Point2D(p_pair);

    @Test
    void getPair() {
    assert(Arrays.equals(pp.getPair(), p_pair));
    }

    @Test
    void equals() {
        assert (Point2D.equals(p, pp));
    }

    @Test
    void add() {
        Point2D result = new Point2D(10, 20);
        assert(Point2D.equals(Point2D.add(p, pp), result));
        Point2D point2D = new Point2D(2,2);
        assert(Point2D.equals(Point2D.minus(result, point2D), new Point2D(8,18)));
        assert Point2D.xDistance(result, point2D) == 8;
        assert Point2D.yDistance(result, point2D) == 18;
        assert Point2D.linearDistance(pp, p) == 0.0;
        System.out.println(point2D.toString());
    }


}