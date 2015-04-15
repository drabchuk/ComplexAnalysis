/**
 * Created by Денис on 03.04.2015.
 */
import objects.*;
import geometry.*;
import draw.*;

import java.awt.*;
import java.applet.Applet;
import java.util.ArrayList;

public class Main extends Applet {

    Dimension dmDown;
    Dimension dmUp;
    Dimension dmPrev, dmCurrent;
    Figure bazis = new Figure(new Observer());
    boolean bDrawing;

    public String getAppletInfo()
    {
        return "Name: Main";
    }
    public void init() {
        bDrawing = false;
    }
    public void paint(Graphics g) {
        Dimension dimAppWndDimension = getSize();
        setBackground(Color.white);
        g.setColor(Color.black);
        g.drawRect(0, 0, dimAppWndDimension.width - 1, dimAppWndDimension.height - 1);
        bazis.Show(g);
    }
    public boolean mouseDown(Event evt, int x, int y) {
        dmPrev = new Dimension(x, y);
        return true;
    }
    public boolean mouseUp(Event evt, int x, int y) {
        return true;
    }
    public boolean mouseDrag(Event evt, int x, int y) {
        Graphics g = getGraphics();
        dmCurrent = new Dimension(x, y);


        //bazis.Spin(0.0, (double)(dmCurrent.getWidth() - dmPrev.getWidth())/500, 0.0, 0.0, (double) (dmCurrent.getHeight() - dmPrev.getHeight())/500, 0.0);
        //bazis.Spin(0.0, (double)(dmCurrent.getWidth() - dmPrev.getWidth())/500, 0.0, 0.0, (double) (dmCurrent.getHeight() - dmPrev.getHeight())/500, 0.0);
        bazis.Spin((double)(dmCurrent.getWidth() - dmPrev.getWidth())/500, (double) (dmCurrent.getHeight() - dmPrev.getHeight())/500, 0.0, 0.0, 0.0, 0.0);
        repaint();
        //dmPrev = new Dimension(x, y);
        dmPrev = dmCurrent;
        return true;
    }
    public boolean mouseMove(Event evt, int x, int y) {
        return true;
    }
}
