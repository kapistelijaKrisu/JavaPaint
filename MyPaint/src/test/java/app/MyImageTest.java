
package app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyImageTest {
    MyImage img;
    int width;
    int height;
    
    @Before
    public void setUp() {
        width = 20;
        height = 10;
        img = new MyImage(width, height);
    }
    
    @Test
    public void constructorWorks() {
        Assert.assertTrue(img.getImg().getWidth() == width);
        Assert.assertTrue(img.getImg().getHeight() == height);
        
        Assert.assertTrue(img.getImg().getGraphics() != null);
        
        try {
            img = new MyImage(0, 1);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            img = new MyImage(1, 0);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        
        try {
            img = new MyImage(0, 0);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        img = new MyImage(1, 1);
        Assert.assertTrue(img.getImg().getWidth() == 1);
        Assert.assertTrue(img.getImg().getHeight()== 1);
    }

}
