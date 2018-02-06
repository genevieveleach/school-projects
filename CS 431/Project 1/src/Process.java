class Process implements Comparable<Process> {
  private int pid;
  private int burstTime;
  private int priority;

  Process(int pid, int burstTime, int priority) {
    this.pid = pid;
    this.burstTime = burstTime;
    this.priority = priority;
  }

  void setPid(int pid) {
    this.pid = pid;
  }

  void setBurstTime(int burstTime) {
    this.burstTime = burstTime;
  }

  void setPriority(int priority) {
    this.priority = priority;
  }

  int getPid() {
    return this.pid;
  }

  int getBurstTime() {
    return this.burstTime;
  }

  int getPriority() {
    return this.priority;
  }

  @Override
  public int compareTo(Process that) {
    return Integer.compare(this.burstTime, that.burstTime);
  }
}
