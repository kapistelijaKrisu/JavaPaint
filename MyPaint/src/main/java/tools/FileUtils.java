package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * <p>Utility class that only is used to save and load files.</p>
 * 
 * <p>format May be png or jpg.
 * filename Name of the saved file and must not be empty.</p>
 */
public final class FileUtils {

    private static String fileName = "Untitled";
    private static String format = "png";

    /**
     * illegal to make an instance of this class. Will throw an exeption if you try.
     */
    private FileUtils() {
        throw new UnsupportedOperationException(("don't instantiate this class!"));
    }

    /**
     * 
     * @param fileName sets name for filename and checks for fileName not to be empty or null
     */
    public static void setFileLocation(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        FileUtils.fileName = fileName;
    }

    /**
     * 
     * @param img BufferedImage to be saved on a file according to filename and its format
     * Does nothing if exception occurs.
     */
    public static void saveFile(BufferedImage img) {

        File outputfile = new File(fileName + "." + format);
        try {
            ImageIO.write(img, format, outputfile);

        } catch (IOException ex) {
            //nothing should happen if exception occurs
        }
    }

    /*
    public static void loadFile(String path, ControlUnit app) {

    }*/

    public static String getFileName() {
        return fileName;
    }

    /**
     * 
     * @param format sets format to png or jpg. If format is something else does nothing
     */
    public static void setFormat(String format) {
        if (format == null) return;
        if (format.equals("png") || format.equals("jpg")) {
            FileUtils.format = format;
        }
    }

    public static String getFormat() {
        return format;
    }

}
