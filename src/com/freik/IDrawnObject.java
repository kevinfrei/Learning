package com.freik;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// Instead, let's think about a generic "vector drawing" program:
public interface IDrawnObject {
  public Rectangle2D bounds();
  public Point2D center();
  public boolean hits(Point2D location);
  public void Draw(Graphics drawingSurface);
};

