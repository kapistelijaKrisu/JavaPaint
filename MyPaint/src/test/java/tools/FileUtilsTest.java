
package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {
    
    File output;

    @After
    public void setDefaultName() {
        FileUtils.setFileLocation("Untitiled");
    }

    
    @Test
    public void nameIsNotNull() {
        Assert.assertFalse(FileUtils.getFileName() == null);
    }
    
    @Test
    public void locationNameNotInvalid() {
        String name = FileUtils.getFileName();
        
        FileUtils.setFileLocation(null);
        Assert.assertTrue(name.equals(FileUtils.getFileName()));

        FileUtils.setFileLocation("");
        Assert.assertTrue(name.equals(FileUtils.getFileName()));        
        
    }
    
    @Test
    public void setNameCahngesName() {
        String name = "asd";
        FileUtils.setFileLocation(name);
        Assert.assertTrue(name.equals(FileUtils.getFileName()));  
        
        name = "dsa";
        FileUtils.setFileLocation(name);
        Assert.assertTrue(name.equals(FileUtils.getFileName())); 
        
    }
    
    @Test
    public void savesRight() {
 
        
            FileUtils.setFileLocation("asd");
            FileUtils.setFormat("asd");
            FileUtils.saveFile(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
            output = new File(FileUtils.getFileName()+"."+FileUtils.getFormat());
            Assert.assertEquals("asd.png", output.getPath());    
            output.delete();
        
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
