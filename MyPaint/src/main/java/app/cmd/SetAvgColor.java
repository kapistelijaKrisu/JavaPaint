package app.cmd;

import app.MyImage;
import java.awt.Color;
import tools.Area;

public class SetAvgColor implements CMD {

    private MyImage img;

    public SetAvgColor(MyImage img) {
        if (img == null) {
            throw new IllegalArgumentException();
        }
        this.img = img;
    }

    @Override
    public void execute(MyImage img, Area rect) {
        if (rect == null || img == null) {
            throw new IllegalArgumentException();
        }
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;

        for (int y = 0; y < rect.getCurY() + 1; y++) {
            for (int x = 0; x < rect.getCurX() + 1; x++) {

                int val = img.getImg().getRGB(x + rect.getStartX(), y + rect.getStartY());
                a += (0xff000000 & val) >>> 24;
                r += (0x00ff0000 & val) >> 16;
                g += (0x0000ff00 & val) >> 8;
                b += (0x000000ff & val);
            }

        }

        int divider = (rect.getCurX() + 1) * (rect.getCurY() + 1);
        a /= divider;
        r /= divider;
        g /= divider;
        b /= divider;
        
        img.setColor(new Color(r, g, b, a));

    }

}
