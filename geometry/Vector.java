package geometry;

/**
 * Created by Денис on 03.04.2015.
 */
public class Vector {
    public double x, y, z, t;
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.t = 0;
    }
    public Vector(Vector a) {
        this.x = a.x;
        this.y = a.y;
        this.z = a.z;
        this.t = a.t;
    }
    public Vector(double x, double y, double z, double t) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
    }
    public Vector(int xx, int yy, int zz, int tt) {
        x = (double) xx;
        y = (double) yy;
        z = (double) zz;
        t = (double) tt;
    }

    private void Change(Vector v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.t = v.t;
    }
    public Vector plus(Vector b) {
        Vector c = new Vector();
        c.x = this.x + b.x;
        c.y = this.y + b.y;
        c.z = this.z + b.z;
        c.t = this.t + b.t;
        return c;
    }
    public Vector minus(Vector b) {
        Vector c = new Vector();
        c.x = this.x - b.x;
        c.y = this.y - b.y;
        c.z = this.z - b.z;
        c.t = this.t - b.t;
        return c;
    }
    public Vector ConstProduct(double c) {
        Vector tmp = new Vector();
        tmp.x = this.x*c;
        tmp.y = this.y*c;
        tmp.z = this.z*c;
        tmp.t = this.t*c;
        return tmp;
    }
    public double ScalarProduct(Vector b) {
        Vector c = new Vector();
        c.x = this.x*b.x;
        c.y = this.y*b.y;
        c.z = this.z*b.z;
        c.t = this.t*b.t;
        return c.x + c.y + c.z + c.t;
    }
    public double Norm(){
        return Math.sqrt(this.ScalarProduct(this));
    }
    public Vector normalize() {
        return this.ConstProduct(1/(this.Norm()));
    }
    public void Transform (Angles ang, double m){
        Operator A = new Operator( ang.XY, ang.XZ, ang.XT, ang.YZ, ang.YT, ang.ZT, m);
        Vector tmp = new Vector(this);
        tmp = A.VectorProduct(tmp);
        this.Change(tmp);
    }
}
