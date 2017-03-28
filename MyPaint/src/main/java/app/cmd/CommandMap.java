package app.cmd;

import app.PaintBrush;
import java.util.HashMap;

public class CommandMap {

    public static final int PICKCOLOR = 0;
    public static final int DRAWLINE = 1;
    public static final int DRAWRECT = 2;
    public static final int FILLRECT = 3;

    public static HashMap<Integer, CMD> createCommandMap(PaintBrush brush) {
        HashMap<Integer, CMD> cmds = new HashMap<>();

        cmds.put(DRAWLINE, new DrawLine());
        cmds.put(DRAWRECT, new DrawRect());
        cmds.put(FILLRECT, new FillRect());
        cmds.put(PICKCOLOR, new SetAvgColor(brush));

        return cmds;
    }
}
