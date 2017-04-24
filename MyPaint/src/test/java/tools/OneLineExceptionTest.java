
package tools;


import org.junit.Assert;
import org.junit.Test;
import ui.OneLineException;

public class OneLineExceptionTest {
    
    @Test
    public void nullThrowsException() {
        try {
            OneLineException.throwIfIsNull(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            
        }
    }

}
