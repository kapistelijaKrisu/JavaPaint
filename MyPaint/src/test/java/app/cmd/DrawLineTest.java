package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class DrawLineTest {

    DrawLine cmd;
    ControlUnit cu;

    @Before
    public void setUp() {
        cu = new ControlUnit();
        cu.init(10, 10);
        cu.getBrush().setWidth(1);
        cu.activateSettings(false, false, true);
        cmd = new DrawLine();
    }

    public boolean testColors(Color c) {
        int length = 9;
        int testPixel = 5;

        cu.getBrush().setColor(c);
        
        cu.activateSettings(true, false, false);
        cmd.execute(cu.getImg(), new Area(testPixel, testPixel));
        if (c.getRGB() != cu.getImg().getImg().getRGB(testPixel, testPixel)) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != 5 && j != 5) {
                    if (c.getRGB() != cu.getImg().getImg().getRGB(testPixel, testPixel)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    @Test
    public void testExecute() {

        Assert.assertTrue(testColors(Color.red));
        Assert.assertTrue(testColors(Color.black));

    }
}
