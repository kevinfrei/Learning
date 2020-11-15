package com.freik;

// TODO; Good example for static methods!
// https://github.com/kevinfrei/Learning
public class RationalNumber {
  // Numerator & Denominator
  // D is always positive
  // N / D is always fully simplified
  public int n, d;

  // This is for some simple testing
  public static void test() {
    int num = 1;
    RationalNumber a = new RationalNumber(48, 96); // 1/2 == .5
    RationalNumber r = a.reciprocal();

    // r =? 96/48
    System.out.printf("a = %s, reciprocal = %s\n", a, r);
    System.out.printf("a = %s\n", a);
    RationalNumber b = new RationalNumber(2, 3);
    System.out.printf("b = %s\n", b);
    RationalNumber c = a.mul(b); // a * b

    System.out.printf("%s * %s = %s\n", a, b, c);
    RationalNumber d = a.add(b);
    System.out.printf("%s + %s = %s\n", a, b, d);
    RationalNumber e = d.sub(b);
    System.out.printf("%s - %s = %s\n", d, b, e);

  }



  // Constructor
  public RationalNumber(/* RationNumber this, */ int numer, int denom) {
    // ERROR CHECKING!!!

    this.n = numer;
    this.d = denom;

    // simplify the values
    // Doing this in the constructor makes it so the rest of the code
    // doesn't have to worry about these details
    for (int i = 2; i <= this.d || i <= this.n; i++) {
      while (this.n % i == 0 && this.d % i == 0) {
        this.n = this.n / i;
        this.d = this.d / i;
      }
    }
    if (this.d < 0) {
      this.d = -this.d;
      this.n = -this.n;
    }
    // Know for sure d & n are fully simplified & n is the only neg value
    // (1, -2) => (-1, 2)
  }

  // Multiply
  public RationalNumber mul(/* RationalNumber this, */ RationalNumber b) {
    /*   x   m   (x * m)
     *   - * - = -------
     *   y   n   (y * n)
     */
    // Normal, non-static method
    // System.out.println(this.n);
    // System.out.println(this.d);
    int x = this.n;
    int y = this.d;
    int m = b.n;
    int n = b.d;

    return new RationalNumber(x * m, y * n);
  }

  // Divide
  public RationalNumber div(/* RationalNumber this */ RationalNumber b) {
    /*   x   m   (x * n)
     *   - / - = -------
     *   y   n   (y * m)
     */
    // divide a / b == a * (1/b)
    // divide this / b = this * 1/b
    return this.mul(b.reciprocal());
  }

  public RationalNumber reciprocal(/* RationalNumber this */) {
    return new RationalNumber(this.d, this.n);
  }


  public RationalNumber add(RationalNumber b) {
    /*   x   m   (n * x + m * y)
     *   - + - = ---------------
     *   y   n       (y * n)
     */
    int x = this.n;
    int y = this.d;
    int m = b.n;
    int n = b.d;
    return new RationalNumber(n * x + m * y, y * n);
  }


  public RationalNumber sub(/* RationalNumber this, */RationalNumber b) {
    // Subtract: this - b --> this + (-b)
    return this.add(b.neg());
  }


  public RationalNumber neg() {
    return new RationalNumber(-this.n, this.d);
  }


  public String toString() {
    if (this.d == 1) return Integer.toString(this.n);
    return Integer.toString(this.n) + "/" + Integer.toString(this.d);
  }
}
