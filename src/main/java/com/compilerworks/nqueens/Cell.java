package com.compilerworks.nqueens;

import java.util.Comparator;
import java.util.Objects;

public class Cell implements Comparable<Cell> {

  private final int x;
  private final int y;

  private Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Cell of(int x, int y) {
    return new Cell(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Cell cell = (Cell) o;
    return x == cell.x && y == cell.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public int compareTo(Cell other) {
    return Comparator.comparingInt(Cell::getX)
        .thenComparingInt(Cell::getY)
        .compare(this, other);
  }

  @Override
  public String toString() {
    return "Cell{x=" + x + ", y=" + y + '}';
  }
}
