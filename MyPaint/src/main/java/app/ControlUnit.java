package app;

import ui.MyWindow;
import app.cmd.*;
import java.util.HashMap;
import tools.Area;

public class ControlUnit implements Runnable {

    public final int drawCMD = 1;

    private PaintBrush colors;
    private int currentCMD;
    private HashMap<Integer, CMD> cmds;
    private MyImage img;

    private boolean init = false;

    public void init(HashMap<Integer, CMD> cmds, int width, int height) {
        img = new MyImage(width, height);
        Area.setBounds(width, height);
        
        if (cmds == null || cmds.size() == 0) {
            initDefaultCommands();
        } else {
            this.cmds = cmds;
        }
        colors = new PaintBrush(img.getGraphics());
        init = true;
    }

    private void initDefaultCommands() {
        cmds = new HashMap<>();
        DrawLine draw = new DrawLine(this);
        cmds.put(1, draw);
        currentCMD = drawCMD;
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

    public MyImage getImg() {
        return img;
    }

    public void setActiveCMD(int key) {
        if (cmds.containsKey(key)) {
            this.currentCMD = key;
        }
    }

    public PaintBrush getColors() {
        return colors;
    }

}
