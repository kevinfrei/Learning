package com.freik;

import sun.jvm.hotspot.utilities.Assert;

public class FixedPoint {
  // WHole value
  int w;
  // Fractional
  int f;
  // Sign
  boolean isNegative;

  public FixedPoint(/*FixedPoint this, */ int whole, int fraction) {
    this.w = whole;
    this.f = fraction;
    if (fraction > 9 || fraction < 0) {
      throw new IllegalArgumentException("You idiot: Only pass in 0-9 for fractional values");
    }
    if (whole < 0) {
      isNegative = true;
      whole = -whole;
    } else {
      isNegative = false;
    }
  }

  public FixedPoint(/* FixedPoint this, */ int whole) {
    this.w = Math.abs(whole);
    this.f = 0;
    this.isNegative = (whole < 0);
  }

  public FixedPoint(/* FixedPoint this, */ double d) {
    // w & f should always be positive
    this.w = (int) Math.abs(d);
    this.f = (int) (Math.abs(d) * 10) % 10;
    this.isNegative = (d < 0) ? true : false;
  }

  public boolean equals(/* FixedPoint this, */ FixedPoint b) {
    boolean res = true;
    if (this.w != b.w) {
      res = false;
    } else if (this.f != b.f) {
      res = false;
    }
    return res;
  }

  public FixedPoint add(/* FixedPoint this, */ FixedPoint b) {
    // W.F
    // w.f
    int whole, fraction, carry;
    fraction = this.f + b.f;
    if (fraction > 9) {
      fraction = fraction % 10;
      carry = 1;
    } else {
      carry = 0;
    }
    whole = carry + this.w + b.w;
    return new FixedPoint(whole, fraction);
  }

  @Override
  public FixedPoint sub(FixedPoint b) {
    // 1.2 - 1.6
    // -a - -b
    // -a - b
    // a - -b
    // a - b
    if (this.isNegative && b.isNegative) {
      // -4 - -2 = -4 + 2
      return this.add(b.neg());
    } else if (this.isNegative && !b.isNegative) {
      // -4 - 2 = -(-(-4) + 2)
      return this.neg().add(b).neg();
    } else if (!this.isNegative && b.isNegative) {
      // 4 - -2
      return this.add(b.neg());
    } else if (!this.isNegative && !b.isNegative) {
      // Fall through
    }
    int newW = this.w - b.w; // 0
    int newF = this.f - b.f; // -4
    if (newF < 0) {
      newF = -newF;
      return new FixedPoint(true, newW, newF);
    }
    return new FixedPoint(newW, newF);
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
    return new FixedPoint(!this.isNegative, this.w, this.f);
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
