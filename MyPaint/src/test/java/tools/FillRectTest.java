package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class FillRectTest {

    FillRect cmd;
    ControlUnit cu;
    Area a;

    @Before
    public void setUp() {
        cu = new ControlUnit();
        cu.init(10, 10);
        cu.getBrush().setWidth(1);
        cu.getBrush().setOverride(true);
        cu.getBrush().setColor(Color.yellow);
        cu.activateSettings(true, true, true);
        cmd = new FillRect();
        a = new Area(2, 2);
    }
    
    @Test
    public void testRect() {
        a.udpate(5, 5);
        cmd.execute(cu.getImg(), a.getRectangle());

        for (int i = 0; i < cu.getImg().getImg().getHeight(); i++) {
            for (int j = 0; j < cu.getImg().getImg().getWidth(); j++) {
                if ((i >= 2 && i <=5) && (j >= 2 && j <= 5)) {
                    Assert.assertEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));
                } else {
                    Assert.assertNotEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));
                }
            }
        }
    }

    
    @Test
    public void testArguments() {
        try {
            cmd.execute(null, a.getRectangle());
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            
        }
        
        try {
            cmd.execute(cu.getImg(), null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            
        }
        
        try {
            cmd.execute(null, null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            
        }
        
        try {
            cmd.execute(cu.getImg(), a);        
        } catch (IllegalArgumentException e) {
            Assert.assertFalse(true);
        }
    }
}
