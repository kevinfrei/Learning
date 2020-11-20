package com.freik;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DrawnDot implements IDrawnObject {
  int X, Y;
  public DrawnDot(int x, int y) {
    this.X = x;
    this.Y = y;
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
    drawingSurface.fillRect(this.X,this.Y,1,1);
  }
}
