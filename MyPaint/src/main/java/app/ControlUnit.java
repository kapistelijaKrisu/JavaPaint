package app;

import app.cmd.CommandMap;
import app.cmd.CMD;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
        Area.setBounds(width-1, height-1);
        
        currentCMD = CommandMap.DRAWLINE;
        brush = new PaintBrush(5, true);
        cmds = CommandMap.createCommandMap(brush);
        brush.installSetting(img.getGraphics(), true, true, true);
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

    public void activateSettings(boolean setColor, boolean setComposite, boolean setWidth) {
        brush.installSetting(img.getGraphics(), setColor, setComposite, setWidth);
    }

    public BufferedImage getImg() {
        return img.getImg();
    }
    public Graphics2D getGraphics() {
        return img.getGraphics();
    }
    
    //test

    public PaintBrush getBrush() {
        return brush;
    }
    
    
    
    
}
