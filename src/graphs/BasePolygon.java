/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kuro
 */
public abstract class BasePolygon implements Drawable {

    protected Point[] vertices;
    protected boolean valid;
    protected Line[] edges;
    protected int Xmax = Integer.MIN_VALUE, Xmin = Integer.MAX_VALUE,
            Ymax = Integer.MIN_VALUE, Ymin = Integer.MAX_VALUE;

    public BasePolygon(Point[] vertices) {
        valid = vertices.length >= 3;
        if (valid) {
            this.vertices = vertices;
            calculateBounds();
            calculateEdges();
        }
    }

    @Override
    public boolean draw(Graph g) {
        if (!isValid()) {
            return isValid();
        }
        if (edges == null){
            calculateEdges();
        }
        applyPattern(g);
        for (Line edge : edges) {
            g.draw(edge);
        }
        return isValid();
    }

    public final void calculateBounds() {
        for (Point vertice : vertices) {
            if (vertice.getX() < Xmin) {
                Xmin = vertice.getX();
            }
            if (vertice.getX() > Xmax) {
                Xmax = vertice.getX();
            }
            if (vertice.getY() < Ymin) {
                Ymin = vertice.getY();
            }
            if (vertice.getY() > Ymax) {
                Ymax = vertice.getY();
            }
        }
    }

    protected final void calculateEdges() {
        edges = new Line[vertices.length];
        for (int i = 0; i < getVertices().length - 1; i++) {
            edges[i] = new Line(getVertices()[i], getVertices()[i + 1]);
        }
        edges[edges.length - 1] = new Line(getVertices()[getVertices().length - 1], getVertices()[0]);
    }

    public ArrayList<Point> lineCrossings(Point p) {
        return lineCrossings(new Line(new Point(Xmin - 1, p.getY()), p));
    }

    public ArrayList<Point> lineCrossings(int x, int y) {
        return lineCrossings(new Point(x, y));
    }

    public ArrayList<Point> lineCrossings(Line rayLine) {
        ArrayList<Point> points = new ArrayList<>();
        for (Line edge : edges) {
            Point ip = rayLine.calculateIntersection(edge);
            if (ip != null) {
                points.add(ip);
            }
        }
        return points;
    }

    public void paint(Graph g, int i, ValidateDrawPoint v, int multiplier) {
        paint(g, i, (y -> {
            return lineCrossings(Xmax, y);
        }), (Point p1, Point p2, int y) -> {
            for (int x = p1.getX(); x <= p2.getX(); x++) {
                if (v.validate(x, y)) {
                    g.draw(new Point(x, y));
                }
            }
        }, multiplier);
    }

    public void paint(Graph g, int i, CalculateIPoints cip, int multiplier) {
        paint(g, i, cip, (Point p1, Point p2, int y) -> {
            g.line(p1, p2);
        }, multiplier);
    }

    public void paint(Graph g, int i, CalculateIPoints cip, DrawLine d, int multiplier) {
        Color polygonColor = g.getColor();
        Color fillColor = g.getStyle().getColor();
        g.setColor(fillColor);
        for (int y = Ymin + 1; y < Ymax * multiplier; y += i) {
            ArrayList<Point> points = cip.calc(y);
            Collections.sort(points, new Point.CompareByAscendingX());
            List<Point> pointsWithOutDuplicates = Utils.removeDuplicates(points);
            boolean ap = true;
            Point p1 = null;
            for (Point point : pointsWithOutDuplicates) {
                if (ap) {
                    p1 = point;
                } else {
                    d.draw(p1, point, y);
                }
                ap = !ap;
            }
        }
        g.setColor(polygonColor);
    }

    public void applyPattern(Graph g) {
        switch (g.getStyle().getType()) {
            case 1:
                paint(g, 1, (x, y) -> {
                    return true;
                }, 1);
                break;
            case 2:
                paint(g, 10, (x, y) -> {
                    return true;
                }, 1);
                break;
            case 3:
                paint(g, 1, (x, y) -> {
                    return x % 10 == 0;
                }, 1);
                break;
            case 4:
                paint(g, 10, (y -> {
                    return lineCrossings(new Line(Xmin, y - (Ymax - Ymin), Xmax, y));
                }), 2);
                break;
            case 5:
                paint(g, 10, (y -> {
                    return lineCrossings(new Line(Xmax, y - (Ymax - Ymin), Xmin, y));
                }), 2);
                break;
            case 6:
                paint(g, 1, (x, y) -> {
                    return x % 10 == 0 || y % 10 == 0;
                }, 1);
                break;
            case 7:
                paint(g, 10, (y -> {
                    return lineCrossings(new Line(Xmin, y - (Ymax - Ymin), Xmax, y));
                }), 2);
                paint(g, 10, (y -> {
                    return lineCrossings(new Line(Xmax, y - (Ymax - Ymin), Xmin, y));
                }), 2);
                break;
        }
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
        if (edges == null) calculateEdges();
        return valid && vertices.length == edges.length;
    }

    public interface ValidateDrawPoint {

        boolean validate(int x, int y);
    }

    public interface DrawLine {

        void draw(Point p1, Point p2, int y);
    }

    public interface CalculateIPoints {

        ArrayList<Point> calc(int y);
    }
}
