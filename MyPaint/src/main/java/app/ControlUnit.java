package app;

import app.cmd.CommandMap;
import java.awt.image.BufferedImage;
import tools.TwoPoint;

/**
 *
 * <p>
 * The core of the application which by itself does nothing and only works upon
 * user calling it to manipulate MyImage.</p>
 */
public class ControlUnit implements Runnable {

    private final CommandMap cmds;
    private MyImage img;
    private Log log;
    private boolean logging;

    public ControlUnit() {
        cmds = new CommandMap();
        log = new Log();
        img = new MyImage(256, 256);
        logging = true;
    }

    public ControlUnit(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        cmds = new CommandMap();
        log = new Log();
        img = new MyImage(width, height);
        logging = true;
    }

    /**
     *
     * @param image sets the working image as image and adds previous image to
     * log history
     */
    public void setImage(BufferedImage image) {
        updateHistory();
        img.setImg(image);
    }

    /**
     * does nothing <br>
     * all the commands are given externally.
     */
    @Override
    public void run() {
    }

    /**
     * <p>
     * adds to log current image of MyImage first. Then calls currently set CMD
     * execute().</p>
     * <p>
     * @param a coordinate information for CMD implementations to use</p>
     */
    public void execute(TwoPoint a) {
        if (logging) {
            updateHistory();
        }
        cmds.getCurrentCMD().execute(img, a);
    }

    /**
     * <p>
     * sets active CMD on its cmds if value is legal. Does nothing if value is
     * illegal.</p>
     *
     * @param key see CommandMap key values
     */
    public void setActiveCMD(int key) {
        cmds.setCMD(key);
    }

    /**
     * adds current MyImage image onto log.
     */
    private void updateHistory() {
        log.archieveImage(img.getImg());
    }

    /**
     * gets previous step if there is one from log. Sets said previous step as
     * MyImage image
     */
    public void undo() {
        BufferedImage prev = log.popPrevious(img.getImg());
        if (prev != null) {
            img.setImg(prev);
        }
    }

    /**
     * gets redo step if there is one from log. Sets said redo step as MyImage
     * image
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
