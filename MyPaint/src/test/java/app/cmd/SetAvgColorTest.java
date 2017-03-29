package app.cmd;

import app.ControlUnit;
import app.MyImage;
import app.PaintBrush;
import java.awt.Color;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class SetAvgColorTest {

    FillRect cmd;
    SetAvgColor avgSetter;
    ControlUnit cu;
    Area a;

    @Before
    public void setUp() {
        cu = new ControlUnit();
        cu.init(2, 4);
        cu.getBrush().setWidth(1);
        cu.getBrush().setOverride(true);
        cu.activateSettings(true, true, true);
        cmd = new FillRect();
        a = new Area(0, 0);
        avgSetter = new SetAvgColor(cu.getBrush());
    }

    @Test
    public void avgIsRight() {
        Color c1 = new Color(22,33,4,55);
        Color c2 = new Color(221,23,14,155);
        Color c3 = new Color(21,123,124,125);
        Color c4 = new Color(121,83,66,1);
        
        Color[] ct = new Color[] { c1,c2,c3,c4};
        for (int i = 0; i < ct.length; i++) {
            cu.getBrush().setColor(ct[i]);
        cu.activateSettings(true, false, false);
        a.init(1, i);
        cmd.execute(cu.getImg(), a.getRectangle());

        avgSetter.execute(cu.getImg(), a.getRectangle());
        Assert.assertEquals(ct[i].getRGB(), cu.getBrush().getColor().getRGB());

            
        }
 
        a.init(1, 1);
        a.udpate(1, 3);
        avgSetter.execute(cu.getImg(), a.getRectangle());

         int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 1; i < ct.length; i++) {
           int val = ct[i].getRGB();
           a += (0xff000000 & val) >>> 24;
        r += (0x00ff0000 & val) >> 16;
        g += (0x0000ff00 & val) >> 8;
        b += (0x000000ff & val);
            
        }
        
        Color test = new Color(r / 3, g / 3, b / 3, a / 3);
        Assert.assertEquals(test.getRGB(), cu.getImg().getGraphics().getColor().getRGB());
    }

    @Test
    public void testArguments() {
        try {
            avgSetter.execute(null, a.getRectangle());
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {

        }

        try {
            avgSetter.execute(cu.getImg(), null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {

        }

        try {
            avgSetter.execute(null, null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {

        }

        try {
            avgSetter.execute(cu.getImg(), a);
        } catch (IllegalArgumentException e) {
            Assert.assertFalse(true);
        }

        try {
            avgSetter = new SetAvgColor(null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {

        }
        try {
            avgSetter = new SetAvgColor(cu.getBrush());
        } catch (IllegalArgumentException e) {
            Assert.assertFalse(true);
        }
    }
}
