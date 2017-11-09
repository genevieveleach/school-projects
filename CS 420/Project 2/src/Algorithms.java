import java.util.*;

public class Algorithms {

  private static final double CROSSOVER = 0.5;
  private static final double MUTATIONCHANCE = 0.3;
  private static final int POPULATIONSIZE = 10000;

  public static int steepestHillClimbing(Board board) {
    int cost = 0;
    while( true ) {
      int[] next = board.nextState();
      if (next == null) {
        break;
      }
      board = new Board(next);
      cost++;
    }
    if( board.getPairs() == 0 ){
      System.out.println("Final Positions: " + Arrays.toString(board.getBoard()));
      return cost;
    }
    else {
      return -1;
    }
  }

  public static int genetic(Board male) {
    ArrayList<Board> population = new ArrayList<>();
    HashSet<Integer> existingPopulation = new HashSet<>();

    population.add(male);
    existingPopulation.add(male.hashCode());
    int[] femaleBoard = Arrays.copyOf(male.getBoard(), male.getBoard().length);
    Board female = new Board( mutate(femaleBoard));
    population.add(female);
    existingPopulation.add(female.hashCode());

    while(true) {
      ArrayList<Board> nextGeneration = new ArrayList<>();
      for (int i = 0; i < POPULATIONSIZE; i++) {
        //selection
        Board daddy = population.get((int)(Math.random() * population.size()));
        Board mommy = population.get((int)(Math.random() * population.size()));

        //crossover
        int[] childBoard = procreate(daddy, mommy);

        //mutate
        if (Math.random() < MUTATIONCHANCE) {
          childBoard = mutate(childBoard);
        }
        Board child = new Board(childBoard);
        if (!existingPopulation.contains(child.hashCode())) {
          nextGeneration.add(child);
          existingPopulation.add(child.hashCode());
        }
      }
      population.addAll(nextGeneration);
      population.sort(boardComparator);
      if(population.size() > POPULATIONSIZE) {
        population.subList(POPULATIONSIZE, population.size()).clear();
      }
      Board check = population.get(0);
      if(check.getPairs() == 0) {
        System.out.println("Final Positions: " + Arrays.toString(check.getBoard()));
        return existingPopulation.size();
      }
    }
  }

  private static int[] procreate(Board father, Board mother) {
    int[] fatherBoard = father.getBoard();
    int[] motherBoard = mother.getBoard();
    int[] childBoard = new int[fatherBoard.length];
    System.arraycopy(fatherBoard, 0, childBoard, 0, (int)(CROSSOVER * childBoard.length));
    System.arraycopy(motherBoard, (int)(CROSSOVER * childBoard.length), childBoard, (int)(CROSSOVER * childBoard.length),
        motherBoard.length - (int)(CROSSOVER * childBoard.length));
    return childBoard;
  }

  private static int[] mutate(int[] board) {
    int[] newBoard = new int[board.length];
    System.arraycopy(board, 0, newBoard, 0, board.length);
    newBoard[(int)(Math.random() * board.length)] = (int)(Math.random() * board.length);
    return newBoard;
  }

  public static Comparator<Board> boardComparator = new Comparator<Board>() {
    @Override
    public int compare(Board o1, Board o2) {
      return o1.getPairs() - o2.getPairs();
    }
  };
}
