package app.cmd;

import app.MyImage;
import app.PaintBrush;
import java.awt.Color;
import tools.Area;

public class SetAvgColor implements CMD {

    private PaintBrush brush;

    public SetAvgColor(PaintBrush brush) {
        if (brush == null) {
            throw new IllegalArgumentException();
        }
        this.brush = brush;
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

        // System.out.println(a + " " + r + " " + g + " " + a);
        brush.setColor(new Color(r, g, b, a));
        brush.installSetting(img.getGraphics(), true, false, false);

    }

}
