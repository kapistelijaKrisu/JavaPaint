package app.cmd;

import app.MyImage;
import java.util.HashMap;

/**
 * 
 * <p>Defines codenames of each key and may create a hashmap of commands according to the keys.</p>
 * 
 */
public class CommandMap {
    public static final int FILLCOLOR = -1;
    public static final int PICKCOLOR = 0;
    public static final int DRAWLINE = 1;
    public static final int DRAWRECT = 2;
    public static final int FILLRECT = 3;

    public static HashMap<Integer, CMD> createCommandMap() {
        HashMap<Integer, CMD> cmds = new HashMap<>();

        cmds.put(DRAWLINE, new DrawLine());
        cmds.put(DRAWRECT, new DrawRect());
        cmds.put(FILLRECT, new FillRect());
        cmds.put(PICKCOLOR, new SetAvgColor());
        cmds.put(FILLCOLOR, new FillColor());

        return cmds;
    }
}
