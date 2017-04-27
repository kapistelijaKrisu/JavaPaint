
package tools;

import org.junit.Assert;
import org.junit.Test;


public class CoordinateTest {
    @Test
    public void TestCoordinateEquals() {
        Coordinate c = new Coordinate(1, 2);
        Assert.assertFalse(c.equals(new Coordinate(2, 2)));
        Assert.assertFalse(c.equals(new Coordinate(1, 1)));
        Assert.assertFalse(c.hashCode() == (new Coordinate(2, 2).hashCode()));
        Assert.assertTrue(c.hashCode() == (new Coordinate(1, 2).hashCode()));
    }
    @Test 
    public void hashCodeTest() {
        Coordinate c = new Coordinate(1, 2);
        Coordinate b = new Coordinate(1, 2);
        Coordinate a = new Coordinate(2, 1);
        
        Assert.assertEquals(c.hashCode(), b.hashCode());
        Assert.assertNotEquals(a.hashCode(), b.hashCode());
        Assert.assertEquals(1 * 200 + 2 * 200000, b.hashCode());
    }
    @Test
    public void equalsTest() {
        Coordinate c = new Coordinate(1, 2);
        Coordinate b = new Coordinate(1, 2);
        Coordinate a = new Coordinate(2, 1);
            
        Assert.assertEquals(c, b);
        Assert.assertNotEquals(a, b);
        Assert.assertNotEquals(a, null);
        Assert.assertNotEquals(a, "asd");

    }
}
