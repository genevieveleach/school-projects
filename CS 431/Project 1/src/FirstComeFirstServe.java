import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

class FirstComeFirstServe extends SchedulerAlg {

    FirstComeFirstServe(ArrayList<Process> list, String fileName) throws IOException {
    processes = new LinkedList<>();
    processes.addAll(list);
    this.fileName = fileName;
    writeHeaderToFile(fileName);
    this.totalProcesses = list.size();
  }

  @Override
  void writeFinishedProcessToFile(String fileName, int pid) throws IOException {
    System.out.println("Process " + pid + " finished, writing to FCFS-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/FCFS-" + fileName + ".csv", true));
    bw.write(",,,,,,Process " + pid + " finished\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeAverageToFile(String fileName, int avgCompletionTime) throws IOException {
    System.out.println("Processes finished. Writing average completion time to FCFS-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/FCFS-" + fileName + ".csv", true));
    bw.write("\n,,,,,,Average Completion Time: " + avgCompletionTime);
    bw.flush();
    bw.close();
  }

  @Override
  void writeSwitchToFile(String fileName, int prevPid, int newPid, int beginningCpuTime, int endCpuTime) throws IOException {
    System.out.println("Switching processes, writing to FCFS-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/FCFS-" + fileName + ".csv", true));
    bw.write(",,,,,,Switching Processes: " + prevPid + " --> " + newPid + "," + beginningCpuTime + "," + endCpuTime + "\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeDataToFile(String fileName, int pid, int startingCPU, int cpuTime, int burstTime, int endingBurstTime, int completionTime) throws IOException {
    System.out.println("Writing data for process " + pid + " to FCFS-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/FCFS-" + fileName + ".csv", true));
    bw.write(pid+ "," + startingCPU + "," + cpuTime + "," + burstTime + "," + endingBurstTime + "," + completionTime + "\n");
    bw.flush();
    bw.close();
  }

  @Override
  void writeHeaderToFile(String fileName) throws IOException {
    System.out.println("Creating output file for FCFS-" + fileName + ".csv");
    BufferedWriter bw = new BufferedWriter(new FileWriter("./output/FCFS-" + fileName + ".csv", false));
    bw.write("Process ID,Starting CPU Time,Ending CPU Time,Starting Burst Time,Ending Burst Time,Completion Time,,Starting CPU Time,Ending CPU Time\n");
    bw.flush();
    bw.close();
  }

}
