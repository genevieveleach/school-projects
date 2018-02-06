import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class RoundRobin extends SchedulerAlg {

  private Queue<Process> processes = new LinkedList<>();
  private final int TIME_QUANTUM;

  RoundRobin(ArrayList<Process> processList, int timeQuantum, String fileName) {
    processes.addAll(processList);
    this.TIME_QUANTUM = timeQuantum;
    this.fileName = fileName;
    this.totalProcesses = processList.size();
  }

  void run() throws IOException {
    int prevPid = 0;
    boolean switchStatement = false;
    while(!processes.isEmpty()) {
      Process currentProcess = processes.poll();
      if(switchStatement) {
        writeSwitchToFile(fileName, prevPid, currentProcess.getPid(), cpuTime, cpuTime+SWITCH_TIME);
        cpuTime += SWITCH_TIME;
      }
      int startingCPU = cpuTime;
      int startingBurstTime = currentProcess.getBurstTime();
      if((startingBurstTime - TIME_QUANTUM) <= 0) {
        
      } else {

      }
    }
  }

  @Override
  void writeFinishedProcessToFile(String fileName, int pid) throws IOException {
    System.out.println("Process " + pid + " finished, writing to RR" + TIME_QUANTUM + "-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/RR" + TIME_QUANTUM + "-" + ".csv", true));
    bw.write(",,,,,,Process " + pid + " finished\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeAverageToFile(String fileName, int avgCompletionTime) throws IOException {
    System.out.println("Processes finished. Writing average completion time to RR" + TIME_QUANTUM + "-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/RR" + TIME_QUANTUM + "-" + ".csv", true));
    bw.write("\n,,,,,,Average Completion Time: " + avgCompletionTime);
    bw.flush();
    bw.close();
  }

  @Override
  void writeSwitchToFile(String fileName, int prevPid, int newPid, int beginningCpuTime, int endCpuTime) throws IOException {
    System.out.println("Switching processes, writing to RR" + TIME_QUANTUM + "-" + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/RR" + TIME_QUANTUM + "-" + ".csv", true));
    bw.write(",,,,,,Switching Processes: " + prevPid + " --> " + newPid + "," + beginningCpuTime + "," + endCpuTime + "\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeDataToFile(String fileName, int pid, int startingCPU, int cpuTime, int burstTime, int endingBurstTime, int completionTime) throws IOException {
    System.out.println("Writing data for process " + pid + " to RR" + TIME_QUANTUM + "-" + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/RR" + TIME_QUANTUM + "-" + ".csv", true));
    bw.write(pid+ "," + startingCPU + "," + cpuTime + "," + burstTime + "," + endingBurstTime + "," + completionTime + "\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeHeaderToFile(String fileName) throws IOException {
    System.out.println("Creating output file for SJF-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/RR" + TIME_QUANTUM + "-" + ".csv", false));
    bw.write("Process ID,Starting CPU Time,Ending CPU Time,Starting Burst Time,Ending Burst Time,Completion Time,,Starting CPU Time,Ending CPU Time\n");
    bw.flush();
    bw.close();
  }

}
