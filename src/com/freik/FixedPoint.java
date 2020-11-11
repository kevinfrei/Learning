package com.freik;

public class FixedPoint implements INumber<FixedPoint> {
  int w, f;

  public FixedPoint(/*FixedPoint this, */ int whole, int fraction) {
    // Fraction should be from 0-9. Anything else is "out of range" logically
    // Remember, the constructor is a good place to "normalize" all your data:
    this.w = whole;
    this.f = fraction;
  }

  @Override
  public FixedPoint add(FixedPoint b) {
    // WWWWWWWW.F
    // wwwwwwww.f
    //          (F + f)
    // 1.4 + 2.5 =
    // this.w = 1, this.f = 4
    // b.w = 2, b.f = 5
    int newW = this.w + b.w;
    int newF = this.f + b.f;
    if (newF > 9) {
      newW = newW + 1;
      newF = newF - 10;
    }
    return new FixedPoint(newW, newF);
  }

  @Override
  public FixedPoint sub(FixedPoint b) {
    int newF = this.f - b.f;
    if (newF < 0) {
      // This is for a borrow code
      int newW = this.w - b.w - 1;
      return new FixedPoint(newW, newF + 10);
    } else {
      int newW = this.w - b.w;
      return new FixedPoint(newW, newF);
    }
  }

  private FixedPoint fracMult(int fract) {
    int a = this.w * fract;
    int newW = a / 10;
    int newF = a % 10;
    return new FixedPoint(newW, newF);
  }

  @Override
  public FixedPoint mul(FixedPoint b) {
    // 1234.5
    // 6789.2
    // *______
    // 2 * 1234.5 = 2,469.0
    // 6789 * 1234.5 = 8,381,020.5

    // Whole number product
    int newW = this.w * b.w;
    FixedPoint whole = new FixedPoint(newW, 0);

    // Fraction * whole (this)
    FixedPoint thisTimesBf = this.fracMult(b.f);
    // Fraction * whole (b)
    FixedPoint bTimesThisF = b.fracMult(this.f);

    // Fraction product
    int newF = this.f * b.f;
    // Adding 5 results in *rounding* .5 up to 1
    newF = (newF + 5) / 10;
    FixedPoint fTimesF = new FixedPoint(0, newF);

    // Add the four parts of the product
    return whole.add(thisTimesBf.add(bTimesThisF.add(fTimesF)));
  }

  @Override
  public FixedPoint div(FixedPoint b) {
    // This is a bit of a cheat, but works...
    int thisNum = this.w * 10 + this.f;
    int bNum = b.w * 10 + b.f;
    int res = thisNum * 10 / bNum; // The extra * 10 gets the fractional result
    return new FixedPoint(res / 10, res % 10);
  }

  @Override
  public FixedPoint neg() {
    return new FixedPoint(-this.w, this.f);
  }

  public boolean equals(FixedPoint b) {
    return this.w == b.w && this.f == b.f;
  }

  public boolean lessThan(FixedPoint b) {
    if (this.w == b.w) return this.f < b.w;
    return this.w < b.w;
  }

  public boolean greaterThan(FixedPoint b) {
    return !this.lessThan(b);
  }

  @Override
  public String toString() {
    return Integer.toString(w) + "." + Integer.toString(f);
  }
}
