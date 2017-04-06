package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * <p>Utility class that only is used to save and load files.</p>
 * 
 * <p>format - May be png or jpg.<br>
 * filename - Name of the saved file and must not be empty.</p>
 */
public final class FileUtils {

    private static String fileName = "Untitled";
    private static String format = "png";

    private FileUtils() {
        throw new UnsupportedOperationException(("don't instantiate this class!"));
    }

    public static void setFileLocation(String fileLocation) {
        if (fileLocation == null || fileLocation.isEmpty()) {
            return;
        }
        FileUtils.fileName = fileLocation;
    }

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
