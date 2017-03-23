package app;

import app.cmd.CMD;
import app.cmd.DrawLine;
import java.awt.Color;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class ControlUnitTest {

    ControlUnit cu;

    @Before
    public void SetUp() {
        cu = new ControlUnit();
    }

    @Test
    public void initWorks1() {
        Assert.assertFalse(cu.getInit());
        try {
            cu.run();
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu.init(0, 0);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu.init(1, 0);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu.init(0, 1);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        cu.init(1, 1);
        Area a = new Area(2, 2);
        Assert.assertTrue(a.getCurX() == 1);
        Assert.assertTrue(a.getCurY() == 1);

        Assert.assertTrue(cu.getImg().getImg().getHeight() == 1);
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 1);

        Assert.assertTrue(cu.getInit());

        cu.init(10, 5);
        a = new Area(20, 20);
        Assert.assertTrue(a.getCurX() == 10);
        Assert.assertTrue(a.getCurY() == 5);

        Assert.assertTrue(cu.getImg().getImg().getHeight() == 5);
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 10);
        Assert.assertTrue(cu.getInit());
    }

    @Test
    public void setActiveCMDWorks() {
        cu.init(1, 1);
        Assert.assertTrue(cu.getCurrentCMD() == 1);

        cu.setActiveCMD(5);
        Assert.assertTrue(cu.getCurrentCMD() == 1);
    }

    @Test
    public void testExecute() {
        cu.init(1, 1);
        cu.setActiveCMD(ControlUnit.defaultDrawCMD);
        cu.execute(new Area(0, 0));
        Assert.assertTrue(cu.getImg().getImg().getRGB(0, 0) == Color.black.getRGB());
        cu.getPaintBrush().setCurrentColor(Color.yellow);
        cu.execute(new Area(0, 0));
        Assert.assertTrue(cu.getImg().getImg().getRGB(0, 0) == Color.yellow.getRGB());
    }
}
