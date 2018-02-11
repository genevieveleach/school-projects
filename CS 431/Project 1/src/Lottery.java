import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Lottery extends SchedulerAlg{

  private ArrayList<Process> processes = new ArrayList<>();
  private final int TIME_QUANTUM;

  Lottery(ArrayList<Process> processList, int timeQuantum, String fileName) throws IOException {
    for (Process p : processList) {
      processes.add(new Process(p));
    }
    this.TIME_QUANTUM = timeQuantum;
    this.fileName = fileName;
    this.totalProcesses = processList.size();
    writeHeaderToFile(fileName);
  }

  @Override
  void run() throws IOException {
    int prevPid = 0;
    while(!processes.isEmpty()) {
      Process currentProcess = processes.get(ticket());
      if (prevPid != currentProcess.getPid() && prevPid != 0) {
        writeSwitchToFile(fileName, prevPid, currentProcess.getPid(), cpuTime, cpuTime + SWITCH_TIME);
        cpuTime += SWITCH_TIME;
      }
      int startingCPU = cpuTime;
      int startingBurstTime = currentProcess.getBurstTime();
      if ((startingBurstTime - TIME_QUANTUM) <= 0) {
        cpuTime += startingBurstTime;
        currentProcess.setBurstTime(0);
      } else {
        cpuTime += TIME_QUANTUM;
        currentProcess.setBurstTime(startingBurstTime - TIME_QUANTUM);
      }
      if (currentProcess.getBurstTime() == 0) {
        writeDataToFile(fileName, currentProcess.getPid(), startingCPU, cpuTime, startingBurstTime, currentProcess.getBurstTime(), cpuTime);
        writeFinishedProcessToFile(fileName, currentProcess.getPid());
        totalCompletionTime += cpuTime;
        processes.remove(currentProcess);
      } else {
        writeDataToFile(fileName, currentProcess.getPid(), startingCPU, cpuTime, startingBurstTime, currentProcess.getBurstTime(), 0);
      }
      prevPid = currentProcess.getPid();
    }
    writeAverageToFile(fileName, totalCompletionTime/totalProcesses);
  }

  private int ticket() {
    Random rand = new Random();
    int ticket = rand.nextInt(calculateTotalPriority()) + 1;
    System.out.println("Ticket chosen: " + ticket);
    do {
      for (int i = 0; i < processes.size(); i++) {
        ticket -= processes.get(i).getPriority();
        if (ticket <= 0) {
          System.out.println("Process chosen: " + processes.get(i).getPid());
          return i;
        }
      }
    } while (ticket >= 0);
    try {
      throw new Exception("Reached end without choosing a process. Check lottery/ticket method.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }

  private int calculateTotalPriority() {
    int priority = 0;
    for (Process p : processes) {
      priority += p.getPriority();
    }
    return priority;
  }

  @Override
  void writeFinishedProcessToFile(String fileName, int pid) throws IOException {
    System.out.println("Process " + pid + " finished, writing to Lottery-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/Lottery-" + fileName + ".csv", true));
    bw.write(",,,,,,Process " + pid + " finished\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeAverageToFile(String fileName, int avgCompletionTime) throws IOException {
    System.out.println("Processes finished. Writing average completion time to Lottery-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/Lottery-" + fileName + ".csv", true));
    bw.write("\n,,,,,,Average Completion Time: " + avgCompletionTime);
    bw.flush();
    bw.close();
  }

  @Override
  void writeSwitchToFile(String fileName, int prevPid, int newPid, int beginningCpuTime, int endCpuTime) throws IOException {
    System.out.println("Switching processes, writing to Lottery-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/Lottery-" + fileName + ".csv", true));
    bw.write(",,,,,,Switching Processes: " + prevPid + " --> " + newPid + "," + beginningCpuTime + "," + endCpuTime + "\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeDataToFile(String fileName, int pid, int startingCPU, int cpuTime, int burstTime, int endingBurstTime, int completionTime) throws IOException {
    System.out.println("Writing data for process " + pid + " to Lottery-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/Lottery-" + fileName + ".csv", true));
    bw.write(pid+ "," + startingCPU + "," + cpuTime + "," + burstTime + "," + endingBurstTime + "," + completionTime + "\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeHeaderToFile(String fileName) throws IOException {
    System.out.println("Creating output file for Lottery-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/Lottery-" + fileName + ".csv", false));
    bw.write("Process ID,Starting CPU Time,Ending CPU Time,Starting Burst Time,Ending Burst Time,Completion Time,,Starting CPU Time,Ending CPU Time\n");
    bw.flush();
    bw.close();
  }
}
