package app.cmd;

import app.ControlUnit;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class FillColorTest {

    ControlUnit cu;
    Area a;
    BufferedImage img;
    HashMap<Integer, HashSet<Integer>> testBits;
    int color;

    @Before
    public void setUp() {
        cu = new ControlUnit();
        cu.init(3, 3);
        cu.getBrush().setWidth(1);
        cu.getBrush().setOverride(true);
        cu.getBrush().setColor(Color.yellow);
        cu.activateSettings(true, true, true);
        cu.setActiveCMD(CommandMap.FILLCOLOR);
        a = new Area(1, 1);

        img = cu.getImg();
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
    public void test1() {
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
    public void test2() {
        a.init(5, 5);
        cu.execute(a);
        cu.init(3, 3);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {

                Assert.assertEquals(0, cu.getImg().getRGB(i, j));

            }

        }
    }

    public boolean testLoopPart(Area a, int size) {

        cu.execute(a);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {

                if (cu.getBrush().getColor().getRGB() != cu.getImg().getRGB(i, j)) {
                    return false;
                }

            }

        }
        return true;
    }

    @Test
    public void test4() {
        int size = 5;
        for (int i = 0; i < size; i+= size-1) {
            for (int j = 0; j < size; j+= size-1) {
                a.init(i, j);
                testLoopPart(a, size);
            }

        }

    }

    @Test
    public void test3() {
        a.init(2, 0);
        testBits.get(2).add(0);
        testBits.get(2).add(1);
        testBits.get(2).add(2);

        cu.execute(a);
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (testBits.containsKey(i) && testBits.get(i).contains(j)) {

                    Assert.assertEquals(Color.yellow.getRGB(), img.getRGB(i, j));
                } else {
                    Assert.assertFalse(img.getRGB(i, j) == cu.getBrush().getColor().getRGB());
                }

            }

        }
    }

    @Test
    public void testArguments() {
        FillColor cmd = new FillColor();
        try {
            cmd.execute(null, a.getRectangle());
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {

        }

        try {
            cmd.execute(null, null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {

        }

    }

}
