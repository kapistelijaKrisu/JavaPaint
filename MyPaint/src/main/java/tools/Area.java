package tools;

/**
 *
 * <p>
 * Class used to format data that implements CMD.</p>
 * <p>
 * Contains starting pixel, previous pixel, current pixel as int x,y</p>
 *
 */
public final class Area {

    private static final int MIN_VAL = 0;
    private static int maxX = MIN_VAL, maxY = MIN_VAL;

    private int startX, startY;

    private int curX, curY;
    private int lastX, lastY;

    public Area(int x, int y) {
        init(x, y);
    }

    /**
     * 
     * <p>private constructor made for setting parameters into x,y,width,height format by finding smallest x,y and taking the absolute difference of bigger x,y as width,height.</p>
     * <p>
     * @param x will be startX
     * @param y will be startY
     * @param width will be currentX and lastX
     * @param height y will be currentY and lastY
     */
    private Area(int x, int y, int width, int height) {
        startX = Math.min(x, width);
        startY = Math.min(y, height);

        curX = fixToRange(Math.abs(x - width), true);
        curY = fixToRange(Math.abs(y - height), false);

        lastX = curX;
        lastY = curY;

    }

    /**
     * 
     * @param x - sets all x's
     * @param y  sets all y's
     * 
     * checks values legality and fixes them with fixToRange()
     */
    public void init(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        lastX = x;
        lastY = y;
        startX = x;
        startY = y;
        curX = startX;
        curY = startY;
    }

    /**
     * <p>parameters are checked and fixed with fixToRange()
     * 
     * @param x - new curX
     * @param y - new curY</p>
     * <p>
     * prevX will be previous curX <br>
     * prevY will be previous curX </p>
     */
    public void udpate(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        lastX = curX;
        lastY = curY;
        curX = x;
        curY = y;
    }

    /**
     * 
     * @param value value to be fixed.
     * @param x - true = value is fixed according to x-axis, else value is fixed according to y-axis
     * @return returns legal value of value. No changes will be made if value is legal. 
     */
    private int fixToRange(int value, boolean x) {
        value = Math.max(MIN_VAL, value);
        if (x) {
            value = Math.min(value, maxX);
        } else {
            value = Math.min(value, maxY);
        }
        return value;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    /**
     * 
     * @param maxX maximum value that x's can have <br>
     * @param maxY maximum value that y's can have
     */
    public static void setBounds(int maxX, int maxY) {
        maxX = Math.max(MIN_VAL, maxX);
        maxY = Math.max(maxY, MIN_VAL);
        Area.maxX = maxX;
        Area.maxY = maxY;
    }

    /**
     * 
     * @return return itself in x,y,width, height format. See private constructor.
     */
    public Area getRectangle() {
        return new Area(startX, startY, curX, curY);

    }

}
