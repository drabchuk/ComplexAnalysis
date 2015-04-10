package geometry;

/**
 * Created by Денис on 03.04.2015.
 */
public class Operator extends Matrix {
    private Angles angles;
    Operator() {
        super();
        for (int i = 0; i < 4; i++) {
            data[i][i] = 1;
        }
        angles = new Angles();
    }
    Operator(int j) {
        super();
    }
    Operator(Matrix m) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = m.data[i][j];
            }
        }
    }
    Operator(Operator m) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = m.data[i][j];
            }
        }
        angles = m.angles;
    }
    Operator(double XY, double XZ, double XT, double YZ, double YT, double ZT, double m) {//rotate around XY on fi1 & around ZT on fi2, Scale *m times
        angles = new Angles(XY, XZ, XT, YZ, YT, ZT);
        Operator tmp = new Operator();
        Operator tmp1 = new Operator();
        if ((XY !=0) || (ZT!=0)) {
            tmp = Rotate2x(0,1,2,3,XY,ZT);
            tmp1 = tmp1.Product(tmp);
        }
        if ((XZ !=0) || (YT!=0)) {
            tmp = Rotate2x(0,2,1,3,XZ,YT);
            tmp1 = tmp1.Product(tmp);
        }
        if ((XT !=0) || (YZ!=0)) {
            tmp = Rotate2x(0,3,1,2,XT,YZ);
            tmp1 = tmp1.Product(tmp);
        }
        tmp1.ConstProduct(m);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = tmp1.data[i][j];
            }
        }
    }

    private Operator Rotate2x(int i, int j, int k, int l, double fi1, double fi2) {
        Operator tmp = new Operator();
        tmp.data[i][i] = Math.cos(Math.toRadians(fi1));
        tmp.data[i][j] = -Math.sin(Math.toRadians(fi1));
        tmp.data[j][i] = Math.sin(Math.toRadians(fi1));
        tmp.data[j][j] = Math.cos(Math.toRadians(fi1));

        tmp.data[k][k] = Math.cos(Math.toRadians(fi2));
        tmp.data[k][l] = -Math.sin(Math.toRadians(fi2));
        tmp.data[l][k] = Math.sin(Math.toRadians(fi2));
        tmp.data[l][l] = Math.cos(Math.toRadians(fi2));
        return tmp;
    }
    Operator Product(Operator matrix) {
        Operator tmp = new Operator(0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    tmp.data[i][j] += this.data[i][k]*matrix.data[k][j];
                }
            }
        }
        return tmp;
    }
    Operator Mobr() {
        Operator tmp = new Operator();
        return tmp;
    }
}

