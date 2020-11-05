/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

/**
 *
 * @author kuro
 */
public class Point implements Drawable {

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
}
