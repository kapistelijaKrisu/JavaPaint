package tools;

public class Area {
    private static final int MIN_VAL = 0;
    private static int maxX = MIN_VAL, maxY = MIN_VAL;
    
    private int startX, startY;

    private int curX, curY;
    private int lastX, lastY;

    public Area(int x, int y) {
        init(x, y);
    }
    
    private Area(int x, int y, int x2, int y2) {
        startX = Math.min(x, x2);
        startY = Math.min(y, y2);
        
        curX = fixToRange(Math.abs(x - x2), true);
        curY = fixToRange(Math.abs(y - y2), false);
        
        lastX = curX;
        lastY = curY;
        
        System.out.println("x:" + startX + " y:" + startY+ " x2:" + curX + " y2:" +curY);
    }

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

    public void udpate(int x, int y) {
        x = fixToRange(x, true);
        y = fixToRange(y, false);
        lastX = curX;
        lastY = curY;
        curX = x;
        curY = y;
    }

    private int fixToRange(int a, boolean x) {
        a = Math.max(MIN_VAL, a);
        if (x) {
            a = Math.min(a, maxX);
        } else {
            a = Math.min(a, maxY);
        }
        return a;
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

    public static void setBounds(int maxX, int maxY) {
        maxX = Math.max(MIN_VAL, maxX);
        maxY = Math.max(maxY, MIN_VAL);
        Area.maxX = maxX;
        Area.maxY = maxY;
    }

    
   
    public Area getRectangle() {
        System.out.println("x:" + startX + " y:" + startY+ " x2:" + curX + " y2:" +curY);
        return new Area(startX, startY, curX, curY);    
        
    }


}
