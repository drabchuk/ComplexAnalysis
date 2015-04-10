package geometry;

/**
 * Created by Денис on 03.04.2015.
 */
public class Angles {
    public double XY, XZ, XT, YZ, YT, ZT;

    public Angles () {
        XY = XZ = XT = YZ = YT = ZT = 0;
    }
    public Angles (double xy,double xz,double xt,double yz,double yt,double zt) {
        XY = xy;
        XZ = xz;
        XT = xt;
        YZ = yz;
        YT = yt;
        ZT = zt;
    }

    public void Rotate(double xy,double xz,double xt,double yz,double yt,double zt) {
        XY += xy;
        XZ += xz;
        XT += xt;
        YZ += yz;
        YT += yt;
        ZT += zt;
    }
}
