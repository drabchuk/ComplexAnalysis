package draw;

import java.awt.*;

/**
 * Created by Денис on 03.04.2015.
 */
public class Line {
    private double x1, x2, y1, y2;
    private int x1i, x2i, y1i, y2i;
    private int scale = 150, xShift = 550, yShift = 350;
    public Line () {
        x1 = 0.0;
        x2 = 0.0;
        y1 = 0.0;
        y2 = 0.0;
        synchronize();
    }
    public Line (int z1, int t1, int z2, int t2) {
        x1 = (double) z1;
        x2 = (double) z2;
        y1 = (double) t1;
        y2 = (double) t2;
        synchronize();
    }
    public Line (double z1, double t1, double z2, double t2) {
        x1 = z1;
        x2 = z2;
        y1 = t1;
        y2 = t2;
        synchronize();
    }
    public void Change(double z1, double t1, double z2, double t2) {
        x1 = z1;
        x2 = z2;
        y1 = t1;
        y2 = t2;
        synchronize();
    }

    private void synchronize() {
        x1i = (int) (x1*scale) + xShift;
        x2i = (int) (x2*scale) + xShift;
        y1i = (int) (y1*scale) + yShift;
        y2i = (int) (y2*scale) + yShift;
    }
    public void Init (double z1, double t1, double z2, double t2) {
        x1 = z1;
        x2 = z2;
        y1 = t1;
        y2 = t2;
        synchronize();
    }
    public void Draw (Graphics g) {
        g.drawLine(x1i, y1i, x2i, y2i);
    }
}
