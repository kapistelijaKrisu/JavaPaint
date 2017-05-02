package tools;

import java.awt.Rectangle;

/**
 *
 * <p>
 * Class used to format data that implements CMD.</p>
 * <p>
 * Contains previous pixel and current pixel as int x,y</p>
 *
 */
public final class TwoPoint {

    private static final int MIN_VAL = 0;
    private static int maxX = MIN_VAL, maxY = MIN_VAL;

    private int prevX, prevY;

    private int curX, curY;

    public TwoPoint(int x, int y) {
        setAll(x, y);
    }

    

    /**
     * 
     * @param x - sets all x's
     * @param y  sets all y's
     * 
     * checks values legality and fixes them with fixToRange()
     */
    public void setAll(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        prevX = x;
        prevY = y;
        curX = prevX;
        curY = prevY;
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
        prevX = curX;
        prevY = curY;
        curX = x;
        curY = y;
    }
    
    /**
     * <p>parameters are checked and fixed with fixToRange()
     * 
     * @param x - new curX
     * @param y - new curY</p>
     * <p>
     * prevX and prevY remains unchanged.
     */
    public void udpateCurrents(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
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

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    /**
     * 
     * @param maxX maximum value that x's can have <br>
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
     * @return Rectangle measurments based on coordinate values.
     */
    public Rectangle getRectangle() {        
        int x = Math.min(prevX, curX);
        int y = Math.min(prevY, curY);

        int width = fixToRange(Math.abs(prevX - curX), true);
        int height = fixToRange(Math.abs(prevY - curY), false);
        
        return new Rectangle(x, y, width, height);

    }

}
