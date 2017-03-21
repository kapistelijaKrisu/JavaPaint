package app;

import UI.MyWindow;
import app.cmd.*;
import java.util.HashMap;
import tools.Area;

public class ControlUnit implements Runnable {

    public final int DRAW_CMD = 1;

    private ColorProfile colors;
    private int currentCMD;
    private HashMap<Integer, CMD> cmds;
    private MyImage img;

    private boolean init = false;

    public void init(HashMap<Integer, CMD> cmds) {
        img = new MyImage(500, 400);
        if (cmds == null || cmds.size() == 0) {
            initDefaultCommands();
        } else {
            this.cmds = cmds;
        }
        colors = new ColorProfile(img.getGraphics());        
        init = true;
    }

    private void initDefaultCommands() {
        cmds = new HashMap<>();
        FillPoint draw = new FillPoint(this);
        cmds.put(1, draw);
        currentCMD = DRAW_CMD;
    }

    @Override
    public void run() {
        if (!init) {
            return;
        }
        MyWindow window = new MyWindow(this);
        window.repaint();
    }

    public void execute(Area a) {
        cmds.get(currentCMD).execute(a);
    }

    public void resetCMD() {
        cmds.get(currentCMD).reset();
    }

    public MyImage getImg() {
        return img;
    }

    public void setActiveCMD(int key) {
        if (cmds.containsKey(key)) {
            this.currentCMD = key;
        }
    }

    public ColorProfile getColors() {
        return colors;
    }

}
