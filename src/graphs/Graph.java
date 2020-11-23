/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author kuro
 */
public class Graph {

    private BufferedImage ctx;
    private Color color;
    private Style style;

    public Graph(int w, int h, Color color) {
        ctx = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        this.color = color;
        style = new Style(0, color);
    }

    public Graph(int w, int h) {
        this(w, h, Color.BLACK);
    }

    public Color getPixel(int x, int y) {
        int clr = getCtx().getRGB(x, y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        return new Color(red, green, blue);
    }

    public Color getPixel(Point p) {
        return getPixel((int) p.getX(), (int) p.getY());
    }

    public boolean draw(Drawable o) {
        return o.draw(this);
    }

    public boolean pixel(Point p) {
        return p.draw(this);
    }

    public boolean pixel(int x, int y) {
        return pixel(new Point(x, y));
    }

    public boolean line(Line l) {
        return l.draw(this);
    }

    public boolean line(Point p1, Point p2) {
        return line(new Line(p1, p2));
    }

    public boolean line(int x1, int y1, int x2, int y2) {
        return line(new Line(x1, y1, x2, y2));
    }

    // rectangle by 2 points
    public boolean rectangle(Point p1, Point p2) {
        Quadrilateral q = new Quadrilateral(p1, p2);
        return q.draw(this);
    }

    public boolean rectangle(int x1, int y1, int x2, int y2) {
        return rectangle(new Point(x1, y1), new Point(x2, y2));
    }

    // rectangle by point
    public boolean rectangle(Point p1, int width, int height) {
        Quadrilateral q = new Quadrilateral(width, height, p1);
        return q.draw(this);
    }

    public boolean rectangle(Point p1, int width, int height, QuadrilateralAnchor anchor) {
        Quadrilateral q = new Quadrilateral(width, height, p1, anchor);
        return q.draw(this);
    }

    public boolean rectangle(int x1, int y1, int width, int height, QuadrilateralAnchor anchor) {
        return rectangle(new Point(x1, y1), width, height, anchor);
    }

    // square
    public boolean square(Point p1, int side) {
        Quadrilateral q = new Quadrilateral(side, side, p1);
        return q.draw(this);
    }

    public boolean square(int x1, int y1, int side) {
        return square(new Point(x1, y1), side);
    }

    public boolean square(Point p1, int side, QuadrilateralAnchor anchor) {
        Quadrilateral q = new Quadrilateral(side, side, p1, anchor);
        return q.draw(this);
    }

    public boolean square(int x1, int y1, int side, QuadrilateralAnchor anchor) {
        return square(new Point(x1, y1), side, anchor);
    }

    // trectangulo
    public boolean rightTriangle(Point p, int side1, int side2) {
        Polygon pol = new Polygon(new Point[]{
            p, new Point(p.getX() + side1, p.getY()),
            new Point(p.getX(), p.getY() - side2)});
        return pol.draw(this);
    }

    public boolean rightTriangle(int x1, int y1, int side1, int side2) {
        return rightTriangle(new Point(x1, y1), side1, side2);
    }

    // tequilatero
    public boolean equilateralTriangle(Point p, int side) {
        int h = (int) ((Math.sqrt(3) * side) / 2);
        Polygon pol = new Polygon(new Point[]{
            p, new Point(p.getX() + side, p.getY()),
            new Point(p.getX() + side / 2, p.getY() - h)});
        return pol.draw(this);
    }

    public boolean equilateralTriangle(int x1, int y1, int side) {
        return equilateralTriangle(new Point(x1, y1), side);
    }

    // pentagon
    public boolean pentagon(Point p, int side) {
        int x = (int) Math.abs(Math.sin(72) * side);
        int y = (int) Math.abs(Math.cos(72) * side);
        int h = (int) (side / (2 * Math.tan(Math.toRadians(36)))) * 2;

        Polygon pol = new Polygon(new Point[]{
            p, new Point(p.getX() + side, p.getY()),
            new Point(p.getX() + side + x, p.getY() - y),
            new Point(p.getX() + side / 2, p.getY() - h),
            new Point(p.getX() - x, p.getY() - y),});
        return pol.draw(this);
    }

    public boolean pentagon(int x1, int y1, int side) {
        return pentagon(new Point(x1, y1), side);
    }

    // polygon
    public boolean polygon(int[] vertices, int sides) {
        Polygon pol = new Polygon(vertices, sides);
        return pol.draw(this);
    }

    public boolean polygon(Point[] vertices) {
        Polygon pol = new Polygon(vertices);
        return pol.draw(this);
    }

    // circle
    public void circle(Point p, int r) {
        Circle c = new Circle(p, r);
        c.draw(this);

    }

    public void circle(int cx, int cy, int r) {
        circle(new Point(cx, cy), r);
    }

    // N agono
    public boolean nAgono(Point c, int n, int l) {
        if (n < 3 || n > 11) {
            return false;
        }
        ArrayList<Point> list = new ArrayList<>();
        int r = (int) (l / (2 * Math.sin(Math.toRadians((360 / n) / 2))));
        for (int angulo = 0; angulo < 360; angulo += (360 / n)) {
            list.add(new Point(
                    (int) (c.getX() + r * Math.cos(Math.toRadians(angulo))),
                    (int) (c.getY() + r * Math.sin(Math.toRadians(angulo)))
            ));
        }
        Polygon pol = new Polygon(list.toArray(new Point[list.size()]));
        pol.draw(this);
        return true;
    }

    public boolean nAgono(int x, int y, int n, int l) {
        return nAgono(new Point(x, y), n, l);
    }

    public Polygon nAgonop(Point c, int n, int l) {
        if (n < 3 || n > 11) {
            return null;
        }
        ArrayList<Point> list = new ArrayList<>();
        for (int angulo = 0; angulo < 360; angulo += (360 / n)) {
            list.add(new Point(
                    (int) (c.getX() + l * Math.cos(Math.toRadians(angulo))),
                    (int) (c.getY() + l * Math.sin(Math.toRadians(angulo)))
            ));
        }
        Polygon pol = new Polygon(list.toArray(new Point[list.size()]));
        return pol;
    }

    public void bar3d(Point p, int l) {
        square(p, l);
        Point p2 = new Point(p.getX() + (int) (Math.sin(Math.toRadians(45)) * l / 2),
                p.getY() - (int) (Math.cos(Math.toRadians(45)) * l / 2));
        line(p, p2);
        line(new Point(p.getX(), p.getY() - l), new Point(p2.getX(), p2.getY() - l));
        line(new Point(p.getX() + l, p.getY() - l), new Point(p2.getX() + l, p2.getY() - l));
        line(new Point(p.getX() + l, p.getY()), new Point(p2.getX() + l, p2.getY()));
        square(p2, l);
    }

    public void bar3d(int x, int y, int l) {
        bar3d(new Point(x, y), l);
    }

    /**
     * @return the ctx
     */
    public BufferedImage getCtx() {
        return ctx;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
