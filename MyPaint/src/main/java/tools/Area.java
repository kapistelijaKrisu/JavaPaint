
package tools;

public class Area {
    
    public int x, y, width, height;
    public int updateX, updateY;

    public Area(int x, int y, int x2, int y2) {
        set(x, y, x2, y2);
        updateX = -1;
        updateY = -1;
    }
    
    public void udpate(int x, int y) {
        updateX = x;
        updateY = y;
    }
    
    public void set(int x1, int y1, int x2, int y2) {
         width = Math.abs(x1-x2) + 1;       
         height = Math.abs(y1-y2) + 1;
        
         x = Math.min(x1, x2);
        x = Math.max(x,0);
        
         y = Math.min(y1, y2);      
        y = Math.max(y, 0);
    }
    
    public void reset() {
        x = -1;
        y = -1;
        width = 0;
        height = 0;
        updateX = -1;
        updateY = -1;
    }
}
