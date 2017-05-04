package app;

import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.TwoPoint;

public class LogTest {

    @Test
    public void worksNormally() {
        ControlUnit cu;
        TwoPoint a;
        BufferedImage img;

        cu = new ControlUnit(5, 5);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getImg().setColor(Color.black);
        img = cu.getImg().getImg();
        a = new TwoPoint(0, 0);

        int initial = img.getRGB(0, 0);
        a.jump(0, 4);
        cu.execute(a);
        cu.undo();
        img = cu.getImg().getImg();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Assert.assertEquals(initial, img.getRGB(i, j));
            }

        }
        cu.redo();
        img = cu.getImg().getImg();
        for (int i = 1; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Assert.assertEquals(initial, img.getRGB(i, j));
            }

        }
        for (int i = 0; i < img.getWidth(); i++) {
            Assert.assertEquals(Color.black.getRGB(), img.getRGB(0, i));

        }
    }

    @Test
    public void nullTest() {
        ControlUnit cu = new ControlUnit(5, 5);
        MyImage img = cu.getImg();
        Log log = cu.getLog();

        try {
            log.archieveImage(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            //hurray
        }

        try {
            log.popNext(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            //hurray
        }

        try {
            log.popPrevious(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            //hurray
        }

    }

    @Test
    public void newCommandUpdates() {
        ControlUnit cu = new ControlUnit(5, 5);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getImg().setColor(Color.black);

        MyImage img = cu.getImg();
        Log log = cu.getLog();
        TwoPoint a = new TwoPoint(0, 0);

        cu.execute(a);
        cu.execute(a);
        cu.execute(a);
        cu.undo();
        cu.undo();
        cu.execute(a);
        BufferedImage current = img.getImg();
        cu.redo();
        BufferedImage shouldBeSame = img.getImg();
        Assert.assertEquals(current, shouldBeSame);
        Assert.assertEquals(null, log.popNext(current));

        for (int i = 0; i < 11; i++) {
            cu.undo();

        }
        Assert.assertEquals(null, log.popPrevious(current));

        log.popNext(current);
        Assert.assertNotEquals(null, log.popPrevious(current));
    }
    
    @Test
    public void historySizeTest() {
        ControlUnit cu = new ControlUnit(5, 5);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getImg().setColor(Color.black);

        MyImage img = cu.getImg();
        Log log = cu.getLog();
        log.setLogMaxSize(10);
        TwoPoint a = new TwoPoint(0, 0);
        
        for (int i = 0; i < 15; i++) {
            Assert.assertEquals(Math.min(i, 10), log.getHistorySize());
            cu.execute(a);
        }
    }
    
    @Test
    public void redoSizeTest() {
        ControlUnit cu = new ControlUnit(5, 5);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getImg().setColor(Color.black);

        MyImage img = cu.getImg();
        Log log = cu.getLog();
        log.setLogMaxSize(10);
        TwoPoint a = new TwoPoint(0, 0);
        
        for (int i = 0; i < 10; i++) {
            cu.execute(a);
        }
        
        for (int i = 0; i < 10; i++) {
            cu.undo();
            Assert.assertEquals(i+1, cu.getLog().getRedoSize());
        }
    }
}
