package com.freik;

public class Point{
  private double x;
  private double y;

  public Point(double XX, double YY){
    this.x = XX;
    this.y = YY;


  }
  public double magnitude(){
    double h = Math.hypot(this.x, this.y);
    return h;
  }
  public double distance(Point a){
    double e = this.Xdis(a);
    double f = this.Ydis(a);
    double h = Math.hypot(e, f);
    return h;
  }
  public double direction(){
    double y = Math.atan2(this.y, this.x);
    return Math.toDegrees(y);
  }
  public double Xdis(Point a){
    double e = this.x - a.x;
    return e;

  }
  public double Ydis(Point b){
    double e = this.y - b.y;
    return e;
  }
  public double X() {
    return this.x;
  }
  public double Y() {
    return this.y;
  }
}
