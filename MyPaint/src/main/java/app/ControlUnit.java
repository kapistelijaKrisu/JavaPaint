package app;

import app.cmd.CommandMap;
import java.awt.image.BufferedImage;
import tools.TwoPoint;

/**
 *
 * The core of the application which by itself does nothing and only works upon
 * user calling it to manipulate MyImage.
 */
public class ControlUnit {

    private final CommandMap cmds;
    private final MyImage img;
    private Log log;
    private boolean logging;

    /**
     * Initializes its own variables. MyImages default size is 256x256.
     */
    public ControlUnit() {
        cmds = new CommandMap();
        log = new Log();
        img = new MyImage(256, 256);
        logging = true;
    }

    /**
     * Initializes its own variables. Checks for image values not to be below 1.
     * If rule is broken throws IllegalArgumentsException.
     *
     * @param imageWidth width of initial image.
     * @param imageHeight height of initial image.
     */
    public ControlUnit(int imageWidth, int imageHeight) {
        if (imageWidth < 1 || imageHeight < 1) {
            throw new IllegalArgumentException();
        }
        cmds = new CommandMap();
        log = new Log();
        img = new MyImage(imageWidth, imageHeight);
        logging = true;
    }

    /**
     *
     * @param image Sets this on myImage and adds previous image to log history
     * if logging is set true.
     */
    public void setImage(BufferedImage image) {
        if (logging) {
            updateHistory();
        }
        img.setImg(image);
    }

    /**
     *
     * Adds to log current image of MyImage first if logging is true. Then calls
     * currently set CMD to execute.
     *
     * @param info coordinate information for CMD implementations to use
     */
    public void execute(TwoPoint info) {
        if (logging) {
            updateHistory();
        }
        cmds.getCurrentCMD().execute(img, info);
    }

    /**
     *
     * Sets active CMD found by key from its commandMap as its active CMD. Does
     * nothing if commmandMap does not contain the key.
     *
     * @param key see CommandMap key values
     */
    public void setActiveCMD(int key) {
        cmds.setCMD(key);
    }

    /**
     * Adds current image onto log.
     */
    private void updateHistory() {
        log.archieveImage(img.getImg());
    }

    /**
     * Gets previous image from log and sets it as active image if there is one
     * and saves current image onto log.
     */
    public void undo() {
        BufferedImage prev = log.popPrevious(img.getImg());
        if (prev != null) {
            img.setImg(prev);
        }
    }

    /**
     * Gets redo image from log and sets it as active image if there is one and
     * saves current image onto log.
     *
     */
    public void redo() {
        BufferedImage next = log.popNext(img.getImg());
        if (next != null) {
            img.setImg(next);
        }
    }

    public int getCurrentCMD() {
        return cmds.getCurrentKey();
    }

    public MyImage getImg() {
        return img;
    }

    public Log getLog() {
        return log;
    }

    public void setLogging(boolean logging) {
        this.logging = logging;
    }
}
