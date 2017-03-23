
package tools;

import org.junit.*;
import org.junit.Test;

public class FileUtilsTest {
    
    @After
    public void setDefaultName() {
        FileUtils.setFileLocation("Untitiled");
    }

    
    @Test
    public void nameIsNotNull() {
        Assert.assertFalse(FileUtils.getFileLocation() == null);
    }
    
    @Test
    public void locationNameNotInvalid() {
        String name = FileUtils.getFileLocation();
        
        FileUtils.setFileLocation(null);
        Assert.assertTrue(name.equals(FileUtils.getFileLocation()));

        FileUtils.setFileLocation("");
        Assert.assertTrue(name.equals(FileUtils.getFileLocation()));        
        
    }
    
    @Test
    public void setNameCahngesName() {
        String name = "asd";
        FileUtils.setFileLocation(name);
        Assert.assertTrue(name.equals(FileUtils.getFileLocation()));  
        
        name = "dsa";
        FileUtils.setFileLocation(name);
        Assert.assertTrue(name.equals(FileUtils.getFileLocation())); 
        
    }
}
