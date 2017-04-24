package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class FillRectTest {
    
    ControlUnit cu;
    Area a;

    @Before
    public void setUp() {
        cu = new ControlUnit(10, 10);
        cu.getImg().setWidth(1);
        cu.getImg().setOverride(true);
        cu.getImg().setColor(Color.yellow);
        cu.setActiveCMD(CommandMap.FILLRECT);
        a = new Area(2, 2);
    }
    
    @Test
    public void testRect() {
        a.udpate(5, 5);
        cu.execute(a.getRectangle());

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
        FillRect cmd = new FillRect();
        try {
            cmd.execute(null, a.getRectangle());
            Assert.assertFalse(true);
        } catch (NullPointerException e) {
            
        }
      
        try {
            cmd.execute(null, null);
            Assert.assertFalse(true);
        } catch (NullPointerException e) {
            
        }
   
    }
}
