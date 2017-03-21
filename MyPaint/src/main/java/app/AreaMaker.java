
package app;

public class AreaMaker {
    public static int xMax, yMax;
    
    public static Area convert(int x1, int y1, int x2, int y2) {
        int width = Math.abs(x1-x2);       
        int height = Math.abs(y1-y2);
        
        int x = Math.min(x1, x2) - width / 2;
        x = Math.max(x,0);
        
        int y = Math.min(y1, y2) - height / 2;      
        y = Math.max(y, 0);
        
        
        return new Area(x, y, width, height);
    }
    public static Area convert(int x, int y, int size) {
        x = x - size / 2;
        x = Math.max(x,0);
        
        y = y - size / 2;      
        y = Math.max(y, 0);
        
        
        return new Area(x, y, size, size);
    }

}
