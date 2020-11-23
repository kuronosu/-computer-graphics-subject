/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.Comparator;

/**
 *
 * @author kuro
 */
public class Point implements Drawable, Cloneable {

    private int x, y;
    private boolean safe = true;

    public Point(int x, int y, boolean safe) {
        this.x = x;
        this.y = y;
        this.safe = safe;
    }

    public Point(int x, int y) {
        this(x, y, true);
    }

    @Override
    public boolean draw(Graph g) {
        if (isSafe()) {
            try {
                g.getCtx().setRGB(x, y, g.getColor().getRGB());
            } catch (Exception e) {
                return false;
            }
        } else {
            g.getCtx().setRGB(x, y, g.getColor().getRGB());
        }
        return true;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point p2 = (Point) obj;
            return this.x == p2.x && this.y == p2.y;
        }
        return false;
    }

    @Override
    protected Point clone() {
        return new Point(x, y);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the safe
     */
    public boolean isSafe() {
        return safe;
    }

    /**
     * @param safe the safe to set
     */
    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public static class CompareByAscendingX implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return o1.x - o2.x;
        }
    }
}
