package elements;

import org.junit.Test;
import utils.Point2D;
import utils.EnumsForSprites;

public class AlligatorDenTest {
    @Test(timeout = 500)
    public void TestAlligatorDenGenerate() {
        AlligatorDen alligatorDen = new AlligatorDen(EnumsForSprites.ALLIGATOR_DEN, new Point2D(3,3),
                new Point2D(1, 0), 60, 10, true);

        for (int i = 0; i <= 59; i++) {
            alligatorDen.placeElement();
        }
        MovableElement element = alligatorDen.placeElement();

        assert (!(element == null));
    }
}
