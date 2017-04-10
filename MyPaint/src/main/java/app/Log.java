package app;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayDeque;
import ui.OneLineException;

/**
 * 
 * Holds the copied images in 2 stacks for undo and redo easy managment
 */

public class Log {

    private final ArrayDeque<BufferedImage> history;
    private final ArrayDeque<BufferedImage> redo;

    public Log() {
        history = new ArrayDeque<>();
        redo = new ArrayDeque<>();
    }

    public BufferedImage getPrevious(BufferedImage current) {
        OneLineException.nullTest(current);
        if (!history.isEmpty()) {
            BufferedImage prev = history.pop();
            redo.addFirst(current);
            return prev;
        }
        return null;
    }

    public BufferedImage getNext(BufferedImage current) {
        OneLineException.nullTest(current);
        if (!redo.isEmpty()) {
            BufferedImage next = redo.pop();
            history.addFirst(current);
            return next;
        }
        return null;

    }

    public void addStep(BufferedImage img) {
        history.push(clone(img));
        redo.clear();
    }

    private static BufferedImage clone(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
