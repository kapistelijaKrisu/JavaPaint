package app.cmd;

import java.util.HashMap;

/**
 * <p>
 * Contains each type of CMD implementation in hashmap capsuled from misuse.
 * default accessible CMD is DrawLine.</p>
 * <p>
 * Each command codekey is found from public static final variables.</p>
 *
 */
public class CommandMap {

    public static final int REPLACECOLOR = -2;
    public static final int FILLCOLOR = -1;
    public static final int PICKCOLOR = 0;
    public static final int DRAWLINE = 1;
    public static final int DRAWRECT = 2;
    public static final int FILLRECT = 3;
    public static final int DRAWELLIPSE = 4;
    public static final int FILLELLIPSE = 5;

    private final HashMap<Integer, CMD> cmds;

    private int currentCMD;

    /**
     * Creates all implementations of CMD and puts them into its own HashMap which is capsuled from misuse.
     */
    public CommandMap() {
        cmds = new HashMap<>();
        currentCMD = DRAWLINE;

        cmds.put(DRAWLINE, new DrawLine());
        cmds.put(DRAWRECT, new DrawRect());
        cmds.put(FILLRECT, new FillRect());
        cmds.put(PICKCOLOR, new SetAvgColor());
        cmds.put(FILLCOLOR, new FillColor());
        cmds.put(REPLACECOLOR, new ReplaceColor());
        cmds.put(DRAWELLIPSE, new DrawEllipse());
        cmds.put(FILLELLIPSE, new FillEllipse());

    }

    public CMD getCurrentCMD() {
        return cmds.get(currentCMD);
    }

    /**
     *
     * @param key - If key exists sets accessible CMD retrieved by key. Does
     * nothing if key doesnt exist.
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
