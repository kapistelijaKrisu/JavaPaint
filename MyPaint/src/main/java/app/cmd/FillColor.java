package app.cmd;

import tools.Coordinate;
import app.MyImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import tools.TwoPoint;

/**
 *
 * An implementation of CMD which replaces a color using breadth search on call.
 *
 */
public class FillColor implements CMD {

    /**
     *
     * Fills an area using breadth search starting from current values of point to img.
     * @param img Target to be drawn on.
     * @param point information on where to draw.
     */
    @Override
    public void execute(MyImage img, TwoPoint point) {

        Graphics2D g = img.getGraphics();
        int paintOn = img.getImg().getRGB(point.getCurX(), point.getCurY());
        int paintColor = g.getColor().getRGB();

        HashSet<Coordinate> visited = new HashSet<>();
        ArrayDeque<Coordinate> que = new ArrayDeque<>();
        que.add(new Coordinate(point.getCurX(), point.getCurY()));
        visited.add(new Coordinate(point.getCurX(), point.getCurY()));

        while (!que.isEmpty()) {

            Coordinate at = que.poll();
            img.getImg().setRGB(at.x, at.y, paintColor);

            que.addAll(getNeighbours(at, paintOn, visited, img.getImg()));

        }

    }

    private Collection getNeighbours(Coordinate at, int paintOn, HashSet visited, BufferedImage img) {
        Collection<Coordinate> toReturn = new ArrayList();

        ArrayDeque<Coordinate> allNeighbours = new ArrayDeque<>();
        allNeighbours.add(new Coordinate(at.x, at.y + 1));
        allNeighbours.add(new Coordinate(at.x, at.y - 1));
        allNeighbours.add(new Coordinate(at.x + 1, at.y));
        allNeighbours.add(new Coordinate(at.x - 1, at.y));

        while (!allNeighbours.isEmpty()) {
            Coordinate c = allNeighbours.poll();
            if (canAdd(c, visited, paintOn, img)) {
                toReturn.add(c);
                visited.add(c);
            }
        }
        return toReturn;
    }

    private boolean isInBounds(Coordinate c, int maxX, int maxY) {
        return c.x >= 0 && c.x < maxX && c.y >= 0 && c.y < maxY;
    }

    private boolean canAdd(Coordinate coord, HashSet visited, int paintOn, BufferedImage img) {

        if (isInBounds(coord, img.getWidth(), img.getHeight())) {
            if (!visited.contains(coord) && img.getRGB(coord.x, coord.y) == paintOn) {
                return true;
            }
        }
        return false;
    }

}
