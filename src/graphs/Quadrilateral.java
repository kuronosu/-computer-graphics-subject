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
public final class Quadrilateral extends BasePolygon {

    private Point anchorPoint;
    private int width, height;
    private QuadrilateralAnchor anchorPosition;
    private static final QuadrilateralAnchor defaultAnchorPosition = QuadrilateralAnchor.BOTTOM_LEFT;

    public Quadrilateral(int width, int height, Point anchorPoint,
            QuadrilateralAnchor anchorPosition) {
        super(new Point[0]);
        this.anchorPoint = anchorPoint;
        this.width = width;
        this.height = height;
        this.anchorPosition = anchorPosition;
        this.valid = width > 0 && height > 0;
        if (valid) {
            calculateVertices();
            calculateBounds();
        }
    }

    public Quadrilateral(int width, int height, Point anchorPoint) {
        this(width, height, anchorPoint, defaultAnchorPosition);
    }

    public Quadrilateral(int side, Point anchorPoint, QuadrilateralAnchor anchorPosition) {
        this(side, side, anchorPoint, anchorPosition);
    }

    public Quadrilateral(int side, Point anchorPoint) {
        this(side, side, anchorPoint, defaultAnchorPosition);
    }

    public Quadrilateral(Point p1, Point p2) {
        super(new Point[0]);
        valid = !(p1.getX() == p2.getX() || p1.getY() == p2.getY());
        if (valid) {
            width = Math.abs(p1.getX() - p2.getX());
            height = Math.abs(p1.getY() - p2.getY());
            vertices = new Point[]{
                p1, new Point(p1.getX(), p2.getY()),
                p2, new Point(p2.getX(), p1.getY())
            };
            calculateBounds();
        }
    }

    private void calculateVertices() {
        Point point;
        switch (anchorPosition) {
            case CENTER_LEFT:
                point = new Point(anchorPoint.getX(), anchorPoint.getY() - height / 2);
                break;
            case BOTTOM_LEFT:
                point = new Point(anchorPoint.getX(), anchorPoint.getY() - height);
                break;
            case TOP_CENTER:
                point = new Point(anchorPoint.getX() - width / 2, anchorPoint.getY());
                break;
            case CENTER:
                point = new Point(anchorPoint.getX() - width / 2, anchorPoint.getY() - height / 2);
                break;
            case BOTTOM_CENTER:
                point = new Point(anchorPoint.getX() - width / 2, anchorPoint.getY() - height);
                break;
            case TOP_RIGHT:
                point = new Point(anchorPoint.getX() - width, anchorPoint.getY());
                break;
            case CENTER_RIGHT:
                point = new Point(anchorPoint.getX() - width, anchorPoint.getY() - height / 2);
                break;
            case BOTTOM_RIGHT:
                point = new Point(anchorPoint.getX() - width, anchorPoint.getY() - height);
                break;
            default:
                point = anchorPoint;
        }
        vertices = new Point[]{point,
            new Point(point.getX() + width, point.getY()),
            new Point(point.getX() + width, point.getY() + height),
            new Point(point.getX(), point.getY() + height)};
    }
}
