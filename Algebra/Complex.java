package Algebra;

/**
 * Created by Денис on 03.04.2015.
 */
public class Complex {
    public double Im;
    public double Re;

    Complex() {
        Im = 0;
        Re = 0;
    }
    Complex(double r, double i) {
        Im = i;
        Re = r;
    }

    public static Complex Multiply(Complex z1, Complex z2) {
        Complex tmp = new Complex();
        tmp.Re = z1.Re*z2.Re - z1.Im*z2.Im;
        tmp.Im = z1.Im*z2.Re + z1.Re*z2.Im;
        return tmp;
    }
    public static Complex Plus(Complex z1, Complex z2) {
        Complex tmp = new Complex();
        tmp.Re = z1.Re + z2.Re;
        tmp.Im = z1.Im + z2.Im;
        return tmp;
    }

}
