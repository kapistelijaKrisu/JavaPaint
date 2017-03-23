package tools;

public final class FileUtils {

    private static String fileLocation = "Untitled";

    private FileUtils() {
        throw new UnsupportedOperationException(("don't instantiate this class!"));
    }

    public static void setFileLocation(String fileLocation) {
        if (fileLocation == null || fileLocation.isEmpty()) {
            return;
        }
        FileUtils.fileLocation = fileLocation;
    }
/* later date
    public static void saveFile(BufferedImage img) {

    }

    public static void loadFile(String path, ControlUnit app) {

    }*/

    public static String getFileLocation() {
        return fileLocation;
    }
    
    
}
