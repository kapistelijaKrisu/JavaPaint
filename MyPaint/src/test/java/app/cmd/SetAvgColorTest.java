package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class SetAvgColorTest {

    FillRect cmd;
    
    ControlUnit cu;
    Area a;

    @Before
    public void setUp() {
        cu = new ControlUnit(2, 4);
        cu.getImg().setWidth(1);
        cu.getImg().setOverride(true);
        
        cmd = new FillRect();
        a = new Area(0, 0);
    }

    @Test
    public void avgIsRight() {
        Color c1 = new Color(22,33,4,55);
        Color c2 = new Color(221,23,14,155);
        Color c3 = new Color(21,123,124,125);
        Color c4 = new Color(121,83,66,1);
        
        Color[] ct = new Color[] { c1,c2,c3,c4};
        for (int i = 0; i < ct.length; i++) {
            cu.getImg().setColor(ct[i]);
        a.setAll(1, i);
        cu.setActiveCMD(CommandMap.FILLRECT);
        cu.execute(a);
        
        cu.setActiveCMD(CommandMap.PICKCOLOR);
        cu.execute(a);

        Assert.assertEquals(ct[i].getRGB(), cu.getImg().getColor().getRGB());

            
        }
 
        a.setAll(1, 1);
        a.udpate(1, 3);
        cu.execute(a);

        int aa = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 1; i < ct.length; i++) {
           int val = ct[i].getRGB();
           aa += (0xff000000 & val) >>> 24;
        r += (0x00ff0000 & val) >> 16;
        g += (0x0000ff00 & val) >> 8;
        b += (0x000000ff & val);
            
        }
        
        Color test = new Color(r / 3, g / 3, b / 3, aa / 3);
        Assert.assertEquals(test.getRGB(), cu.getImg().getGraphics().getColor().getRGB());
    }

    @Test
    public void testArguments() {
        SetAvgColor avgSetter = new SetAvgColor();
        try {
            avgSetter.execute(null, a);
            Assert.assertFalse(true);
        } catch (NullPointerException e) {

        }

        try {
            avgSetter.execute(null, null);
            Assert.assertFalse(true);
        } catch (NullPointerException e) {

        }
    }
}
