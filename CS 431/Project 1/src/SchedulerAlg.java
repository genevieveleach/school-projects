import java.io.IOException;

abstract class SchedulerAlg {

  final int SWITCH_TIME = 3;
  int cpuTime = 0;
  int totalCompletionTime;
  int totalProcesses;
  String fileName;

  abstract void run() throws IOException;

  abstract void writeFinishedProcessToFile(String fileName, int pid) throws IOException;

  abstract void writeAverageToFile(String fileName, int avgCompletionTime) throws IOException;

  abstract void writeSwitchToFile(String fileName, int prevPid, int newPid, int beginningCpuTime, int endCpuTime) throws IOException;

  abstract void writeDataToFile(String fileName, int pid, int startingCPU, int cpuTime, int burstTime, int endingBurstTime, int completionTime) throws IOException;

  abstract void writeHeaderToFile(String fileName) throws IOException;

}
