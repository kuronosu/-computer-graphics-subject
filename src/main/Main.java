package main;

import graphs.Graph;
import graphs.QuadrilateralAnchor;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {
        int w = 500, h = 500;
        Graph g = circleTest(); // change the test method for each figure

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
        Graph g = new Graph(500, 500, Color.WHITE);
        g.rightTriangle(20, 120, 100, 50);
        return g;
    }

    private static Graph equilateralTriangleTest() {
        Graph g = new Graph(500, 500, Color.WHITE);
        g.equilateralTriangle(20, 120, 100);
        return g;
    }

    private static Graph pentagonTest() {
        Graph g = new Graph(500, 500, Color.WHITE);
        g.line(175, 0, 175, 500);
        g.line(0, 297, 500, 297);
        g.setColor(Color.CYAN);
        g.pentagon(100, 400, 150);
        return g;
    }

    private static Graph polygonTest() {
        Graph g = new Graph(500, 500, Color.CYAN);
        g.polygon(new int[]{20, 20, 80, 10, 100, 15, 130, 50, 110, 60}, 4);
        g.setColor(Color.YELLOW);
        g.polygon(new int[]{120, 120, 180, 110, 200, 115, 230, 150, 210, 160}, 5);
        return g;
    }

    private static Graph circleTest() {
        Graph g = new Graph(500, 500, Color.CYAN);
        g.circle(250, 250, 100);
        return g;
    }
}
