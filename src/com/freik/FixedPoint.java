package com.freik;

public class FixedPoint {
  public FixedPoint(int whole, int fraction) {
    // Fraction should be from 0-9. Anything else is "out of range" logically
    // Remember, the constructor is a good place to "normalize" all your data:
  }

  public FixedPoint add(FixedPoint b){
    return b;
  }
  public FixedPoint subtract(FixedPoint b){
    return b;
  }

  public FixedPoint multiply(FixedPoint b){
    return b;
  }

  public FixedPoint divide(FixedPoint b){
    return b;
  }

  public FixedPoint negate(){
    return this;
  }

  public boolean equals(FixedPoint b){
    return false;
  }

  public boolean lessThan(FixedPoint b) {
    return false;
  }

  public boolean greaterThan(FixedPoint b) {
    return false;
  }

  @Override
  public String toString() {
    return "";
  }
}
