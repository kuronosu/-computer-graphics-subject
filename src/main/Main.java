package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    private BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    private Color color = Color.WHITE;

    public static void main(String[] args) {
        int w = 500, h = 500;

        Main m = new Main();
        m.lineTest();

        JFrame frame = new JFrame("Pentagon");
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        JLabel l = new JLabel(new ImageIcon(m.image));
        frame.getContentPane().add(l);
        frame.pack();
        frame.setVisible(true);
    }

    private void lineTest() {
        color = Color.RED;
        for (int i = 0; i < 250; i += 25) {
            line(i, 250, 250, 225 - i);
            line(250, i, i + 275, 250);
            line(i, 250, 250, 275 + i);
            if (275 + i == 500) {
                line(499, 250, 250, 275);
            } else {
                line(275 + i, 250, 250, 500 - i);
            }
        }
        color = Color.WHITE;
        line(0, 250, 500, 250);
        line(250, 0, 250, 500);
    }

    private void pixel(int x, int y){
        try {
            image.setRGB(x, y, color.getRGB());
        } catch (Exception e) {
        }
    }

    private void line(int x1, int y1, int x2, int y2) {
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
                pixel(i, (int) (m * (i - x1) + y1));
            } else {
                pixel((int) ((x1 + ((i - y1) / m))), i);
            }
        }
    }
}