package app;

import app.cmd.CommandMap;
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

    private boolean init = false;

    public ControlUnit() {
        cmds = new CommandMap();
        
    }
    
    public void init(int width, int height) {
        img = new MyImage(width, height);
        Area.setBounds(width - 1, height - 1);
        
        init = true;
    }

    @Override
    public void run() {
        if (!init) {
            throw new IllegalStateException();
        }
    }

    public void execute(Area a) {
        img.updateHistory();
        cmds.getCurrentCMD().execute(img, a);
    }

    public void setActiveCMD(int key) {
        cmds.setCMD(key);
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

}
