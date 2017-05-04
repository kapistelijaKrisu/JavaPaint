package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class FileUtilsTest {

    File output;

    @After
    public void setDefaults() {
        FileUtils.setFileLocation("Untitiled");
        FileUtils.setFormat("png");
        File f = new File("asd.png");
        try {
            Files.delete(f.toPath());
        } catch (IOException ex) {
        }

        File f2 = new File("asd.jpg");
        try {
            Files.delete(f2.toPath());
        } catch (IOException ex) {
        }
    }

    @Test
    public void nameIsNotNull() {
        Assert.assertFalse(FileUtils.getFileName() == null);
    }

    @Test
    public void formatIsValid() {
        Assert.assertFalse(FileUtils.getFormat() == null);
        FileUtils.setFormat("jpg");
        Assert.assertTrue(FileUtils.getFormat().equals("jpg"));
        FileUtils.setFormat("asd");
        Assert.assertTrue(FileUtils.getFormat().equals("jpg"));
        FileUtils.setFormat(null);
        Assert.assertTrue(FileUtils.getFormat().equals("jpg"));
        FileUtils.setFormat("png");
        Assert.assertTrue(FileUtils.getFormat().equals("png"));
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
    public void savesRightTest() {

        FileUtils.setFileLocation("asd");
        FileUtils.setFormat("asd");
        FileUtils.saveFile(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
        output = new File(FileUtils.getFileName() + "." + FileUtils.getFormat());
        Assert.assertEquals("asd.png", output.getPath());

        FileUtils.setFileLocation("dsa");

        FileUtils.saveFile(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
        output = new File(FileUtils.getFileName() + "." + FileUtils.getFormat());
        Assert.assertEquals("dsa.png", output.getPath());
        output.delete();

    }

    @Test
    public void constructorIsPrivateTest() throws Exception {
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

    @Test
    public void openRightTest() {
        FileUtils.setFileLocation("asd");
        FileUtils.setFormat("jpg");
        FileUtils.saveFile(new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
        output = new File(FileUtils.getFileName() + "." + FileUtils.getFormat());
        BufferedImage img = FileUtils.loadImageAsARGB(output);

        Assert.assertEquals(BufferedImage.TYPE_INT_ARGB, img.getType());
        output.delete();

        FileUtils.setFileLocation("aaa");
        FileUtils.setFormat("png");
        FileUtils.saveFile(new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));
        output = new File(FileUtils.getFileName() + "." + FileUtils.getFormat());
        img = FileUtils.loadImageAsARGB(output);

        Assert.assertEquals(BufferedImage.TYPE_INT_ARGB, img.getType());
        output.delete();

    }

    @Test
    public void nullParamsDoNohing() {
        output = null;
        BufferedImage img = FileUtils.loadImageAsARGB(output);
        Assert.assertEquals(null, img);

        FileUtils.saveFile(img);
        Assert.assertTrue(true);
    }
}
