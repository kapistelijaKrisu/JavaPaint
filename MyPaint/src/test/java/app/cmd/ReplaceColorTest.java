package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class ReplaceColorTest {

    ControlUnit cu;
    Area a;
    BufferedImage img;

    @Before
    public void setUp() {
        cu = new ControlUnit(5, 5);
        cu.setActiveCMD(CommandMap.REPLACECOLOR);
        cu.getImg().setColor(Color.red);
        img = cu.getImg().getImg();
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                img.setRGB(j, i, 0);
                
            }
            
        }

        a = new Area(0, 0);

        
        img.setRGB(0, 0, Color.BLUE.getRGB());

        img.setRGB(0, 4, Color.BLUE.getRGB());
        img.setRGB(1, 4, Color.YELLOW.getRGB());
        img.setRGB(2, 4, Color.BLUE.getRGB());
        img.setRGB(3, 4, Color.BLACK.getRGB());
        img.setRGB(4, 4, Color.BLUE.getRGB());

    }

    @Test
    public void testRect() {
        cu.execute(a);

        Assert.assertEquals(Color.red.getRGB(), img.getRGB(0, 0));

        Assert.assertEquals(Color.red.getRGB(), img.getRGB(0, 4));
        Assert.assertEquals(Color.YELLOW.getRGB(), img.getRGB(1, 4));
        Assert.assertEquals(Color.red.getRGB(), img.getRGB(2, 4));
        Assert.assertEquals(Color.BLACK.getRGB(), img.getRGB(3, 4));
        Assert.assertEquals(Color.red.getRGB(), img.getRGB(4, 4));

        for (int y = 1; y < img.getWidth()-1; y++) {
            for (int x = 0; x < 5; x++) {
                Assert.assertEquals(0, img.getRGB(x, y));
            }
        }

    }

    @Test
    public void testArguments() {
        FillRect cmd = new FillRect();
        try {
            cmd.execute(null, a);
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
