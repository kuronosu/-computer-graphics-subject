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
public class Polygon extends BasePolygon {

    public Polygon(Point[] vertices) {
        super(vertices);
    }

    public Polygon(int[] vertices, int sides) {
        super(new Point[0]);
        valid = sides >= 3 && vertices.length >= sides * 2;
        if (valid) {
            this.vertices = new Point[sides];
            for (int i = 0; i < sides * 2; i += 2) {
                this.vertices[i / 2] = new Point(vertices[i], vertices[i + 1]);
            }
        }
    }
}
