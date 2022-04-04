package com.os.cpu;

public interface iScheduler {

    /**Data
     * - nProcess: int
     * - timeline: Double = 0
     * - avgExecutionTime : Double
     * - avgWaitTime: Double
     * - normalization: Double
     *
     */
    //total number of processes
    static int nProcess = 0;
    //to keep track of time
    static Double timeline = 0.0;
    //returns average execution time of all processes OR
    // (total execution) / nProcesses
    static Double avgExecutionTime = 0.0;
    //returns average wait time of all processes OR
    // (total wait) / nProcesses
    static Double avgWaitTime = 0.0;
    // total burst time
    static Double totalBurstTime = 0.0;
    static Double avgBurstTime = 0.0;
    //total time (end - begin) time
    static Double totalTime = null;
    //avg time = ((end - begin) / nProcess) time
    static  Double avgTime = null;
    //for normalizing time when it gets out of control
    Double normalization = 0.0;

    //clock
    int clock = 0;
    int clockCycles = 0;

    /*Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     * ~ preemptionTable(i: int, j: int, time: Double): void
     */
    private static void setTimeline(PCB[] pcb) {
        System.out.println("setTimeLine");
    }

    Double getTimeline();
    void setnProcess(PCB[] pcb);
    int getnProcess(PCB[] pcb);
    void setAvgWaitTime(PCB [] pcb);

    public void getAvgWaitTime();
    private static void setAvgExecutionTime(PCB [] pcb){

    }
    public Double getAvgExecutionTime();
    private static void setAvgBurstTime(PCB [] pcb){

    }
    public Double getAvgBurstTime();
    private static void setAverageTime(PCB [] pcb){

    }
    public Double getAverageTime();
    private static void setTotalBurstTime(PCB [] pcb){

    }
    public Double getTotalBurstTime();
    private static void setTotalTime(PCB [] pcb){

    }
    Double getTotalTime();
    String indentation(String text, int limit);
    void normalization();
    //implement...
    //this.normalization = normalization;
    void sortPcb();
    //implement...
    void resultTable();
    //implement...
    void preemptionTable(int i, int j, Double time);
    //implement...
}
