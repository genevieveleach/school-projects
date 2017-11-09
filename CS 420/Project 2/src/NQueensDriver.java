public class NQueensDriver {

  private static final int NUMPROBLEMS = 1000;
  private static final int BOARDSIZE = 21;

  public static void main(String[] args) {
    System.out.println("This is the random N-Queens solver!");
    System.out.println("Randomly generating " + NUMPROBLEMS + " boards of size " + BOARDSIZE + " to solve.");
    Board[] problems = new Board[NUMPROBLEMS];
    for (int i = 0; i < NUMPROBLEMS; i++) {
      problems[i] = new Board(BOARDSIZE);
    }
    System.out.println();

    System.out.println("Solving using Steepest Hill Climbing Algorithm.");
    int solved = 0;
    int totalCost = 0;
    long totalTime = 0;
    for (Board b : problems) {
      long start = System.nanoTime();
      int cost = Algorithms.steepestHillClimbing(b);
      long end = System.nanoTime();
      long runTime = end - start;
      if(cost > 0) {
        totalCost += cost;
        totalTime += runTime;
        solved++;
        System.out.println("Solved. \tMoves Used: " + cost + " \tTime: " + runTime + " ns");
      }
      else {
        //System.out.println("Not solved.");
      }
    }
    if (solved > 0) {
      System.out.println("Problems solved: " + solved + "/" + NUMPROBLEMS + " \tAverage Cost: " +
          totalCost/solved + " \tAverage Time: " + totalTime/solved + " ns");
    } else {
      System.out.println("No problems solved out of " + NUMPROBLEMS + ".");
    }
    System.out.println();

    System.out.println("Solving using Genetic Algorithm.");
    solved = 0;
    totalCost = 0;
    totalTime = 0;
    for (Board b : problems) {
      long start = System.nanoTime();
      int cost = Algorithms.genetic(b);
      long end = System.nanoTime();
      long runTime = end - start;
      if(cost > 0) {
        totalCost += cost;
        totalTime += runTime;
        solved++;
        System.out.println("Solved. \tChildren generated: " + cost + " \tTime: " + runTime + " ns");
      }
      else {
        //System.out.println("Not solved.");
      }
    }
    if (solved > 0) {
      System.out.println("Problems solved: " + solved + "/" + NUMPROBLEMS + " \tAverage Cost: " +
          totalCost/solved + " \tAverage Time: " + totalTime/solved + " ns");
    } else {
      System.out.println("No problems solved out of " + NUMPROBLEMS + ".");
    }
  }
}
