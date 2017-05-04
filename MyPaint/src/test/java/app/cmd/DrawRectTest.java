package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.TwoPoint;

public class DrawRectTest {

    DrawRect cmd;
    ControlUnit cu;
    TwoPoint a;
    
    

    @Before
    public void setUp() {
        cu = new ControlUnit();
        cu = new ControlUnit(10, 10);
        cu.getImg().setBrushWidth(1);
        cu.getImg().setOverride(true);
        cu.getImg().setColor(Color.yellow);
        cu.setActiveCMD(CommandMap.DRAWRECT);
        a = new TwoPoint(2, 2);
        
        
    }
    
    @Test
    public void testRect() {
        a.jump(4, 4);
        cu.execute(a);
        for (int i = 2; i <= 4; i++) {
            for (int j = 2; j <= 4; j++) {
                if (i == 2 || j == 2 || i == 4 || j == 4) {
                    Assert.assertEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));
                } else {
                    Assert.assertNotEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));
                }
            }
        }
    }

}
