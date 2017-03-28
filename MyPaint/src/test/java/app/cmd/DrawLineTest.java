package app.cmd;

import app.ControlUnit;
import app.MyImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class DrawLineTest {

    DrawLine cmd;
    MyImage img;
   

    @Before
    public void setUp() {
        img = new MyImage(10, 10);
        
        cmd = new DrawLine();
    }

    
    public void testColors(Color c) {
        int length = 10;
        int testPixel = 5;

    /*    cu.init(length, length);
        cu.getPaintBrush().setWidth(1);
        cu.getPaintBrush().setCurrentColor(c);
        cu.execute(new Area(5, 5));

        BufferedImage img = cu.getImg().getImg();
        Assert.assertEquals(c.getRGB(), img.getRGB(testPixel, testPixel));

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != 5 && j != 5) {
                    Assert.assertFalse(img.getRGB(i, j) == c.getRGB());
                }

            }
        }*/
    }

    @Test
    public void testExecute() {
        testColors(Color.red);
        testColors(Color.black);
        
    }
}
