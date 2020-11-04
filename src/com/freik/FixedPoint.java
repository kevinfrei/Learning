package com.freik;

public class FixedPoint {
  int w, f;
  public FixedPoint(/*FixedPoint this, */ int whole, int fraction) {
    // Fraction should be from 0-9. Anything else is "out of range" logically
    // Remember, the constructor is a good place to "normalize" all your data:
    this.w = whole;
    this.f = fraction;
  }

  public FixedPoint add(FixedPoint b){
    // WWWWWWWW.F
    // wwwwwwww.f
    //          (F + f)
    int newF = this.f + b.f;
    int carry = 0;
    if (newF >= 10) {
      carry = 1;
      newF -= 10;
    }
    int newW = this.w + b.w + carry;
    return new FixedPoint(newW, newF);
  }

  public FixedPoint subtract(FixedPoint b){
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

  public FixedPoint multiply(FixedPoint b){
    // 1234.5
    // 6789.2
    //*______
    // 2 * 1234.5 = 2,469.0
    // 6789 * 1234.5 = 8,381,020.5

    // Whole number product
    int newW = this.w * b.w;
    FixedPoint whole = new FixedPoint(newW, 0);

    // Fraction * whole (this)
    FixedPoint thisTimesbF = this.fracMult(b.f);
    // Fraction * whole (b)
    FixedPoint bTimesThisF = b.fracMult(this.f);

    // Fraction product
    int newF = this.f * b.f;
    // Adding 5 results in *rounding* .5 up to 1
    newF = (newF + 5) / 10;
    FixedPoint fTimesF = new FixedPoint(0, newF);

    // Add the four parts of the product
    return whole.add(thisTimesbF.add(bTimesThisF.add(fTimesF)));
  }

  public FixedPoint divide(FixedPoint b){
    return b;
  }
  public FixedPoint negate(){
    return this;
  }
  public boolean equals(FixedPoint b){
    return this.w == b.w && this.f == b.f;
  }
  public boolean lessThan(FixedPoint b) {
    return false;
  }
  public boolean greaterThan(FixedPoint b) {
    return false;
  }

  @Override
  public String toString() {
    return Integer.toString(w) + "." + Integer.toString(f);
  }
}
