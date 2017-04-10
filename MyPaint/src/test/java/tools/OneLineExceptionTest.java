
package tools;


import org.junit.Assert;
import org.junit.Test;
import ui.OneLineException;

public class OneLineExceptionTest {
    
    @Test
    public void nullThrowsException() {
        try {
            OneLineException.nullTest(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            
        }
    }
    
    @Test
    public void noNullNoException() {
        try {
            OneLineException.nullTest(2);
            
        } catch (NullPointerException e) {
            Assert.assertTrue(false);
        }
    }
}
