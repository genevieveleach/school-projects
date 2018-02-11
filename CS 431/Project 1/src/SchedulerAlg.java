import java.io.IOException;
import java.util.Queue;

abstract class SchedulerAlg {

  final int SWITCH_TIME = 3;
  int cpuTime = 0;
  int totalCompletionTime;
  int totalProcesses;
  String fileName;
  Queue<Process> processes;

  //FCFS and SJF uses same run(), RR and Lottery override it
  void run() throws IOException {
    int prevPid = 0;
    boolean switchStatement = false;
    while (!processes.isEmpty()) {
      Process currentProcess = processes.poll();
      if(switchStatement) {
        writeSwitchToFile(fileName, prevPid, currentProcess.getPid(), cpuTime, cpuTime+SWITCH_TIME);
        cpuTime += SWITCH_TIME;
      }
      int startingCPU = cpuTime;
      cpuTime += currentProcess.getBurstTime();
      writeDataToFile(fileName, currentProcess.getPid(), startingCPU, cpuTime, currentProcess.getBurstTime(), 0, cpuTime);
      totalCompletionTime += cpuTime;
      writeFinishedProcessToFile(fileName, currentProcess.getPid());
      prevPid = currentProcess.getPid();
      switchStatement = true;
    }
    writeAverageToFile(fileName, totalCompletionTime/totalProcesses);
  }

  abstract void writeFinishedProcessToFile(String fileName, int pid) throws IOException;

  abstract void writeAverageToFile(String fileName, int avgCompletionTime) throws IOException;

  abstract void writeSwitchToFile(String fileName, int prevPid, int newPid, int beginningCpuTime, int endCpuTime) throws IOException;

  abstract void writeDataToFile(String fileName, int pid, int startingCPU, int cpuTime, int burstTime, int endingBurstTime, int completionTime) throws IOException;

  abstract void writeHeaderToFile(String fileName) throws IOException;

}
