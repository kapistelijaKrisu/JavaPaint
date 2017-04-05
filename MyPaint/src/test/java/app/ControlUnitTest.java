package app;

import app.cmd.CommandMap;
import java.awt.AlphaComposite;
import java.awt.Color;
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
        Area a = new Area(1, 1);
        Assert.assertTrue(a.getCurX() == 0);
        Assert.assertTrue(a.getCurY() == 0);

        Assert.assertTrue(cu.getImg().getHeight() == 1);
        Assert.assertTrue(cu.getImg().getWidth() == 1);

        Assert.assertTrue(cu.getInit());

        cu.init(10, 5);
        a = new Area(20, 20);
        Assert.assertTrue(a.getCurX() == 9);
        Assert.assertTrue(a.getCurY() == 4);

        Assert.assertTrue(cu.getImg().getHeight() == 5);
        Assert.assertTrue(cu.getImg().getWidth() == 10);
        Assert.assertTrue(cu.getInit());
    }
    
    @Test
    public void brushTest() {
        cu.init(1, 1);
        Assert.assertTrue(cu.getCurrentCMD() == 1);

        cu.setActiveCMD(5);
        Assert.assertTrue(cu.getCurrentCMD() == 1);
        
        cu.init(1, 1);
        cu.getBrush().setColor(Color.black);
        cu.activateSettings(true, false, false);
        Assert.assertEquals(Color.black, cu.getGraphics().getColor());
    }

    @Test
    public void testExecute() {
        cu.init(1, 1);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getBrush().setColor(Color.black);
        cu.activateSettings(true, true, true);
        cu.execute(new Area(0, 0));
        Assert.assertTrue(cu.getImg().getRGB(0, 0) == Color.black.getRGB());
        cu.getBrush().setColor(Color.yellow);
        cu.activateSettings(true, true, true);
        cu.execute(new Area(0, 0));
        Assert.assertTrue(cu.getImg().getRGB(0, 0) == Color.yellow.getRGB());
    }
}
