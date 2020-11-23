/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.ArrayList;

/**
 *
 * @author kuro
 */
public class Line implements Drawable, Cloneable {

    private Point p1, p2;
    private LineMethod method;
    private ArrayList<Point> calculatedPoints;

    public Line(Point p1, Point p2, LineMethod method) {
        this.p1 = p1;
        this.p2 = p2;
        this.method = method;
    }

    public Line(Point p1, Point p2) {
        this(p1, p2, LineMethod.KURO);
    }

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Line(int x1, int y1, int x2, int y2, LineMethod method) {
        this(new Point(x1, y1), new Point(x2, y2), method);
    }

    private ArrayList<Point> virtual() {
        switch (getMethod()) {
            case DDA:
                return dda();
            case BRESENHAM:
                return bresenham();
            case KURO:
                return kuro();
            default:
                return kuro();
        }
    }

    @Override
    public boolean draw(Graph g) {
        calculatedPoints = virtual();
        for (Point p : calculatedPoints) {
            g.pixel(p);
        }
        return true;
    }

    private ArrayList<Point> kuro() {
        int x1 = getP1().getX(), y1 = getP1().getY(), x2 = getP2().getX(), y2 = getP2().getY();
        double m = (double) (y2 - y1) / (double) (x2 - x1);
        int start, end;
        boolean f;
        if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
            start = y1;
            end = y2;
            f = false;
        } else {
            start = x1;
            end = x2;
            f = true;
        }
        if (start > end) {
            int tmp = end;
            end = start;
            start = tmp;
        }
        ArrayList<Point> al = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (f) {
                al.add(new Point(i, (int) (m * (i - x1) + y1)));
            } else {
                al.add(new Point((int) ((x1 + ((i - y1) / m))), i));
            }
        }
        return al;
    }

    private ArrayList<Point> dda() {
        int x1 = getP1().getX(), y1 = getP1().getY(), x2 = getP2().getX(), y2 = getP2().getY();
        float ax, ay, x = x1, y = y1, pasos;
        int deltaX = x2 - x1;
        int deltaY = y2 - y1, i = 1;
        pasos = Math.abs(deltaX) > Math.abs(deltaY) ? Math.abs(deltaX) : Math.abs(deltaY);
        ax = deltaX / pasos;
        ay = deltaY / pasos;
        ArrayList<Point> al = new ArrayList<>();
        while (i++ <= pasos) {
            al.add(new Point((int) Math.floor(x), (int) Math.floor(y)));
            x += ax;
            y += ay;
        }
        return al;
    }

    private ArrayList<Point> bresenham() {
        int x1 = getP1().getX(), y1 = getP1().getY(), x2 = getP2().getX(), y2 = getP2().getY();
        float e, ax, ay, temp;
        int s1, s2, intercambio, i, x = x1, y = y1;

        ax = Math.abs(x2 - x1);
        ay = Math.abs(y2 - y1);
        s1 = x2 - x1 < 0 ? -1 : x2 - x1 > 0 ? 1 : 0;
        s2 = y2 - y1 < 0 ? -1 : y2 - y1 > 0 ? 1 : 0;
        if (ay > ax) {
            temp = ax;
            ax = ay;
            ay = temp;
            intercambio = 1;
        } else {
            intercambio = 0;
        }
        e = 2 * ay - ax;
        ArrayList<Point> al = new ArrayList<>();
        for (i = 1; i <= ax; i++) {
            al.add(new Point(x, y));
            if (e >= 0) {
                if (intercambio == 1) {
                    x = x + s1;
                } else {
                    y = y + s2;
                }
                e = e - (2 * ax);
            }
            if (intercambio == 1) {
                y = y + s2;
            } else {
                x = x + s1;
            }
            e = e + 2 * ay;
        }
        return al;
    }

    public ArrayList<Point> getCalculatedPoints() {
        if (calculatedPoints == null) {
            calculatedPoints = virtual();
        }
        return calculatedPoints;
    }

    public Point getMinInX() {
        if (getP1().getX() < getP2().getX()) {
            return getP1();
        }
        return getP2();
    }

    public Point getMaxInX() {
        if (getP1().getX() > getP2().getX()) {
            return getP1();
        }
        return getP2();
    }

    public Point getMinInY() {
        if (getP1().getY() < getP2().getY()) {
            return getP1();
        }
        return getP2();
    }

    public Point getMaxInY() {
        if (getP1().getY() > getP2().getY()) {
            return getP1();
        }
        return getP2();
    }

    /**
     * @return the horizontal
     */
    public boolean isHorizontal() {
        return p1.getY() == p2.getY();
    }

    public boolean isVertical() {
        return p1.getX() == p2.getX();
    }

    @Override
    protected Line clone() {
        Line newLine = new Line(getP1().clone(), getP2().clone());
        newLine.method = getMethod();
        newLine.calculatedPoints = new ArrayList<>(calculatedPoints);;
        return newLine;
    }

    public double calculateM() {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return dy / dx;
    }

    private boolean verifyResult(double x, double y) {
        return x >= getMinInX().getX() && x <= getMaxInX().getX() && y >= getMinInY().getY() && y <= getMaxInY().getY();
    }

    private Point calculateIntersectionInVertical(Line l2) {
        for (Point p1 : getCalculatedPoints()) {
            for (Point p2 : l2.getCalculatedPoints()) {
                if (p1.equals(p2)) {
                    return p1;
                }
            }
        }
        return null;
    }

    public Point calculateIntersection(Line l2) {
        if (isVertical() || l2.isVertical()) {
            Point p = calculateIntersectionInVertical(l2);
            return p != null ? p : l2.calculateIntersectionInVertical(this);
        }
        double a1 = calculateM(), b1 = -1;
        double c1 = a1 * (double) p1.getX() - (double) p1.getY();
        double a2 = l2.calculateM(), b2 = -1;
        double c2 = a2 * (double) l2.p1.getX() - (double) l2.p1.getY();
        double numerador_y = a2 * c1 - a1 * c2;
        double denominador_y = a2 * b1 - a1 * b2;
        boolean paralelas = a1 * b2 == a2 * b1;
        if (paralelas) {
            return null;
        }
        double x, y;
        if (a1 == 0) {
            y = c1 / b1;
            x = (c2 - b2 * y) / a2;
        } else {
            y = numerador_y / denominador_y;
            x = (c1 - b1 * y) / a1;
        }
        if (verifyResult(x, y) && l2.verifyResult(x, y)) {
            return new Point((int) Math.round(x), (int) Math.round(y));
        }
        return null;
    }

    /**
     * @return the p1
     */
    public Point getP1() {
        return p1;
    }

    /**
     * @return the p2
     */
    public Point getP2() {
        return p2;
    }

    /**
     * @return the method
     */
    public LineMethod getMethod() {
        return method;
    }

}
