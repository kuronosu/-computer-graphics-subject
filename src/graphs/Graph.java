/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author kuro
 */
public class Graph {

    private BufferedImage ctx;
    private Color color;

    public Graph(int w, int h, Color color) {
        ctx = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        this.color = color;
    }

    public Graph(int w, int h) {
        this(w, h, Color.BLACK);
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
        return new Quadrilateral(p1, p2).draw(this);
    }

    public boolean rectangle(int x1, int y1, int x2, int y2) {
        return rectangle(new Point(x1, y1), new Point(x2, y2));
    }

    // rectangle by point
    public boolean rectangle(Point p1, int width, int height) {
        return new Quadrilateral(width, height, p1).draw(this);
    }

    public boolean rectangle(Point p1, int width, int height, QuadrilateralAnchor anchor) {
        return new Quadrilateral(width, height, p1, anchor).draw(this);
    }

    public boolean rectangle(int x1, int y1, int width, int height, QuadrilateralAnchor anchor) {
        return rectangle(new Point(x1, y1), width, height, anchor);
    }

    // square
    public boolean square(Point p1, int side) {
        return new Quadrilateral(side, side, p1).draw(this);
    }

    public boolean square(int x1, int y1, int side) {
        return square(new Point(x1, y1), side);
    }

    public boolean square(Point p1, int side, QuadrilateralAnchor anchor) {
        return new Quadrilateral(side, side, p1, anchor).draw(this);
    }

    public boolean square(int x1, int y1, int side, QuadrilateralAnchor anchor) {
        return square(new Point(x1, y1), side, anchor);
    }

    // trectangulo
    public boolean rightTriangle(Point p, int side1, int side2) {
        return new Polygon(new Point[]{
            p, new Point(p.getX() + side1, p.getY()),
            new Point(p.getX(), p.getY() - side2)}).draw(this);
    }

    public boolean rightTriangle(int x1, int y1, int side1, int side2) {
        return rightTriangle(new Point(x1, y1), side1, side2);
    }

    // tequilatero
    public boolean equilateralTriangle(Point p, int side) {
        int h = (int) ((Math.sqrt(3) * side) / 2);
        return new Polygon(new Point[]{
            p, new Point(p.getX() + side, p.getY()),
            new Point(p.getX() + side / 2, p.getY() - h)}).draw(this);
    }

    public boolean equilateralTriangle(int x1, int y1, int side) {
        return equilateralTriangle(new Point(x1, y1), side);
    }

    // pentagon
    public boolean pentagon(Point p, int side) {
        int x = (int) Math.abs(Math.sin(72) * side);
        int y = (int) Math.abs(Math.cos(72) * side);
        int h = (int) (side / (2 * Math.tan(Math.toRadians(36)))) * 2;

        return new Polygon(new Point[]{
            p, new Point(p.getX() + side, p.getY()),
            new Point(p.getX() + side + x, p.getY() - y),
            new Point(p.getX() + side / 2, p.getY() - h),
            new Point(p.getX() - x, p.getY() - y),}).draw(this);
    }

    public boolean pentagon(int x1, int y1, int side) {
        return pentagon(new Point(x1, y1), side);
    }

    // polygon
    public boolean polygon(int[] vertices, int sides) {
        return new Polygon(vertices, sides).draw(this);
    }

    public boolean polygon(Point[] vertices) {
        return new Polygon(vertices).draw(this);
    }

    // circle
    public void circle(int cx, int cy, int r) {
        for (int i = 0; i < 360; i++) {
            int x = (int) (Math.sin(Math.toRadians(i)) * r);
            int y = (int) (Math.cos(Math.toRadians(i)) * r);
            pixel(cx + x, cy + y);
        }
    }

    /**
     * @return the ctx
     */
    public BufferedImage getCtx() {
        return ctx;
    }

    /**
     * @param ctx the ctx to set
     */
    public void setCtx(BufferedImage ctx) {
        this.ctx = ctx;
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
}
