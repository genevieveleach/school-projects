import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

  private int nodesGenerated;
  private int steps;
  public void aStarSearch(Board board) {
    PriorityQueue<Board> frontier = new PriorityQueue<>();
    HashSet<Board> exploredSet = new HashSet<>();
    long start = System.nanoTime();
    nodesGenerated = 0;
    steps = 0;
    frontier.add(board);
    while (!frontier.isEmpty()) {
      steps++;
      Board b = frontier.poll();

      if(b.isGoalState()) {
        long end = System.nanoTime() - start;

        System.out.println("Elapsed time: " + end * Math.pow(10, 9) + " s");
      }

      exploredSet.add(b);

      ArrayList<Board> successors = b.getSuccessors();
      nodesGenerated += successors.size();
      for (Board temp:successors) {
        nodesGenerated++;
        if (!exploredSet.contains(temp)) {
          frontier.add(temp);
        }
      }

    }
  }

  public int getNodesGenerated() {
    return nodesGenerated;
  }

  public int getSteps() {
    return steps;
  }
}
