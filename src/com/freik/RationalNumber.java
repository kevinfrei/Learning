package com.freik;

// TODO; Good example for static methods!

public class RationalNumber {
  // Numerator & Denominator

  private int n, d;

  // This is for some simple testing
  public static void sample() throws Exception {
    RationalNumber a = new RationalNumber(48, 96); // 1/2 == .5
    RationalNumber b = new RationalNumber(2, 3);
    RationalNumber c = a.mult(b);
    System.out.printf("%s * %s = %s\n", a, b, c);
    RationalNumber d = a.mult(b.div(c));
    System.out.printf("%s * %s / %s = %s\n", a, b, c, d);
  }

  // Constructor
  public RationalNumber(/* RationNumber this, */ int numer, int denom) throws Exception {
    // ERROR CHECKING!!!
    if (denom == 0) {
      throw new Exception("DIVIDE BY ZERO! ARGH!");
    }
    this.n = numer;
    this.d = denom;

    // simplify the values
    // Doing this in the constructor makes it so the rest of the code
    // doesn't have to worry about these details
    for (int i = 2; i <= this.d && i <= this.n; i++) {
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
  public RationalNumber mult(/* RationalNumber this, */ RationalNumber b) throws Exception {
    /*   x   m   (x * m)
     *   - * - = -------
     *   y   n   (y * n)
     */
    // Normal, non-static method
    // System.out.println(this.n);
    // System.out.println(this.d);
    int resn = this.n * b.n;
    int resd = this.d * b.d;
    return new RationalNumber(resn, resd);
  }
  // Divide
  public RationalNumber div(RationalNumber b) throws Exception {
    /*   x   m   (x * n)
     *   - / - = -------
     *   y   n   (y * m)
     */
    return this.mult(b.reciprocal());
  }

  public RationalNumber reciprocal() throws Exception {
    return new RationalNumber(this.d, this.n);
  }

  public RationalNumber add(RationalNumber b) throws Exception {
    /*   x   m   (n * x + m * y)
     *   - + - = ---------------
     *   y   n       (y * n)
     */
    int x = this.n;
    int y = this.d;
    int m = b.n;
    int n = b.d;
    int resn = n * x + m * y;
    int resd = y * n;
    return new RationalNumber(resn, resd);
  }

  public RationalNumber subtract(RationalNumber b) throws Exception {
    return this.add(b.negate());
  }

  public RationalNumber negate() throws Exception {
    return new RationalNumber(-this.n, this.d);
  }

  @Override
  public String toString() {
    if (this.d == 1) return Integer.toString(this.n);
    return Integer.toString(this.n) + "/" + Integer.toString(this.d);
  }
}
