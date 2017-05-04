package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * <p>
 * Utility class that only is used to save and load files.</p>
 *
 * <p>
 * format May be png or jpg. filename Name of the saved file and must not be
 * empty.</p>
 */
public final class FileUtils {

    private static String fileName = "Untitled";
    private static String format = "png";

    /**
     * illegal to make an instance of this class. Will throw an exeption if you
     * do.
     */
    private FileUtils() {
        throw new UnsupportedOperationException(("don't instantiate this class!"));
    }

    /**
     *
     * @param fileName Sets name for filename and checks for fileName not to be
     * empty or null
     */
    public static void setFileLocation(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        FileUtils.fileName = fileName;
    }

    /**
     *
     * @param file Pointer to image
     * @return BufferedImage as BufferedImage.TYPE_INT_ARGB regardless of file
     * original extension. <br>
     * returns null if fails.
     */
    public static BufferedImage loadImageAsARGB(File file) {

        BufferedImage ranodmFormatted = null;

        try {
            ranodmFormatted = ImageIO.read(file);
        } catch (IOException | IllegalArgumentException ex) {
            return null;
        }

        BufferedImage alphaImg = new BufferedImage(ranodmFormatted.getWidth(),
                ranodmFormatted.getHeight(), BufferedImage.TYPE_INT_ARGB);
        alphaImg.createGraphics().drawImage(ranodmFormatted, 0, 0, null);
        return alphaImg;

    }

    /**
     *
     * @param img BufferedImage to be saved on a file according to filename and
     * its format Does nothing if exception occurs.
     */
    public static void saveFile(BufferedImage img) {

        File outputfile = new File(fileName + "." + format);
        try {
            ImageIO.write(img, format, outputfile);

        } catch (IOException | IllegalArgumentException ex) {
            //nothing should happen if exception occurs
        }
    }

    /**
     *
     * @param format Sets format to png or jpg. If format is something else does
     * nothing
     */
    public static void setFormat(String format) {
        if (format == null) {
            return;
        }
        if (format.equals("png") || format.equals("jpg")) {
            FileUtils.format = format;
        }
    }

    public static String getFormat() {
        return format;
    }

    public static String getFileName() {
        return fileName;
    }

}
