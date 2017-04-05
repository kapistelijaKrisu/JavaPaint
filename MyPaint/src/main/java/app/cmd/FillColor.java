package app.cmd;

import app.MyImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import tools.Area;

public class FillColor implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
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
        int x = at.x;
        int y = at.y;

        x = at.x + 1;
        Coordinate coord = new Coordinate(x, y);
    
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        y = at.y + 1;
        coord = new Coordinate(x, y);
     
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        y = at.y - 1;
        coord = new Coordinate(x, y);
    
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        x = at.x;
        coord = new Coordinate(x, y);
 
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        y = at.y + 1;
        coord = new Coordinate(x, y);
    
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        x = at.x - 1;
        coord = new Coordinate(x, y);

        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        y = at.y;
        coord = new Coordinate(x, y);
   
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        y = at.y + 1;
        coord = new Coordinate(x, y);
    
        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
        }

        y = at.y - 1;
        coord = new Coordinate(x, y);

        if (isLegal(x, y, img.getWidth(), img.getHeight())) {
            c.add(coord);
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

    private boolean isLegal(int x, int y, int maxX, int maxY) {
        System.out.println("?" + x + " " + y);
        return x >= 0 && x < maxX && y >= 0 && y < maxY;
    }

    private class Coordinate {

        public final int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Coordinate other = (Coordinate) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + this.x;
            hash = 29 * hash + this.y;
            return hash;
        }

        @Override
        public String toString() {
            return "x:" + x + " y:" + y;
        }

    }

}
