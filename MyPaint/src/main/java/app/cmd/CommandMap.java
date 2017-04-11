package app.cmd;

import java.util.HashMap;

/**
 * <p>Contains each type of CMD implementation in hashmap hidden form misuse.
 * default accessible CMD is DrawLine.</p>
 * <p>Defines codenames of each key.</p>
 * 
 */
public class CommandMap {
    public static final int REPLACECOLOR = -2;
    public static final int FILLCOLOR = -1;
    public static final int PICKCOLOR = 0;
    public static final int DRAWLINE = 1;
    public static final int DRAWRECT = 2;
    public static final int FILLRECT = 3;

    private final HashMap<Integer, CMD> cmds;
    
    private int currentCMD;
    
    public CommandMap() {
        cmds = new HashMap<>();
        currentCMD = DRAWLINE;

        cmds.put(DRAWLINE, new DrawLine());
        cmds.put(DRAWRECT, new DrawRect());
        cmds.put(FILLRECT, new FillRect());
        cmds.put(PICKCOLOR, new SetAvgColor());
        cmds.put(FILLCOLOR, new FillColor());
        cmds.put(REPLACECOLOR, new ReplaceColor());

    }
    
    public CMD getCurrentCMD() {
        return cmds.get(currentCMD);
    }
    /**
     * 
     * @param key - If key exists sets accessible CMD retrieved by key.
     * Does nothing if key doesnt exist
     */
    public void setCMD(int key) {
        if (cmds.containsKey(key)) {
            currentCMD = key;
        }
    }

    public int getCurrentKey() {
        return currentCMD;
    }
}
