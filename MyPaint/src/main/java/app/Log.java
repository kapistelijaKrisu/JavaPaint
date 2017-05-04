package app;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayDeque;

/**
 *
 * Holds the history and redo steps in two stacks and manages them.
 */
public class Log {

    private final ArrayDeque<BufferedImage> history;
    private final ArrayDeque<BufferedImage> redo;
    private int logMaxSize = 300;

    public Log() {
        history = new ArrayDeque<>();
        redo = new ArrayDeque<>();
    }

    /**
     *
     * @param current puts the current image to redo stack. Throws exception if
     * null.
     * @return return popped image from history stack.
     */
    public BufferedImage popPrevious(BufferedImage current) {
        if (current == null) {
            throw new NullPointerException();
        }
        if (!history.isEmpty()) {
            BufferedImage prev = history.pop();
            redo.addFirst(current);
            return prev;
        }
        return null;
    }

    /**
     *
     * @param current puts the current image to history stack. Throws exception
     * if null.
     * @return returns popped image from redo stack.
     */
    public BufferedImage popNext(BufferedImage current) {
        if (current == null) {
            throw new NullPointerException();
        }
        if (!redo.isEmpty()) {
            BufferedImage next = redo.pop();
            history.addFirst(current);
            return next;
        }
        return null;

    }

    /**
     *
     * @param img clone of this image is added to history stack.
     */
    public void archieveImage(BufferedImage img) {
        history.push(clone(img));
        redo.clear();

        if (history.size() > logMaxSize) {
            history.pop();
        }
    }

    private static BufferedImage clone(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void setLogMaxSize(int logMaxSize) {
        this.logMaxSize = logMaxSize;
    }

    public int getHistorySize() {
        return history.size();
    }

    public int getRedoSize() {
        return redo.size();
    }
}
