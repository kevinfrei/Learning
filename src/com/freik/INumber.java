package com.freik;

public interface INumber<T> {
  public T add(T a);

  public T sub(T a);

  public T mul(T a);

  public T div(T b) throws Exception;

  public T neg();
}
