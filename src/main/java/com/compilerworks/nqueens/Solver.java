package com.compilerworks.nqueens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {

  private final int n;
  private final List<List<Integer>> solutions = new ArrayList<>();

  public Solver(int n) {
    this.n = n;
  }

  public List<List<Integer>> solve() {
    for (int column = 0; column < this.n; column++) {
      List<Integer> placements = new ArrayList<>(List.of(column));
      solve(this.getAvailableColsOnNextRow(placements), placements);
    }

    return this.solutions;
  }

  private void solve(Set<Integer> availableColumns, List<Integer> placements) {

    // If we have already placed n queens within constraints, we have a solution
    if (placements.size() == this.n) {
      this.solutions.add(new ArrayList<>(placements));
    }

    // If there are no more available columns to place in the next row, we will go back
    // to the previous row and try another placement
    if (availableColumns.isEmpty()) {
      return;
    }

    // Search further with each available column as the starting point
    for (Integer col : availableColumns) {
      placements.add(col);
      solve(getAvailableColsOnNextRow(placements), placements);
      placements.remove(col);
    }
  }

  /**
   * Computes the available columns in the next row based on our constraints
   */
  private Set<Integer> getAvailableColsOnNextRow(List<Integer> columnsPlaced) {
    int nextRow = columnsPlaced.size();

    if (nextRow == this.n) {
      return Set.of();
    }

    Set<Integer> bannedColumns = new HashSet<>();
    for (int row = 0; row < columnsPlaced.size(); row++) {
      int col = columnsPlaced.get(row);

      bannedColumns.add(col);

      if (row == nextRow - 1) {
        bannedColumns.add(col + 1);
        bannedColumns.add(col - 1);
      }

      int diagonal1 = col + nextRow - row;
      int diagonal2 = col - nextRow + row;
      bannedColumns.add(diagonal1);
      bannedColumns.add(diagonal2);
    }

    banColumnsInStraightLine(columnsPlaced, nextRow, bannedColumns);

    Set<Integer> allColumns = IntStream.range(0, this.n).boxed().collect(Collectors.toSet());
    allColumns.removeAll(bannedColumns);
    return allColumns;
  }

  private void banColumnsInStraightLine(
      List<Integer> columnsPlaced,
      int targetRow,
      Set<Integer> bannedColumns
  ) {
    // For every pair of columns we have already placed,
    // check if the target row contains a column that could be in a straight line
    // with the selected pair.
    for (int row1 = 0; row1 < columnsPlaced.size(); row1++) {
      int col1 = columnsPlaced.get(row1);

      for (int row2 = row1 + 1; row2 < columnsPlaced.size(); row2++) {
        int col2 = columnsPlaced.get(row2);

        for (int targetCol = 0; targetCol < this.n; targetCol++) {

          // Renaming variables to be clear about the formula used for three points named
          // A(x,y) B(x,y) C(x,y)
          // Absolute of (Ax(By - Cy) + Bx(Cy - Ay) + Cx(Ay - By)) / 2
          // should be zero for three points to be in straight line

          int ax = row1;
          int ay = col1;

          int bx = row2;
          int by = col2;

          int cx = targetRow;
          int cy = targetCol;

          double area = ((ax * (by - cy)) + (bx * (cy - ay)) + (cx * (ay - by))) / 2.0;
          if (Math.abs(area) == 0.0) {
            bannedColumns.add(targetCol);
          }
        }
      }
    }
  }
}
