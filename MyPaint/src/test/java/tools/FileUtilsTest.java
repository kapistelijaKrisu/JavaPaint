
package tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
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
    
    @Test
    public void testConstructorIsPrivate() throws Exception {
      Constructor constructor = FileUtils.class.getDeclaredConstructor();
      assertTrue(Modifier.isPrivate(constructor.getModifiers()));
      constructor.setAccessible(true);
      
      try {
        constructor.newInstance();
        assertTrue(false); //should throw error
      } catch (UnsupportedOperationException | InvocationTargetException e) {
          assertTrue(true);
      }
    }
}
