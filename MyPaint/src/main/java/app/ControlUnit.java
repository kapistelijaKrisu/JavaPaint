package app;

import app.cmd.*;
import java.util.HashMap;
import tools.Area;

public class ControlUnit implements Runnable {

    

    private PaintBrush brush;
    private int currentCMD;
    private HashMap<Integer, CMD> cmds;
    private MyImage img;

    private boolean init = false;

    public void init(int width, int height) {
        img = new MyImage(width, height);
        Area.setBounds(width, height);
        brush = new PaintBrush(img.getGraphics(), 5, true);
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
        cmds.get(currentCMD).execute(img.getImg(), a);
    }

    public MyImage getImg() {
        return img;
    }

    public void setActiveCMD(int key) {
        if (cmds.containsKey(key)) {
            this.currentCMD = key;
        }
    }

    public PaintBrush getPaintBrush() {
        return brush;
    }

    //test
    public boolean getInit() {
        return init;
    }

    public int getCurrentCMD() {
        return currentCMD;
    }
}
