package tools;

import java.awt.Rectangle;

/**
 *
 * <p>
 * Class used to format data that implements CMD.</p>
 * <p>
 * Contains previous pixel and current pixel as integer values <br>
 * previousX, previousY, currentX, currentY</p>
 * <p>
 * Will never go below 0 or exceed its static max values</p>
 *
 */
public final class TwoPoint {

    private static final int MIN_VAL = 0;
    private static int maxX = MIN_VAL, maxY = MIN_VAL;

    private int previousX, previousY;

    private int currentX, currentY;

    public TwoPoint(int x, int y) {
        setAll(x, y);
    }

    /**
     * checks legality of values and fixes them.
     *
     * @param x - sets all x's
     * @param y sets all y's
     *
     */
    public void setAll(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        previousX = x;
        previousY = y;
        currentX = previousX;
        currentY = previousY;
    }

    /**
     * previousX will be previous currentX <br>
     * previousY will be previous currentX <br>
     * parameters are checked and fixed with fixToRange()
     *
     * @param x - new curX
     * @param y - new curY
     *
     */
    public void jump(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        previousX = currentX;
        previousY = currentY;
        currentX = x;
        currentY = y;
    }

    /**
     *
     * parameters are checked and fixed if needed.<br>
     * previousX and previousY remains unchanged.
     *
     * @param x - new curX
     * @param y - new curY
     *
     *
     */
    public void udpateCurrents(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        currentX = x;
        currentY = y;
    }

    /**
     *
     * @param value value to be fixed.
     * @param x - true = value is fixed according to x-axis, else value is fixed
     * according to y-axis.
     * @return returns legal value of value. No changes will be made if value is
     * legal.
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

    public int getPrevX() {
        return previousX;
    }

    public int getPrevY() {
        return previousY;
    }

    public int getCurX() {
        return currentX;
    }

    public int getCurY() {
        return currentY;
    }

    /**
     *
     * @param maxX maximum value that x's can have
     * @param maxY maximum value that y's can have
     */
    public static void setBounds(int maxX, int maxY) {
        maxX = Math.max(MIN_VAL, maxX);
        maxY = Math.max(maxY, MIN_VAL);
        TwoPoint.maxX = maxX;
        TwoPoint.maxY = maxY;
    }

    /**
     *
     * @return Rectangle measurments calculated from previous and current x and
     * y values.
     */
    public Rectangle getRectangle() {
        int x = Math.min(previousX, currentX);
        int y = Math.min(previousY, currentY);

        int width = fixToRange(Math.abs(previousX - currentX), true);
        int height = fixToRange(Math.abs(previousY - currentY), false);

        return new Rectangle(x, y, width, height);

    }

}
