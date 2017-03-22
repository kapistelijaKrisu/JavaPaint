package tools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AreaTest {

    Area a;
    private int initX, initY;

    @Before
    public void setUp() {
        Area.setBounds(50, 40);

        initX = 10;
        initY = 5;
        a = new Area(initX, initY);
    }

    @Test
    public void constructorValues() {
        if (a.getCurX() == a.getLastX()) {
            if (a.getCurX() == a.getStartX()) {
                Assert.assertEquals(initX, a.getCurX());
            }
        } else {
            Assert.assertFalse(true);
        }

        if (a.getCurY() == a.getLastY()) {
            if (a.getCurY() == a.getStartY()) {
                Assert.assertEquals(initY, a.getCurY());
            }
        } else {
            Assert.assertFalse(true);
        }
    }

    @Test
    public void initTest() {
        int[] testVal = new int[]{-10, -20, 0, 0, 2, 3, 500, 600, 5, 555};
        int[] results = new int[]{0, 0, 0, 0, 2, 3, 50, 40, 5, 40};

        for (int i = 0; i < results.length / 2; i += 2) {
            a.init(testVal[i * 2], testVal[i * 2 + 1]);

            if (a.getCurX() == a.getLastX()) {
                if (a.getCurX() == a.getStartX()) {
                    Assert.assertEquals(results[i * 2], a.getCurX());
                }
            } else {
                Assert.assertFalse(true);
            }

            if (a.getCurY() == a.getLastY()) {
                if (a.getCurY() == a.getStartY()) {
                    Assert.assertEquals(results[i * 2 + 1], a.getCurY());
                }
            } else {
                Assert.assertFalse(true);
            }
        }
    }

    @Test
    public void updateTest() {
        int[] results = new int[]{0, 0, 1, 1, 0, 0, 50, 40,50,5, 6,40};
        int[] testVal = new int[]{0, 0, 1, 1, -3, -6, 50,40,55,5,6,55};

        for (int i = 0; i < results.length / 2; i += 2) {
            int prevX = a.getCurX();
            int prevY = a.getCurY();
            a.udpate(testVal[i * 2], testVal[i * 2 + 1]);

            Assert.assertEquals(a.getStartX(), initX);
            Assert.assertEquals(a.getStartY(), initY);

            Assert.assertEquals(prevX, a.getLastX());
            Assert.assertEquals(prevY, a.getLastY());

            Assert.assertEquals(results[i * 2], a.getCurX());
            Assert.assertEquals(results[i * 2 + 1], a.getCurY());

        }
    }
}
