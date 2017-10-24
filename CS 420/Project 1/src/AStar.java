import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class AStar {

  private static final boolean TESTING = true;
  private int nodesGenerated;

  public HashMap<Integer, Integer> aStarSearch(Board board) throws Exception {
    PriorityQueue<Board> frontier = new PriorityQueue<>();
    HashSet<Board> exploredSet = new HashSet<>();
    HashMap<Integer, Integer> depthNodeMap = new HashMap<>();

    long start = System.nanoTime();
    nodesGenerated = 0;

    frontier.add(board);

    while (!frontier.isEmpty()) {
      Board b = frontier.poll();

      if(b.isGoalState()) {
        long end = System.nanoTime() - start;
        if(!TESTING) {
          b.printStates();
        }
        int heuristic = b.getHeuristic();
        if (heuristic == 1) {
          System.out.println("Heuristic: Misplaced Tiles");
        } else if (heuristic == 2) {
          System.out.println("Heuristic: Manhattan Distance");
        }
        System.out.println("Elapsed time: " + end + " ns");
        System.out.println("Nodes Generated: " + nodesGenerated);
        System.out.println("Steps: " + b.getGn());
        depthNodeMap.put(b.getGn(), nodesGenerated);
        if(TESTING) {
          printTimeToFile(b.getGn(), end);
        }
        return depthNodeMap;
      }

      ArrayList<Board> successors = b.getSuccessors();
      for (Board temp : successors) {
        if (!exploredSet.contains(temp)) {
          frontier.add(temp);
          nodesGenerated++;
        }
      }

      exploredSet.add(b);
    }

    throw new Exception("Puzzle reached end of frontier without finding the goal state. Is your program correct?");
  }


  private static void printTimeToFile(int depth, long time) throws Exception {
    try {
      FileWriter f = new FileWriter(new File("output.csv"),true);
      f.write(depth + "," + time + "\n");
      f.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
