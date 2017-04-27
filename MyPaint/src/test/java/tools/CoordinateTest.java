
package tools;

import org.junit.Assert;
import org.junit.Test;
import app.cmd.Coordinate;


public class CoordinateTest {
    @Test
    public void TestCoordinateEquals() {
        Coordinate c = new Coordinate(1, 2);
        Assert.assertFalse(c.equals(new Coordinate(2, 2)));
        Assert.assertFalse(c.equals(new Coordinate(1, 1)));
        Assert.assertFalse(c.hashCode() == (new Coordinate(2, 2).hashCode()));
        Assert.assertTrue(c.hashCode() == (new Coordinate(1, 2).hashCode()));
    }
}
