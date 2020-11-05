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
public class Line implements Drawable {

    private Point p1, p2;
    private LineMethod method;

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

    @Override
    public boolean draw(Graph g) {
        switch (method) {
            case DDA:
                drawDDA(g);
                break;
            case BRESENHAM:
                drawBresenham(g);
                break;
            case KURO:
                drawKuro(g);
                break;
            default:
                drawDDA(g);
        }
        return true;
    }

    private void drawKuro(Graph g) {
        int x1 = p1.getX(), y1 = p1.getY(), x2 = p2.getX(), y2 = p2.getY();
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
        for (int i = start; i <= end; i++) {
            if (f) {
                g.pixel(i, (int) (m * (i - x1) + y1));
            } else {
                g.pixel((int) ((x1 + ((i - y1) / m))), i);
            }
        }
    }

    private void drawDDA(Graph g) {
        int x1 = p1.getX(), y1 = p1.getY(), x2 = p2.getX(), y2 = p2.getY();
        float ax, ay, x = x1, y = y1, pasos;
        int deltaX = x2 - x1;
        int deltaY = y2 - y1, i = 1;
        pasos = Math.abs(deltaX) > Math.abs(deltaY) ? Math.abs(deltaX) : Math.abs(deltaY);
        ax = deltaX / pasos;
        ay = deltaY / pasos;
        while (i++ <= pasos) {
            g.pixel((int) Math.floor(x), (int) Math.floor(y));
            x += ax;
            y += ay;
        }
    }

    private void drawBresenham(Graph g) {
        int x1 = p1.getX(), y1 = p1.getY(), x2 = p2.getX(), y2 = p2.getY();
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
        for (i = 1; i <= ax; i++) {
            g.pixel(x, y);
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
    }
}
