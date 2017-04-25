
package app.cmd;

import app.MyImage;
import tools.Area;
import tools.OneLineException;

/**
 * 
 * <p>An implementation of CMD which fills a replaces a color of MyImage to color it has been set.</p>
 * 
 */
public class ReplaceColor implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
        int toReplace = img.getImg().getRGB(area.getCurX(), area.getCurY());
        int height = img.getImg().getHeight();
        int width = img.getImg().getWidth();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (img.getImg().getRGB(x, y) == toReplace) {
                    img.getImg().setRGB(x, y, img.getColor().getRGB());
                }
                
            }
            
        }
        
    }
    
}
