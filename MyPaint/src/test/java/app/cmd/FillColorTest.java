package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.TwoPoint;

public class FillColorTest {

    ControlUnit cu;
    TwoPoint a;
    BufferedImage img;
    HashMap<Integer, HashSet<Integer>> testBits;
    int color;

    @Before
    public void setUp() {
        cu = new ControlUnit(3, 3);
        cu.getImg().setBrushWidth(1);
        cu.getImg().setOverride(true);
        cu.getImg().setColor(Color.yellow);
        cu.setActiveCMD(CommandMap.FILLCOLOR);
        a = new TwoPoint(1, 1);

        img = cu.getImg().getImg();
        color = Color.black.getRGB();
        img.setRGB(1, 0, color);
        img.setRGB(1, 1, color);
        img.setRGB(1, 2, color);
        img.setRGB(0, 1, color);

        testBits = new HashMap<>();
        testBits.put(2, new HashSet<>());
        testBits.put(0, new HashSet<>());
        testBits.put(1, new HashSet<>());

    }

    @Test
    public void fillWithObstaclesTest() {
        testBits.get(0).add(1);
        testBits.get(1).add(1);
        testBits.get(1).add(2);
        testBits.get(1).add(0);

        cu.execute(a);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (testBits.containsKey(i) && testBits.get(i).contains(j)) {

                    Assert.assertEquals(Color.yellow.getRGB(), img.getRGB(i, j));
                } else {
                    Assert.assertEquals(0, img.getRGB(i, j));
                }

            }

        }
    }

    @Test
    public void fillWithObstacles2Test() {
        a.setAll(2, 1);
        testBits.get(2).add(0);
        testBits.get(2).add(1);
        testBits.get(2).add(2);

        cu.execute(a);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (testBits.containsKey(i) && testBits.get(i).contains(j)) {

                    Assert.assertEquals(Color.yellow.getRGB(), img.getRGB(i, j));
                } else {
                    Assert.assertFalse(img.getRGB(i, j) == cu.getImg().getColor().getRGB());
                }

            }

        }
    }

    @Test
    public void fillTestAll() {

        a.setAll(2, 2);
        cu.setImage(new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB));
        img = cu.getImg().getImg();

        cu.execute(a);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {

                Assert.assertEquals(Color.yellow.getRGB(), img.getRGB(i, j));

            }

        }
    }

    @Test
    public void testArguments() {
        FillColor cmd = new FillColor();
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
