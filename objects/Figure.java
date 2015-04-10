package objects;

import draw.Line;
import geometry.Angles;
import geometry.Top;
import geometry.Vector;

import java.awt.*;

/**
 * Created by Денис on 03.04.2015.
 */
public class Figure {
    private Vector position = new Vector();
    private Angles direction = new Angles();
    private Top Bazis[] = new Top[4];
    private Top BazisCopy[] = new Top[4];
    private Top frame[][] = new Top[100][100];
    private Observer o1;
    private Line line = new Line();


    public Figure() {
        position = new Vector(0,0,0,0);
        direction = new Angles(0,0,0,0,0,0);
        Bazis[0] = new Top();
        Bazis[1] = new Top();
        Bazis[2] = new Top();
        Bazis[3] = new Top();

        Bazis[0].position4d = new Vector(1,0,0,0);
        Bazis[1].position4d = new Vector(0,1,0,0);
        Bazis[2].position4d = new Vector(0,0,1,0);
        Bazis[3].position4d = new Vector(0,0,0,1);
    }
    public Figure(Observer o) {
        position = new Vector(0,0,0,0);
        direction = new Angles(0.0,0.0,0.0,0.0,0.0,0.0);
        Bazis[0] = new Top();
        Bazis[1] = new Top();
        Bazis[2] = new Top();
        Bazis[3] = new Top();

        Bazis[0].position4d = new Vector(1,0,0,0);
        Bazis[1].position4d = new Vector(0,1,0,0);
        Bazis[2].position4d = new Vector(0,0,1,0);
        Bazis[3].position4d = new Vector(0,0,0,1);
        o1 = o;

        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++) {
                frame[i][j] = new Top();
            }

        double x, y, u, v;

        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++) {
                x = (i-12.5)/5;
                y = (j-12.5)/5;
                /* z*z
                u = ((i - 12.5)*(i-12.5) - (j-12.5)*(j-12.5))/25;
                v = 2*(((i-12.5)*(j-12.5))/25);
                */
                /* e^z
                u = Math.exp(x) * Math.cos(y);
                v = Math.exp(x) * Math.sin(y);
                */
                /*zhukovskiy*/
                u = Math.exp(x) * Math.cos(y);
                v = Math.exp(x) * Math.sin(y);
                //frame[i][j].position4d = new Vector(((double)i/5) - 2.5, ((double) j/5) - 2.5, ((double) i/5) - 2.5, ((double) j/5) - 2.5);
                //frame[i][j].position4d = new Vector((double)(i-12.5)/5, (double) (j-12.5)/5, ((double) ((i - 12.5)*(i-12.5) - (j-12.5)*(j-12.5))/25), (double) (2*(i-12.5)*(j-12.5))/25);
                frame[i][j].position4d = new Vector(x, y, u, v);
            }
    }

    public void Show(Graphics g) {
        Transformate();
        g.setColor(Color.red);
        line.Change(0,0,BazisCopy[0].position2d.x, BazisCopy[0].position2d.y);
        line.Draw(g);
        //g.drawLine(0,0,(int) BazisCopy[0].position2d.x, (int) BazisCopy[0].position2d.y);

        g.setColor(Color.green);
        line.Change(0,0,BazisCopy[1].position2d.x, BazisCopy[1].position2d.y);
        line.Draw(g);
        //g.drawLine(0,0,(int) BazisCopy[1].position2d.x, (int) BazisCopy[1].position2d.y);

        g.setColor(Color.blue);
        line.Change(0,0, BazisCopy[2].position2d.x, BazisCopy[2].position2d.y);
        line.Draw(g);
        //g.drawLine(0,0,(int) BazisCopy[2].position2d.x, (int) BazisCopy[2].position2d.y);

        g.setColor(Color.yellow);
        line.Change(0,0, BazisCopy[3].position2d.x, BazisCopy[3].position2d.y);
        line.Draw(g);
        //g.drawLine(0,0,(int) BazisCopy[3].position2d.x, (int) BazisCopy[3].position2d.y);
        g.setColor(Color.black);
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 24; j++) {
                line.Change(frame[i][j].position2d.x, frame[i][j].position2d.y, frame[i+1][j].position2d.x, frame[i+1][j].position2d.y);
                line.Draw(g);
                line.Change(frame[i][j].position2d.x, frame[i][j].position2d.y, frame[i][j+1].position2d.x, frame[i][j+1].position2d.y);
                line.Draw(g);
            }
        }
    }
    public void Spin(Angles a) {
        direction.XY += a.XY;
        direction.XZ += a.XZ;
        direction.XT += a.XT;
        direction.YZ += a.YZ;
        direction.YT += a.YT;
        direction.ZT += a.ZT;
    }
    public void Spin(int x, int y) {
        Spin(0.0, (double) x, 0.0, 0.0, (double) y, 0.0);
    }
    public void Spin(double xy,double xz,double xt,double yz,double yt,double zt) {
        direction.XY += xy;
        direction.XZ += xz;
        direction.XT += xt;
        direction.YZ += yz;
        direction.YT += yt;
        direction.ZT += zt;
    }
    private void Transformate() {
        Rotate();
        Calculate3dProjection();
        Calculate2DProjection();
    }
    private void Rotate() {
        for(int i = 0; i < 4; i++) {
            BazisCopy[i] = Bazis[i];
            BazisCopy[i].position4d.Transform(direction, 1);
        }
        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++) {
                frame[i][j].position4d.Transform(direction,1);
            }
    }

    private void Init() {

    }

    private void Calculate3dProjection() {
        for (int i = 0; i < 4; i++) {
            Vector a = BazisCopy[i].position4d.minus(o1.position);
            double s = a.ScalarProduct(o1.direction);
            s = s / (o1.direction.Norm());
            Vector v = o1.direction.ConstProduct(s / o1.direction.Norm());
            Vector v1 = a.minus(v);
            double l = o1.direction.Norm() / (v.Norm());
            BazisCopy[i].position3d = v1.ConstProduct(l);
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Vector a = frame[i][j].position4d.minus(o1.position);
                double s = a.ScalarProduct(o1.direction);
                s = s / (o1.direction.Norm());
                Vector v = o1.direction.ConstProduct(s / o1.direction.Norm());
                Vector v1 = a.minus(v);
                double l = o1.direction.Norm() / (v.Norm());
                frame[i][j].position3d = v1.ConstProduct(l);
            }
        }
    }
    private void Calculate2DProjection() {
        for (int i = 0; i < 4; i++) {
            Vector a = BazisCopy[i].position3d.minus(o1.position3d);
            double s = a.ScalarProduct(o1.direction3d);
            double xx= o1.direction3d.Norm();
            s = s/(o1.direction3d.Norm());
            Vector v = o1.direction3d.ConstProduct(s/o1.direction3d.Norm());
            Vector v1 = a.minus(v);
            double l = o1.direction3d.Norm() / (v.Norm());
            BazisCopy[i].position2d = v1.ConstProduct(l);
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                Vector a = frame[i][j].position3d.minus(o1.position3d);
                double s = a.ScalarProduct(o1.direction3d);
                s = s / (o1.direction3d.Norm());
                Vector v = o1.direction3d.ConstProduct(s / o1.direction3d.Norm());
                Vector v1 = a.minus(v);
                double l = o1.direction3d.Norm() / (v.Norm());
                frame[i][j].position2d = v1.ConstProduct(l);
            }
        }
    }
}
