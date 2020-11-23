/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import static graphs.Utils.rotatePoint;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kuro
 */
public class Circle implements Drawable {

    private final Point center;
    private final int radius;
    private final ArrayList<Point> points = new ArrayList<>();
    private final HashMap<Integer, Bounds> pointsByX = new HashMap<>();
    private final HashMap<Integer, Bounds> pointsByY = new HashMap<>();

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public void applyPattern(Graph g) {
        fillHashMaps();
        Color cc = g.getColor();
        g.setColor(g.getStyle().getColor());
        switch (g.getStyle().getType()) {
            case 1:
                for (Map.Entry<Integer, Bounds> entry : pointsByY.entrySet()) {
                    Integer y = entry.getKey();
                    Bounds b = entry.getValue();
                    for (int x = b.min.getX(); x < b.max.getX(); x++) {
                        g.pixel(x, y);
                    }
                }
                break;
            case 2:
                for (Map.Entry<Integer, Bounds> entry : pointsByY.entrySet()) {
                    Integer y = entry.getKey();
                    Bounds b = entry.getValue();
                    if (y % 10 == 0) {
                        for (int x = b.min.getX(); x < b.max.getX(); x++) {
                            g.pixel(x, y);
                        }
                    }
                }
                break;
            case 3:
                for (Map.Entry<Integer, Bounds> entry : pointsByX.entrySet()) {
                    Integer x = entry.getKey();
                    Bounds b = entry.getValue();
                    if (x % 10 == 0) {
                        for (int y = b.min.getY(); y < b.max.getY(); y++) {
                            g.pixel(x, y);
                        }
                    }
                }
                break;
            case 4:
                for (Map.Entry<Integer, Bounds> entry : pointsByY.entrySet()) {
                    Integer y = entry.getKey();
                    Bounds b = entry.getValue();
                    if (y % 10 == 0) {
                        g.line(rotatePoint(b.min, center, 45), rotatePoint(b.max, center, 45));
                    }
                }
                break;
            case 5:
                for (Map.Entry<Integer, Bounds> entry : pointsByY.entrySet()) {
                    Integer y = entry.getKey();
                    Bounds b = entry.getValue();
                    if (y % 10 == 0) {
                        g.line(rotatePoint(b.min, center, -45), rotatePoint(b.max, center, -45));
                    }
                }
                break;
            case 6:
                for (Map.Entry<Integer, Bounds> entry : pointsByY.entrySet()) {
                    Integer y = entry.getKey();
                    Bounds b = entry.getValue();
                    for (int x = b.min.getX(); x < b.max.getX(); x++) {
                        if (y % 10 == 0 || x % 10 == 0) {
                            g.pixel(x, y);
                        }
                    }
                }
                break;
            case 7:
                for (Map.Entry<Integer, Bounds> entry : pointsByY.entrySet()) {
                    Integer y = entry.getKey();
                    Bounds b = entry.getValue();
                    if (y % 10 == 0 && y != center.getY() - radius && y != center.getY() + radius) {
                        g.line(rotatePoint(b.min, center, 45), rotatePoint(b.max, center, 45));
                        g.line(rotatePoint(b.min, center, -45), rotatePoint(b.max, center, -45));
                    }
                }
                break;
        }
        g.setColor(cc);
    }

    private void fillHashMaps() {
        if (!pointsByY.isEmpty()) {
            return;
        }
        HashMap<Integer, ArrayList<Point>> tmpPointsByX = new HashMap<>();
        HashMap<Integer, ArrayList<Point>> tmpPointsByY = new HashMap<>();
        for (Point point : getPoints()) {
            if (!tmpPointsByX.containsKey(point.getX())) {
                tmpPointsByX.put(point.getX(), new ArrayList<>());
            }
            if (!tmpPointsByY.containsKey(point.getY())) {
                tmpPointsByY.put(point.getY(), new ArrayList<>());
            }
            tmpPointsByX.get(point.getX()).add(point);
            tmpPointsByY.get(point.getY()).add(point);
        }
        tmpPointsByX.entrySet().forEach(entry -> {
            int key = entry.getKey();
            ArrayList<Point> val = entry.getValue();
            pointsByX.put(key, getMixMaxByY(val));
        });
        tmpPointsByY.entrySet().forEach(entry -> {
            int key = entry.getKey();
            ArrayList<Point> val = entry.getValue();
            pointsByY.put(key, getMixMaxByX(val));
        });
    }

    private Bounds getMixMaxByX(ArrayList<Point> l) {
        Point max = null, min = null;
        for (Point point : l) {
            if (max == null) {
                max = point;
            }
            if (min == null) {
                min = point;
            }
            if (point.getX() > max.getX()) {
                max = point;
            }
            if (point.getX() < min.getX()) {
                min = point;
            }
        }
        return new Bounds(min, max);
    }

    private Bounds getMixMaxByY(ArrayList<Point> l) {
        Point max = null, min = null;
        for (Point point : l) {
            if (max == null) {
                max = point;
            }
            if (min == null) {
                min = point;
            }
            if (point.getY() > max.getY()) {
                max = point;
            }
            if (point.getY() < min.getY()) {
                min = point;
            }
        }
        return new Bounds(min, max);
    }

    public void calculatePoints() {
        int x = 0, y = radius;
        int d = 3 - 2 * radius;
        addPoint(center.getX(), center.getY(), x, y);
        while (y >= x) {
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            addPoint(center.getX(), center.getY(), x, y);
        }
    }

    @Override
    public boolean draw(Graph g) {
        applyPattern(g);
        if (getPoints().isEmpty()) {
            int x = 0, y = radius;
            int d = 3 - 2 * radius;
            drawCircle(g, center.getX(), center.getY(), x, y);
            addPoint(center.getX(), center.getY(), x, y);
            while (y >= x) {
                x++;
                if (d > 0) {
                    y--;
                    d = d + 4 * (x - y) + 10;
                } else {
                    d = d + 4 * x + 6;
                }
                drawCircle(g, center.getX(), center.getY(), x, y);
                addPoint(center.getX(), center.getY(), x, y);
            }
        } else {
            getPoints().forEach(point -> {
                g.pixel(point);
            });
        }
        return true;
    }

    private void drawCircle(Graph g, int xc, int yc, int x, int y) {
        g.pixel(xc + x, yc + y);
        g.pixel(xc - x, yc + y);
        g.pixel(xc + x, yc - y);
        g.pixel(xc - x, yc - y);
        g.pixel(xc + y, yc + x);
        g.pixel(xc - y, yc + x);
        g.pixel(xc + y, yc - x);
        g.pixel(xc - y, yc - x);
    }

    private void addPoint(int xc, int yc, int x, int y) {
        points.add(new Point(xc + x, yc + y));
        points.add(new Point(xc - x, yc + y));
        points.add(new Point(xc + x, yc - y));
        points.add(new Point(xc - x, yc - y));
        points.add(new Point(xc + y, yc + x));
        points.add(new Point(xc - y, yc + x));
        points.add(new Point(xc + y, yc - x));
        points.add(new Point(xc - y, yc - x));
    }

    /**
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return the points
     */
    public ArrayList<Point> getPoints() {
        if (points.isEmpty()) {
            calculatePoints();
        }
        return points;
    }

    class Bounds {

        Point max, min;

        public Bounds(Point min, Point max) {
            this.max = max;
            this.min = min;
        }
    }
}
