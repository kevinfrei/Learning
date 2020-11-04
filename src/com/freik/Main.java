package com.freik;

public class Main {
  private static void Check(String expr, FixedPoint val, int whole, int fraction) {
    FixedPoint check = new FixedPoint(whole, fraction);
    System.out.println(expr);
    if (val.equals(check)) {
      System.out.println("Correct!");
    } else {
      System.out.println("Wrong: Got this value instead:");
      System.out.print(val);
      System.out.println();
    }
  }

  public static void main(String[] args) {
    try {
      RationalNumber.sample();
      float pi = 3.1415926f;
      // .31415926 * 10^1
      float ff = 50.5f;
      // .505 * 10^2
      float something = 1123794812.1f;
      // .1237948121123412341234 * 10^10
      FixedPoint zero = new FixedPoint(0, 0); // 0.0
      FixedPoint one = new FixedPoint(1, 0); // 1.0
      FixedPoint two = new FixedPoint(2, 0); // 2.0
      FixedPoint v11 = new FixedPoint(1, 1); // 1.1
      FixedPoint v34 = new FixedPoint(3, 4); // 3.4
      FixedPoint v66 = new FixedPoint(6, 6); // 6.6
      FixedPoint v30 = new FixedPoint(3, 0);
      FixedPoint v29 = new FixedPoint(2, 9);
      // Check equals for basic functioning
      if (!v34.equals(v34) || !one.equals(one) || v34.equals(one) || one.equals(v34)) {
        System.out.println("equals seems broken. Without that working, nothing else will work properly.");
        return;
      }
      // Easy Add/Subtract:
      Check("3.4 + 0.0 = 3.4", v34.add(zero), 3, 4);
      Check("3.4 - 0.0 = 3.4", v34.subtract(zero), 3, 4);
      Check("0.0 + 3.4 = 3.4", zero.add(v34), 3, 4);
      // Slightly harder
      Check("3.4 + 1.0 = 4.4", v34.add(one), 4, 4);
      Check("3.4 - 1.0 = 2.4", v34.subtract(one), 2, 4);
      // Harder still
      Check("3.4 + 1.1 = 4.5", v34.add(v11), 4, 5);
      Check("3.4 + 6.6 = 10.0", v34.add(v66), 10, 0);
      Check("3.0 - 2.9 = 0.1", v30.subtract(v29), 0, 1);
      // Easy Multiply tests
      Check("3.4 * 0.0 = 3.4", v34.multiply(zero), 0, 0);
      Check("3.4 * 1.0 = 3.4", v34.multiply(one), 3, 4);
      // A little harder multiply
      Check("2.0 * 2.0 = 4.0", two.multiply(two), 4, 0);
      Check("1.1 * 2.0 = 2.2", v11.multiply(two), 2, 2);
      // Rounding?
      Check("3.4 * 1.1 = 3.7", v34.multiply(v11), 3, 7);
      Check("6.6 * 1.1 = 7.3", v66.multiply(v11), 7, 3);
      // Divide is arguably complicated
      Check("3.4 / 1.0 = 3.4", v34.divide(one), 3, 4);
      Check("6.6 / 2.0 = 3.3", v66.divide(two), 3, 3);
      Check("6.6 / 1.1 = 6.0", v66.divide(v11), 6, 0);
    } catch (Exception e) {
      System.out.println("Your program crashed. You probably divided by zero.");
    }
  }
}
