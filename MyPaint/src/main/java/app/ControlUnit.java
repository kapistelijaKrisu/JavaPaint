package app;

import ui.MyWindow;
import app.cmd.*;
import java.util.HashMap;
import tools.Area;

public class ControlUnit implements Runnable {

    public static final int defaultDrawCMD = 1;
    public static final int defaultRectCMD = 2;

    private PaintBrush brush;
    private int currentCMD;
    private HashMap<Integer, CMD> cmds;
    private MyImage img;

    private boolean init = false;

    public void init(int width, int height) {
        img = new MyImage(width, height);
        Area.setBounds(width, height);

        initDefaultCommands();

        brush = new PaintBrush(img.getGraphics(), 5, true);

        init = true;
    }

    private void initDefaultCommands() {
        cmds = new HashMap<>();
        DrawLine draw = new DrawLine(this);
        cmds.put(defaultDrawCMD, draw);
        cmds.put(defaultRectCMD, new FillRect(this));
        currentCMD = defaultDrawCMD;
    }

    @Override
    public void run() {
        if (!init) {
            throw new IllegalStateException();
        }
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
