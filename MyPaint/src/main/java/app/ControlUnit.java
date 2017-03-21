package app;

import UI.MyWindow;
import app.cmd.CMD;
import app.cmd.FillPoint;
import java.util.HashMap;

public class ControlUnit implements Runnable {
    public final int DRAW_CMD = 1;

    private ColorProfile colors;
    private int currentCMD;
    private HashMap<Integer, CMD> cmds;
    private MyImage img;
    
    private boolean init = false;

    public void init(HashMap<Integer, CMD> cmds) {
        
        if (cmds == null || cmds.size() == 0) {
            initDefaultCommands();
        } else {
            this.cmds = cmds;
        }
        colors = new ColorProfile();          
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
        if (!init) return;
        
        img = new MyImage(500, 400);
        AreaMaker.xMax = img.getImg().getWidth();
        AreaMaker.yMax = img.getImg().getHeight();
        
        MyWindow window = new MyWindow(this);
        window.repaint();
    }

    public MyImage getImg() {
        return img;
    }

    public void setImg(MyImage img) {
        this.img = img;
    }

    public void setActiveCMD(int key) {
        if (cmds.containsKey(key)) {
            this.currentCMD = key;
        }
    }

    public void execute(Area a) {
        cmds.get(currentCMD).execute(a);
    }

    public ColorProfile getColors() {
        return colors;
    }
    
    
    
}
