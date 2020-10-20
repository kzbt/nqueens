package com.compilerworks.nqueens;

import java.util.List;

public class Nqueens {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Not enough arguments");
      System.exit(-1);
    }


    int n = 0;
    try {
      n = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.err.println("Invalid argument. Expecting a number");
      System.exit(-1);
    }

    List<List<Integer>> solutions = new Solver(n).solve();
    prettyPrintSolutions(solutions);
  }

  public static void prettyPrintSolutions(List<List<Integer>> solutions) {
    System.out.printf("Found %d solutions%n", solutions.size());

    for (List<Integer> solution : solutions) {

      StringBuilder solutionStr = new StringBuilder(solution.toString()).append("\n");

      for (int row = 0; row < solution.size(); row++) {
        int col = solution.get(row);

        StringBuilder rowStr = new StringBuilder("_".repeat(solution.size()));
        rowStr.setCharAt(col, 'X');
        solutionStr.append(rowStr).append("\n");
      }

      System.out.println();
      System.out.println(solutionStr);
    }
  }
}
