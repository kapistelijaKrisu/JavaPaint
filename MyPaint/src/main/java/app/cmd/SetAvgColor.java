package app.cmd;

import app.MyImage;
import app.PaintBrush;
import java.awt.Color;
import tools.Area;

public class SetAvgColor implements CMD {
        private PaintBrush brush;
    public SetAvgColor(PaintBrush brush) {
        if (brush == null) throw new IllegalArgumentException();
        this.brush = brush;
    }

    @Override
    public void execute(MyImage img, Area area) {
        int[] rgbs = img.getImg().getRGB(area.getStartX(), area.getStartY(), area.getCurX(), area.getCurY(), null, 0, area.getCurX());
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        for (int i = 0; i < rgbs.length; i++) {
            int val = rgbs[i];
            a += (0xff000000 & val) >>> 24;
            r += (0x00ff0000 & val) >> 16;
            g += (0x0000ff00 & val) >> 8;
            b += (0x000000ff & val);
        }
        a /= rgbs.length;
        r /= rgbs.length;
        g /= rgbs.length;
        b /= rgbs.length;
        brush.setCurrentColor(new Color(r, g, b, a));
        brush.installSetting(img.getGraphics(), true, false, false);
    }

}
