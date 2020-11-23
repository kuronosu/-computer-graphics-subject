package main;

import graphs.Graph;
import graphs.Point;
import graphs.QuadrilateralAnchor;
import graphs.Style;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {
        int w = 500, h = 500;
        Graph g = cubeTest(); // change the test method for each figure

        JFrame frame = new JFrame("Pentagon");
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        JLabel l = new JLabel(new ImageIcon(g.getCtx()));
        frame.getContentPane().add(l);
        frame.pack();
        frame.setVisible(true);
    }

    private static Graph lineTest() {
        Graph g = new Graph(500, 500, Color.YELLOW);
        for (int i = 0; i < 250; i += 25) {
            g.line(i, 250, 250, 225 - i);
            g.line(250, i, i + 275, 250);
            g.line(i, 250, 250, 275 + i);
            if (275 + i == 500) {
                g.line(499, 250, 250, 275);
            } else {
                g.line(275 + i, 250, 250, 500 - i);
            }
        }
        g.setColor(Color.WHITE);
        g.line(0, 250, 500, 250);
        g.line(250, 0, 250, 500);
        return g;
    }

    private static Graph rectangleTest() {
        Graph g = new Graph(500, 500, Color.RED);
        g.setStyle(new Style(1, Color.YELLOW));
        g.rectangle(20, 20, 120, 120);
        g.line(20, 20, 120, 120);
        g.setColor(Color.GREEN);
        g.rectangle(20, 230, 120, 130);
        g.line(20, 230, 120, 130);
        g.setColor(Color.BLUE);
        g.rectangle(230, 20, 130, 120);
        g.line(230, 20, 130, 120);
        g.setColor(Color.WHITE);
        g.rectangle(130, 130, 230, 230);
        g.line(130, 130, 230, 230);
        return g;
    }

    private static Graph squareTest() {
        Graph g = new Graph(500, 500, Color.RED);
        g.setStyle(new Style(1, Color.YELLOW));
        g.square(20, 120, 100);
        g.line(20, 20, 120, 120);
        g.setColor(Color.GREEN);
        g.square(20, 230, 100);
        g.line(20, 230, 120, 130);
        g.setColor(Color.BLUE);
        g.square(130, 120, 100);
        g.line(230, 20, 130, 120);
        g.setColor(Color.WHITE);
        g.square(130, 130, 100, QuadrilateralAnchor.TOP_LEFT);
        g.line(130, 130, 230, 230);
        return g;
    }

    private static Graph rightTriangleTest() {
        Graph g = new Graph(500, 500, Color.YELLOW);
        g.setStyle(new Style(1, Color.BLUE));
        g.rightTriangle(20, 120, 100, 50);
        return g;
    }

    private static Graph equilateralTriangleTest() {
        Graph g = new Graph(500, 500, Color.YELLOW);
        g.setStyle(new Style(1, Color.BLUE));
        g.equilateralTriangle(20, 120, 100);
        return g;
    }

    private static Graph pentagonTest() {
        Graph g = new Graph(500, 500, Color.RED);
        g.setStyle(new Style(1, Color.CYAN));
//        g.line(175, 0, 175, 500);
//        g.line(0, 297, 500, 297);
//        g.setColor(Color.CYAN);
        g.pentagon(100, 400, 150);
        return g;
    }

    private static Graph polygonTest() {
        Graph g = new Graph(500, 500, Color.CYAN);
        g.setStyle(new Style(1, Color.WHITE));
        g.polygon(new int[]{20, 20, 80, 10, 100, 15, 130, 50, 110, 60}, 4);
        g.setColor(Color.YELLOW);
        g.polygon(new int[]{120, 120, 180, 110, 200, 115, 230, 150, 210, 160}, 5);
        return g;
    }

    private static Graph circleTest() {
        Graph g = new Graph(500, 500, Color.WHITE);
        g.setStyle(new Style(7, Color.YELLOW));
        g.circle(250, 250, 200);
        g.line(250, 0, 250, 500);
        g.line(0, 250, 500, 250);
        g.line(0, 0, 500, 500);
        g.line(500, 0, 0, 500);
//        g.circle(140, 140, 100);
//        g.setStyle(new Style(1, Color.YELLOW));
//        g.circle(360, 140, 100);
//        g.setStyle(new Style(6, Color.YELLOW));
//        g.circle(140, 360, 100);
//        g.setStyle(new Style(7, Color.YELLOW));
//        g.circle(360, 360, 100);
        return g;
    }

    private static Graph nAgonoTest() {
        Graph g = new Graph(500, 500, Color.CYAN);
        g.setStyle(new Style(1, Color.YELLOW));
        g.nAgono(100, 100, 7, 75);
        g.nAgono(400, 100, 8, 75);
        g.nAgono(100, 400, 9, 75);
        g.nAgono(400, 400, 10, 75);
        return g;
    }

    private static Graph fillTest() {
        Graph g = new Graph(500, 500, Color.CYAN);
        g.setStyle(new Style(6, Color.YELLOW));
//        g.nAgono(250, 250, 10, 100);
//        g.line(50, 200, 50, 300);
//        g.line(50, 200, 500, 200);
//        g.line(50, 300, 500, 300);
//        g.nAgono(100, 250, 5, 50);
//        g.setColor(Color.RED);
//        g.nAgono(400, 100, 9, 50);
//        g.nAgono(100, 400, 7, 50);
//        g.nAgono(400, 400, 9, 50);
        g.polygon(new Point[]{
            new Point(0, 200),
            new Point(40, 50),
            new Point(90, 150),
            new Point(190, 150),
            new Point(240, 50),
            new Point(280, 200),
            new Point(140, 400)});
//        g.polygon(new Point[]{
//            new Point(100, 150),
//            new Point(50, 50),
//            new Point(250, 150),
//            new Point(300, 50),
//        });
//        g.polygon(new Point[]{
//            new Point(100, 150),
//            new Point(50, 50),
//            new Point(250, 150),
//            new Point(200, 50)
//        });
//        g.setStyle(new Style(0, Color.YELLOW));
//        g.nAgono(100, 400, 9, 75);
//        g.setStyle(new Style(1, Color.YELLOW));
//        g.nAgono(400, 400, 10, 75);
//        g.pentagon(100, 400, 150);
//        g.nAgono(250, 250, 10, 100);
//        g.setColor(Color.BLUE);
//        g.line(311,0,311,500);
//        g.line(0, 440,500,440);
//        g.line(0, 59,500,59);
        return g;
    }
    
    private static Graph cubeTest() {
        Graph g = new Graph(500, 500, Color.CYAN);
//        g.setStyle(new Style(1, Color.YELLOW));
        g.bar3d(100, 300, 200);
        return g;
    }
}
