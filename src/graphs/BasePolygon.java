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
public abstract class BasePolygon implements Drawable {

    protected Point[] vertices;
    protected boolean valid;

    public BasePolygon(Point[] vertices) {
        valid = vertices.length >= 3;
        if (valid) {
            this.vertices = vertices;
        }
    }

    @Override
    public boolean draw(Graph g) {
        if (!isValid()) {
            return isValid();
        }
        for (int i = 0; i < getVertices().length - 1; i++) {
            g.line(getVertices()[i], getVertices()[i + 1]);
        }
        g.line(getVertices()[getVertices().length - 1], getVertices()[0]);
        return isValid();
    }

    /**
     * @return the vertices
     */
    public Point[] getVertices() {
        return vertices;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }
}
