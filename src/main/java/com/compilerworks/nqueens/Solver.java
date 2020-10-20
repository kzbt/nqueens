package com.compilerworks.nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {

  private final int n;
  private final int[][] board;

  private final Set<List<Integer>> solutions = new HashSet<>();

  public Solver(int n) {
    this.n = n;
    this.board = new int[this.n][this.n];

    for (int[] row : this.board) {
      Arrays.fill(row, 0);
    }
  }

  public void solve() {
    for (int column = 0; column < this.n; column++) {
      List<Integer> placements = new ArrayList<>(List.of(column));
      solve(this.getAvailableColsOnNextRow(placements), placements);
    }

    System.out.println("Found " + this.solutions.size() + " solutions");
//    System.out.println(this.solutions);
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

    Set<Integer> allColumns = IntStream.range(0, this.n).boxed().collect(Collectors.toSet());
    allColumns.removeAll(bannedColumns);
//    System.out.println("Placed: " + columnsPlaced);
//    System.out.println("Banned: " + bannedColumns);
//    System.out.println("Available: " + allColumns);
    return allColumns;
  }
}
