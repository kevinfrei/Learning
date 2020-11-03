package com.freik;


// TODO; Good example for static methods!

public class RationalNumber {
    // Numerator & Denominator

    private int n, d;

    // This is for some simple testing
    public static void sample()/* throws Exception*/ {
        RationalNumber a = new RationalNumber(1, 2);
        RationalNumber b = new RationalNumber(2,3);
        RationalNumber c = a.add(b);
        System.out.print(RationalNumber.toString(a));
        System.out.print(" + ");
        System.out.print(RationalNumber.toString(b));
        System.out.print(" = ");
        System.out.println(RationalNumber.toString(c));

        RationalNumber d = a.mult(b.div(c));
        System.out.println(RationalNumber.toString(d));
    }

    // Constructor
    public RationalNumber(int numer, int denom) /*throws Exception*/ {
        // ERROR CHECKING!!!
        if (denom == 0) {
//            throw new Exception("DIVIDE BY ZERO! ARGH!");
        }
        this.n = numer;
        this.d = denom;
        // simplify the values
        // Doing this in the constructor makes it so the rest of the code
        // doesn't have to worry about these details
        for (int i = 2; i < this.d && i < this.n; i++) {
            while (this.n % i == 0 && this.d % i == 0) {
                this.n = this.n / i;
                this.d = this.d / i;
            }
        }
        if (this.d < 0) {
            this.d = -this.d;
            this.n = -this.n;
        }
    }
    // Multiply
    public RationalNumber mult(RationalNumber b) {
        /*   x   m   (x * m)
         *   - * - = -------
         *   y   n   (y * n)
         */
        // Normal, non-static method
        System.out.println(this.n);
        System.out.println(this.d);
        int resn = this.n * b.n;
        int resd = this.d * b.d;
        return new RationalNumber(resn, resd);
    }
    // Divide
    public RationalNumber div(RationalNumber b) {
        /*   x   m   (x * n)
         *   - / - = -------
         *   y   n   (y * m)
         */
        int resn = this.n * b.d;
        int resd = this.d * b.n;
        return new RationalNumber(resn, resd);
    }
    public RationalNumber reciprocal() {
        return new RationalNumber(this.d, this.n);
    }
    public RationalNumber add(RationalNumber b) {
        /*   x   m   (n * x + m * y)
         *   - + - = -------
         *   y   n   (y * n)
         */
        int x = this.n;
        int y = this.d;
        int m = b.n;
        int n = b.d;
        int resn = n * x + m * y;
        int resd = y * n;
        return new RationalNumber(resn, resd);
    }
    public RationalNumber subtract(RationalNumber b) {
        return this.add(b.negate());
    }
    public RationalNumber negate() {
        return new RationalNumber(-this.n, this.d);
    }
    public static String toString(RationalNumber v) {
        return Integer.toString(v.n) + "/" + Integer.toString(v.d);
    }
}
