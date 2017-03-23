
package app.cmd;

import app.ControlUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class CMDTest {
    CMD cmd;
    int test;
    
    @Before
    public void setUp() {
        test = 0;
    }
    
    @Test
    public void executeWorks() {
        cmd = new CMD(null) {
            @Override
            public void execute(Area area) {
                if (areaOrControllerIsNull(area)) {
                    test++;
                } else {
                    test = 10;
                }
            }
        };
        cmd.execute(null);
        Assert.assertEquals(test, 1);
        Assert.assertFalse(test == 10);
        cmd.execute(new Area(2, 2));
        Assert.assertEquals(test, 2);
         Assert.assertFalse(test == 10);
    }    

    @Test 
    public void constructorWorks() {
        ControlUnit cpu = new ControlUnit();
        cmd = new CMD(cpu) {
            @Override
            public void execute(Area area) {
                
            }
        };
        Assert.assertEquals(cpu, cmd.controller);
    }
}
