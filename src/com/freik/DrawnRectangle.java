package com.freik;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DrawnRectangle implements IDrawnObject {
  // Member data: stuff that "lives" with each DrawnRectangle object
  int X, Y, W, H;

  public DrawnRectangle(int x, int y, int w, int h) {
    this.X = x;
    this.Y = y;
    this.W = w;
    this.H = h;
  }

  public DrawnRectangle(Point a, Point b) {
    int x1 = (int) a.X();
    int x2 = (int) b.X();
    int y1 = (int) a.Y();
    int y2 = (int) b.Y();
    this.X = Math.min(x1, x2);
    this.Y = Math.min(y1, y2);
    this.W = Math.abs(x1 - x2);
    this.H = Math.abs(y1 - y2);
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
    drawingSurface.drawRect(this.X, this.Y, this.W, this.H);
  }
}
