package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.TwoPoint;

public class DrawEllipseTest {

    DrawRect cmd;
    ControlUnit cu;
    TwoPoint a;
    HashMap<Integer, HashSet<Integer>> testBits;

    @Before
    public void setUp() {
        cu = new ControlUnit(10, 10);
        cu.getImg().setBrushWidth(1);
        cu.getImg().setOverride(true);
        cu.getImg().setColor(Color.yellow);
        cu.setActiveCMD(CommandMap.DRAWELLIPSE);

        testBits = new HashMap<>();
        testBits.put(2, new HashSet<>());
        testBits.put(0, new HashSet<>());
        testBits.put(1, new HashSet<>());
        testBits.put(3, new HashSet<>());
        testBits.put(4, new HashSet<>());
        testBits.put(5, new HashSet<>());
    }

    @Test
    public void testRect() {
        testBits.get(0).add(1);
        testBits.get(0).add(2);
        testBits.get(1).add(0);
        testBits.get(1).add(3);
        testBits.get(2).add(0);
        testBits.get(2).add(3);
        testBits.get(3).add(1);
        testBits.get(3).add(2);

        a = new TwoPoint(0, 0);
        a.jump(3, 3);
        cu.execute(a);
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                
                if (testBits.containsKey(i) && testBits.get(i).contains(j)) {
                    Assert.assertEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));
                } else {
                    Assert.assertEquals(0, cu.getImg().getImg().getRGB(i, j));
                }
            }
        }

    }
}
