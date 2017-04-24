package app;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayDeque;
import ui.OneLineException;

/**
 * 
 * Holds the history and redo steps in 2stacks and manages them
 */

public class Log {

    private final ArrayDeque<BufferedImage> history;
    private final ArrayDeque<BufferedImage> redo;

    public Log() {
        history = new ArrayDeque<>();
        redo = new ArrayDeque<>();
    }

    /**
     * 
     * @param current puts the current image to redo stack.
     * throws exception if null.
     * @return return popped image from history stack.
     */
    public BufferedImage popPrevious(BufferedImage current) {
        OneLineException.throwIfIsNull(current);
        if (!history.isEmpty()) {
            BufferedImage prev = history.pop();
            redo.addFirst(current);
            return prev;
        }
        return null;
    }

    /**
     * 
     * @param current puts the current image to history stack.
     * throws exception if null.
     * @return returns popped image from redo stack.
     */
    public BufferedImage popNext(BufferedImage current) {
        OneLineException.throwIfIsNull(current);
        if (!redo.isEmpty()) {
            BufferedImage next = redo.pop();
            history.addFirst(current);
            return next;
        }
        return null;

    }

    /**
     * 
     * @param img image is added to history stack.
     */
    public void archieveImage(BufferedImage img) {
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
