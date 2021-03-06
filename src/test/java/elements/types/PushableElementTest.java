package elements.types;

import elements.types.PushableElement;
import game.Board;
import game.ObjectManager;
import game.PlayerState;
import org.junit.*;
import utils.EnumsForSprites;
import utils.Point2D;

public class PushableElementTest {
    @Test(timeout = 500)
    public void TestSetPos() {
        PushableElement pe = new PushableElement(EnumsForSprites.PUSHABLE_ELEMENT,
                new Point2D(3,3), 10);

        Point2D moveTo = new Point2D(5,5);
        assert(!Point2D.equals(pe.getPos(), moveTo));
        pe.setPos(new Point2D(5,5));
        assert (Point2D.equals(pe.getPos(), moveTo));
    }

    @Test(timeout = 500)
    public void TestOnContact(){
        PushableElement pe = new PushableElement(EnumsForSprites.PUSHABLE_ELEMENT,
                new Point2D(3,3), 10);
        PlayerState ps = new PlayerState(100, new Point2D(3,4));

        assert (!pe.onContact(ps.getPos()));
        ps.setPos(new Point2D(3,3));
        assert (pe.onContact(ps.getPos()));
    }

}
