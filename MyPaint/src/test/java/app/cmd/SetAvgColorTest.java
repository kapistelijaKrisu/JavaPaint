
package app.cmd;

import app.MyImage;
import app.PaintBrush;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class SetAvgColorTest {
    SetAvgColor avg;
    MyImage img;
    Area a;
    
    @Before
    public void setUp() {
        avg = new SetAvgColor(new PaintBrush(1, true));
        img = new MyImage(10, 10);
        a = new Area(0, 0);
    }
    @Test
    public void avgIsRight() {
        //....
    }

}
