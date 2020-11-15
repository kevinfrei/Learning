package com.freik;

public class Line {
  private Point c;
  private Point d;
  // Ax + By + C = 0
  // A = -Rise
  // B = Run
  private double A(/* Line this */ ) {
    return -this.c.Ydis(this.d);
  }

  private double B(/* Line this */ ) {
    return this.c.Xdis(this.d);
  }

  private double C(/* Line this */ ) {
    double Cc = -A() * c.X() - B() * c.Y();
    double Cd = -A() * d.X() - B() * d.Y();
    if (Cc != Cd) {
      System.out.printf(
          "%f != %f - This might be true, but they should be *really* close\n", Cc, Cd);
    }
    return Cc;
  }

  public Line(Point a, Point b) {
    this.c = a;
    this.d = b;
  }

  public double Slope() {
    double e = d.Xdis(c);
    double f = d.Ydis(c);
    double y = Math.atan2(f, e);
    return Math.toDegrees(y);
  }
  // https://byjus.com/point-of-intersection-formula/
  private Point getIntercept(/* Line this, */ Line other) throws Exception {
    double ix, iy;
    double a1, b1, c1, a2, b2, c2;
    a1 = this.A();
    b1 = this.B();
    c1 = this.C();
    a2 = other.A();
    b2 = other.B();
    c2 = other.C();
    double divider = a1 * b2 - a2 * b1;
    if (divider == 0.0) {
      throw new Exception("Lines are parallel");
    }
    ix = (b1 * c2 - b2 * c1) / divider;
    iy = (a2 * c1 - a1 * c2) / divider;
    return new Point(ix, iy);
  }

  // Is Point i within the "bounding box" of the line this.c -> this.d
  private boolean inRange(/* Line this */ Point i) {
    double ix = i.X();
    double iy = i.Y();
    double cx = this.c.X();
    double cy = this.c.Y();
    double dx = this.d.X();
    double dy = this.d.Y();
    boolean xInRange, yInRange;
    if (cx <= dx) {
      xInRange = (cx <= ix && ix <= dx);
    } else {
      xInRange = (dx <= ix && ix <= cx);
    }
    if (cy <= dy) {
      yInRange = (cy <= iy && iy <= dy);
    } else {
      yInRange = (dy <= iy && iy <= cy);
    }
    return xInRange && yInRange;
  }

  private boolean colinear(/* Line this, */ Line other) {
    // If the y intercept is 0, then only identical slopes are colinear
    double tA = this.A();
    double tB = this.B();
    double tC = this.C();
    double oA = other.A();
    double oB = other.B();
    double oC = other.C();

    if (tC == 0 && oC == 0) {
      if (tA == oA && tB == oB) {
        return true;
        // Handle negatives of both A & B
        // 2x - 3y = 0  is the same as -2x + 3y = 0
      } else if (tA == -oA && tB == -oB) {
        return true;
      } else {
        return false;
      }
    }
    if (tC == 0 || oC == 0) {
      return false;
    }
    // We know both C's are non-zero
    // 2x + 3y + 1 = 0 is the same as -4x + -6y + -2 = 0
    tA = tA / tC;
    tB = tB / tC;
    oA = oA / oC;
    oB = oB / oC;
    return tA == oA && tB == oB;
  }

  public boolean intersect(/* Line this, */ Line other) {
    try {
      Point i = this.getIntercept(other);
      if (this.inRange(i)) {
        if (other.inRange(i)) {
          return true;
        }
      }
    } catch (Exception e) {
      // Exception occurs for no intercept, which means the lines are *parallel*!
      // If they're colinear, they might still intersect
      if (this.colinear(other)) {
        if (this.inRange(other.c)) {
          return true;
        } else if (this.inRange(other.d)){
          return true;
        }
      }
    }
    return false;
  }

  public static void test() {
    System.out.println("Hello from Line.test");
    Point origin = new Point(0,0);
    Point p11 = new Point (1,1);
    Point p32 = new Point(3, 2);
    Point p33 = new Point(3,3);
    Line a = new Line(origin, p11);
    Line b = new Line(origin, p32);
    Line c = new Line(p11, p32);
    Line vertical = new Line(p33, p32);
    if (a.intersect(b)){
      System.out.println("Yes!");
    } else {
      System.out.println("Kevin messed up!");
    }
    if (a.intersect(c)){
      System.out.println("Yes!");
    } else {
      System.out.println("Kevin messed up again!");
    }
    if (c.intersect(b)){
      System.out.println("Yes!");
    } else {
      System.out.println("Kevin messed still messed up!");
    }
    if (a.intersect(vertical)){
      System.out.println("Bad bad bad!");
    } else {
      System.out.println("Vertical lines seem to work");
    }
  }
}
