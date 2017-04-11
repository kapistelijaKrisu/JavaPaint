package app;

import app.cmd.CommandMap;
import java.awt.image.BufferedImage;
import tools.Area;

/**
 *
 * <p>
 * The core of the application which by itself does nothing and only works upon
 * user calling it to manipulate MyImage.</p>
 */
public class ControlUnit implements Runnable {

    private final CommandMap cmds;
    private MyImage img;
    private final Log log;
    private boolean init = false;

    public ControlUnit() {
        cmds = new CommandMap();
        log = new Log();
    }

    /**
     * <p>
     * Sets initial MyImage width and height.
     * Allows usage of run after the call has been completed</p>
     *
     * @param width height of initial MyImage 
     * @param height width of initial MyImage
     */
    public void init(int width, int height) {
        img = new MyImage(width, height);
 

        init = true;
    }

    /**
     * does nothing but checks whether this object has been initialized and
     * throws exception if not. <br>
     * all the commands are given externally.
     */
    @Override
    public void run() {
        if (!init) {
            throw new IllegalStateException();
        }
    }

    /**
     * <p>
     * adds to log current image of MyImage first. Then calls currently set CMD
     * execute().</p>
     * <p>
     * @param a coordinate information for CMD implementations to use</p>
     */
    public void execute(Area a) {
        updateHistory();
        cmds.getCurrentCMD().execute(img, a);
    }

    /**<p>
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

    public boolean getInit() {
        return init;
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
}
