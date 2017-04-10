package app;

import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class LogTest {

    @Test
    public void worksNormally() {
        ControlUnit cu;
        Area a;
        BufferedImage img;

        cu = new ControlUnit();
        cu.init(5, 5);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getImg().setColor(Color.black);
        img = cu.getImg().getImg();
        a = new Area(0, 0);

        int initial = img.getRGB(0, 0);
        a.udpate(0, 4);
        cu.execute(a);
        cu.getImg().undo();
        img = cu.getImg().getImg();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Assert.assertEquals(initial, img.getRGB(i, j));
            }

        }
        cu.getImg().redo();
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
        MyImage img = new MyImage(5, 5);
        Log log = img.getLog();
        
        try {
            log.addStep(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            //hurray
        }
        
        try {
            log.getNext(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            //hurray
        }
        
        try {
            log.getPrevious(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            //hurray
        }
        
    }
    
    @Test
    public void newCommandUpdates() {
        ControlUnit cu = new ControlUnit();
        cu.init(5, 5);
        cu.setActiveCMD(CommandMap.DRAWLINE);
        cu.getImg().setColor(Color.black);
        
        MyImage img = cu.getImg();
        Log log = img.getLog();
        Area a = new Area(0, 0);
        
        
        cu.execute(a);
        cu.execute(a);
        cu.execute(a);
        img.undo();
        img.undo();
        cu.execute(a);
        BufferedImage current = img.getImg();
        img.redo();
        BufferedImage shouldBeSame = img.getImg();
        Assert.assertEquals(current, shouldBeSame);
        Assert.assertEquals(null, log.getNext(current));
        
        for (int i = 0; i < 11; i++) {
            img.undo();
            
        }
        Assert.assertEquals(null, log.getPrevious(current));
        
        log.getNext(current);
        Assert.assertNotEquals(null, log.getPrevious(current));
    }
}
