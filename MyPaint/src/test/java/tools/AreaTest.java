package tools;

import java.awt.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AreaTest {

    Area a;
    private int initX, initY;
    private int maxX, maxY;

    @Before
    public void setUp() {

        maxX = 50;
        maxY = 40;
        Area.setBounds(maxX, maxY);

        initX = 10;
        initY = 5;
        a = new Area(initX, initY);
    }

    @Test
    public void constructorValues() {
        if (a.getCurX() == a.getPrevX()) {
            Assert.assertEquals(initX, a.getCurX());
        }
        if (a.getCurY() == a.getPrevY()) {
            Assert.assertEquals(initY, a.getCurY());
        }

    }

    @Test
    public void initTest() {
        int[] testVal = new int[]{-10, -20, 0, 0, 2, 3, 500, 600, 5, 555};
        int[] results = new int[]{0, 0, 0, 0, 2, 3, maxX, maxY, 5, 40};

        for (int i = 0; i < results.length / 2; i += 2) {
            a.setAll(testVal[i * 2], testVal[i * 2 + 1]);

            if (a.getCurX() == a.getPrevX()) {
                Assert.assertEquals(results[i * 2], a.getCurX());
            }

            if (a.getCurY() == a.getPrevY()) {
                Assert.assertEquals(results[i * 2 + 1], a.getCurY());
            }
        }
    }

    @Test
    public void updateTest() {
        int[] results = new int[]{0, 0, 1, 1, 0, 0, maxX, maxY, maxX, 5, 6, maxY};
        int[] testVal = new int[]{0, 0, 1, 1, -3, -6, 50, 40, 55, 5, 6, 55};

        for (int i = 0; i < results.length / 2; i += 2) {
            int prevX = a.getCurX();
            int prevY = a.getCurY();
            a.udpate(testVal[i * 2], testVal[i * 2 + 1]);

            Assert.assertEquals(results[i * 2], a.getCurX());
            Assert.assertEquals(results[i * 2 + 1], a.getCurY());

        }
    }

    @Test
    public void updateCurrentTest() {
        a.udpate(2, 3);

        Assert.assertEquals(10, a.getPrevX());
        Assert.assertEquals(5, a.getPrevY());
        Assert.assertEquals(2, a.getCurX());
        Assert.assertEquals(3, a.getCurY());

        a.setAll(5, 5);
        a.udpateCurrents(50, 50);

        Assert.assertEquals(5, a.getPrevX());
        Assert.assertEquals(5, a.getPrevY());
        Assert.assertEquals(maxX, a.getCurX());
        Assert.assertEquals(maxY, a.getCurY());

        a.udpateCurrents(-50, -50);

        Assert.assertEquals(5, a.getPrevX());
        Assert.assertEquals(5, a.getPrevY());
        Assert.assertEquals(0, a.getCurX());
        Assert.assertEquals(0, a.getCurY());
    }

    @Test
    public void getRectTest() {

        Rectangle r = a.getRectangle();
        Assert.assertEquals(initX, r.x);
        Assert.assertEquals(initY, r.y);
        Assert.assertEquals(0, r.width);
        Assert.assertEquals(0, r.height);

        a.udpate(5, 1);
        r = a.getRectangle();
        Assert.assertEquals(5, r.x);
        Assert.assertEquals(1, r.y);
        Assert.assertEquals(5, r.width);
        Assert.assertEquals(4, r.height);

        a.udpate(20, 10);
        r = a.getRectangle();
        Assert.assertEquals(15, r.width);
        Assert.assertEquals(9, r.height);

    }
}
