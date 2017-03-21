
package app.cmd;

import app.Area;
import app.ControlUnit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FillPoint extends CMD {
    private int prevX = -1, prevY= -1;
    public FillPoint(ControlUnit controller) {
        super(controller);
    }  

    @Override
    public void execute(Area area) {
        int color = controller.getColors().getCurrentColor();
        BufferedImage img = controller.getImg().getImg();
        Graphics2D graphics = img.createGraphics();

        graphics.setPaint(new Color(color, true));
        
        if (prevX == -1) {        
            prevX = area.x;
            prevY = area.y;
            graphics.fillRect(area.x, area.y, area.width, area.height);
        } else {
            for (int i = 0; i < area.width; i++) {
                graphics.drawLine(prevX+i, prevY, area.x+i, area.y);  
                graphics.drawLine(prevX, prevY+i, area.x, area.y+i); 
            }
            prevX = area.x;
            prevY = area.y;
        }
    }
}
