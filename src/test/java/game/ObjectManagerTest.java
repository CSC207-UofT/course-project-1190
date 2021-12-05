package game;

import game.ObjectManager;
import elements.Alligator;
import elements.Element;
import org.junit.*;
import utils.EnumsForSprites;
import utils.Point2D;

import java.util.ArrayList;

public class ObjectManagerTest {

    @Test(timeout = 500)
    public void TestgetImg() {
        ArrayList<Element> testArray = new ArrayList<>();
        Point2D pos = new Point2D(4, 5);
        Point2D velocity = new Point2D(0,2);
        Alligator a = new Alligator(EnumsForSprites.ALLIGATOR, pos, 50, 80, velocity);
        ObjectManager om = new ObjectManager(18);
        om.addObject(a);
        assert(om.getPointImagePairs().get(0).getImg().equals(EnumsForSprites.ALLIGATOR));
    }

    @Test(timeout = 500)
    public void TestgetPoint() {
        ArrayList<Element> testArray = new ArrayList<>();
        Point2D pos = new Point2D(4, 5);
        Point2D velocity = new Point2D(0,2);
        Alligator a = new Alligator(EnumsForSprites.ALLIGATOR, pos, 50, 80, velocity);
        ObjectManager om = new ObjectManager(18);
        om.addObject(a);
        assert(Point2D.equals(om.getPointImagePairs().get(0).getPoint(),new Point2D(4,5)));
    }

    @Test(timeout = 500)
    public void TestAddObject() {
        ArrayList<Element> testArray = new ArrayList<>();
        Point2D pos = new Point2D(4, 5);
        Point2D velocity = new Point2D(0,2);
        Alligator a = new Alligator(EnumsForSprites.ALLIGATOR, pos, 50, 80, velocity);
        ObjectManager om = new ObjectManager(18);
        om.addObject(a);
    }
}