
package toolTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class AreaTest {
    Area a;
    private int initX, initY;
    
    @Before
    public void setUp() {
        Area.setBounds(50, 40);
        
        initX = 10;
        initY = 5;
        a = new Area(initX, initY);
    }
    
    @Test
    public void constructorValues() {  
        if (a.getCurX() == a.getLastX()) {
            if (a.getCurX() == a.getStartX()) {
                Assert.assertEquals(initX, a.getCurX());
            }
        } else {
            Assert.assertFalse(true);
        }
        
        if (a.getCurY() == a.getLastY()) {
            if (a.getCurY() == a.getStartY()) {
                Assert.assertEquals(initY, a.getCurY());
            }
        } else {
            Assert.assertFalse(true);
        }
        
    //    assertTrue(tulos.contains(numero));
    }
}
