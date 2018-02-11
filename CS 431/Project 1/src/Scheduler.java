import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scheduler {

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println("Invalid args; no test file name(s) provided.");
      System.exit(1);
    }

    for (String fileName : args) {
      System.out.println("\nUsing file: " + fileName);
      ArrayList<Process> processList = readFile(fileName);

      if (processList == null) {
        continue;
      }

      System.out.println("\nRunning First Come First Serve...");
      FirstComeFirstServe fcfs = new FirstComeFirstServe(processList, fileName);
      fcfs.run();

      System.out.println("First Come First Serve finished.");

      System.out.println("\nRunning Shortest Job First...");
      ShortestJobFirst sjf = new ShortestJobFirst(processList, fileName);
      sjf.run();

      System.out.println("Shortest Job First finished.");

      System.out.println("\nRunning Round Robin 25...");
      RoundRobin rr25 = new RoundRobin(processList, 25, fileName);
      rr25.run();

      System.out.println("Round Robin 25 finished.");

      System.out.println("\nRunning Round Robin 50...");
      RoundRobin rr50 = new RoundRobin(processList, 50, fileName);
      rr50.run();

      System.out.println("Round Robin 50 finished.");

      System.out.println("\nRunning Lottery...");
      Lottery lottery = new Lottery(processList, 50, fileName);
      lottery.run();

      System.out.println("Lottery finished.");
    }
    System.out.println("All files finished.");
  }

  private static ArrayList<Process> readFile(String fileName) throws IOException {
    ArrayList<Process> processList = new ArrayList<>();
    File file = new File("./resources/" + fileName);
    if(!file.exists()) {
      System.out.println("File " + fileName + " does not exist.");
      return null;
    }
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;
    System.out.println("Reading file: " + fileName);
    while((line = br.readLine()) != null) {
      int tempPid = Integer.parseInt(line);
      line = br.readLine();
      int tempBurstTime = Integer.parseInt(line);
      line = br.readLine();
      int tempPriority = Integer.parseInt(line);
      processList.add(new Process(tempPid, tempBurstTime, tempPriority));
    }
    System.out.println("File " + fileName + " read.");
    return processList;
  }
}
