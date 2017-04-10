package app.cmd;

import app.MyImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import tools.Area;
import ui.Coordinate;

/**
 * 
 * <p>An implementation of CMD which replaces a color of MyImage using breadth search.</p>
 * 
 */

public class FillColor implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
        if (area == null || img == null) {
            throw new IllegalArgumentException();
        }

        Graphics2D g = img.getGraphics();
        int paintOn = img.getImg().getRGB(area.getCurX(), area.getCurY());
        int paintColor = g.getColor().getRGB();

        HashSet<Coordinate> visited = new HashSet<>();
        ArrayDeque<Coordinate> que = new ArrayDeque<>();
        que.add(new Coordinate(area.getCurX(), area.getCurY()));
        visited.add(new Coordinate(area.getCurX(), area.getCurY()));

        while (!que.isEmpty()) {

            Coordinate at = que.poll();
            img.getImg().setRGB(at.x, at.y, paintColor);

            que.addAll(getNeighbours(at, paintOn, visited, img.getImg()));

        }

    }

    private Collection getNeighbours(Coordinate at, int paintOn, HashSet visited, BufferedImage img) {
        Collection<Coordinate> c = new ArrayList();
        Collection<Coordinate> toReturn = new ArrayList();

        Coordinate coord;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                    coord = new Coordinate(at.x + j, at.y + i);
                    if (isLegal(coord, img.getWidth(), img.getHeight())) {
                        c.add(coord);
                    }

                }

            }

        for (Coordinate coo : c) {
            if (!visited.contains(coo)) {

                if (img.getRGB(coo.x, coo.y) == paintOn) {
                    toReturn.add(coo);

                }
            }
            visited.add(coo);
        }
        return toReturn;
    }

    private boolean isLegal(Coordinate c, int maxX, int maxY) {
        return c.x >= 0 && c.x < maxX && c.y >= 0 && c.y < maxY;
    }

}
