package app;

import app.cmd.CommandMap;
import app.cmd.CMD;
import java.util.HashMap;
import tools.Area;

/**
 * 
 * <p>The core of the application which by itself does nothing and only works upon user calling it to manipulate MyImage.</p>
 */
public class ControlUnit implements Runnable {
    
    private int currentCMD;
    private HashMap<Integer, CMD> cmds;
    private MyImage img;

    private boolean init = false;

    public void init(int width, int height) {
        img = new MyImage(width, height);
        Area.setBounds(width-1, height-1);
        cmds = CommandMap.createCommandMap();
        currentCMD = CommandMap.DRAWLINE;
        init = true;
    }

    @Override
    public void run() {
        if (!init) {
            throw new IllegalStateException();
        }
    }

    public void execute(Area a) {
        cmds.get(currentCMD).execute(img, a);
    }

    public void setActiveCMD(int key) {
        if (cmds.containsKey(key)) {
            this.currentCMD = key;
        }
    }

    public boolean getInit() {
        return init;
    }

    public int getCurrentCMD() {
        return currentCMD;
    }

    public MyImage getImg() {
        return img;
    }

}
