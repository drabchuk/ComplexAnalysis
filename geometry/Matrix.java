package geometry;

/**
 * Created by Денис on 03.04.2015.
 */
public class Matrix {
    public double[][] data = new double[4][4];
    Matrix() {
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j++)
                data[i][j] = 0;
        }
    }
    Matrix Product (Matrix matrix) {
        Matrix tmp = new Matrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    tmp.data[i][j] += this.data[i][k]*matrix.data[k][j];
                }
            }
        }
        return tmp;
    }
    Vector VectorProduct (Vector v) {
        Vector tmp = new Vector();
        tmp.x += v.x*this.data[0][0];
        tmp.x += v.y*this.data[0][1];
        tmp.x += v.z*this.data[0][2];
        tmp.x += v.t*this.data[0][3];

        tmp.y += v.x*this.data[1][0];
        tmp.y += v.y*this.data[1][1];
        tmp.y += v.z*this.data[1][2];
        tmp.y += v.t*this.data[1][3];

        tmp.z += v.x*this.data[2][0];
        tmp.z += v.y*this.data[2][1];
        tmp.z += v.z*this.data[2][2];
        tmp.z += v.t*this.data[2][3];

        tmp.t += v.x*this.data[3][0];
        tmp.t += v.y*this.data[3][1];
        tmp.t += v.z*this.data[3][2];
        tmp.t += v.t*this.data[3][3];
        return tmp;
    }
    void ConstProduct (double m) {
        Matrix tmp = new Matrix();
        double s = Math.sqrt(Math.sqrt(m));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = data[i][j]*s;
            }
        }
    }
}
