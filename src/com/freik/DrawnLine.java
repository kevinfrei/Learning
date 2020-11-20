package com.freik;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DrawnLine implements IDrawnObject {
  Point A, B;

  public DrawnLine(Point a, Point b) {
    this.A = a;
    this.B = b;
  }

  public DrawnLine(int xa, int ya, int xb, int yb) {
    this.A = new Point(xa, ya);
    this.B = new Point(xb, yb);
  }

  @Override
  public Rectangle2D bounds() {
    return null;
  }

  @Override
  public Point2D center() {
    return null;
  }

  @Override
  public boolean hits(Point2D location) {
    return false;
  }

  @Override
  public void Draw(Graphics drawingSurface) {
    drawingSurface.drawLine((int) this.A.X(), (int) this.A.Y(), (int) this.B.X(), (int) this.B.Y());
  }
}
