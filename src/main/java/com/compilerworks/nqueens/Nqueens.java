package com.compilerworks.nqueens;

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
  }
}
