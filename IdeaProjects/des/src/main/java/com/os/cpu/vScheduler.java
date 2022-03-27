package com.os.cpu;

public class vScheduler {

    /**Data
     * - nProcess: int
     * - timeline: Double = 0
     * - avgExecutionTime : Double
     * - avgWaitTime: Double
     * - normalization: Double
     *
     */
    //total number of processes
    private static int nProcess;
    //to keep track of time
    private static Double timeline;
    //returns average execution time of all processes OR
    // (total execution) / nProcesses
    private static Double avgExecutionTime;
    //returns average wait time of all processes OR
    // (total wait) / nProcesses
    private static Double avgWaitTime;
    // total burst time
    private static Double totalBurstTime;
    private static Double avgBurstTime;
    //total time (end - begin) time
    private static Double totalTime;
    //avg time = ((end - begin) / nProcess) time
    private static  Double avgTime;
    //for normalizing time when it gets out of control
    private Double normalization = 0.0;


    //clock
    private int clock = 0;
    private int clockCycles = 0;
    /**Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     * ~ preemptionTable(i: int, j: int, time: Double): void
     */
    /**
     * @param pcb
     * @return
     */
    public vScheduler(PCB... pcb);
    private static void setTimeline(PCB [] pcb){
        //Double tl = 0.0;
        for(PCB i :  pcb) {
            timeline = i.getEndTime() - i.getBeginTime();
        }
        //return tl;
    }
    public Double getTimeline(){
        return timeline;
    }
    private static void setnProcess(PCB[] pcb) {
        //int nP = 0;
        for(PCB i :  pcb) {
            nProcess += 1;
        }
        //return nP;
    }
    private static int getnProcess(PCB[] pcb) {
        return nProcess;
    }
    private static void setAvgWaitTime(PCB [] pcb){
        Double aWT = 0.0;
        for(PCB i :  pcb) {
            aWT += i.getWaitTime();
        }
        avgWaitTime = aWT / nProcess;
        //return aWT /= nProcess;
    }
    private static Double getAvgWaitTime(PCB [] pcb){
        return avgWaitTime;
    }
    private static void setAvgExecutionTime(PCB [] pcb){
        Double aET = 0.0;
        for(PCB i :  pcb) {
            aET += i.getExecuted();
        }
        avgExecutionTime = aET / nProcess;
        //return aET /= nProcess;
    }
    abstract private static Double getAvgExecutionTime();
    private static void setAvgBurstTime(PCB [] pcb){
        Double aBT = 0.0;
        for(PCB i :  pcb) {
            aBT += i.getExecuted();
        }
        avgBurstTime = aBT / nProcess;
        //return aBT /= nProcess;
    }
    private static Double getAvgBurstTime(){
        return avgBurstTime;
    }
    private static void setAverageTime(PCB [] pcb){
        //implement...
        //avgWait + avgExe = ave?
        Double aT = avgExecutionTime + avgWaitTime;
        avgTime = aT;
        //System.out.println(aT);
        //return aT;
    }
    private static Double getAverageTime(){
        //implement...
        return avgTime;
    }
    private static void setTotalBurstTime(PCB [] pcb){
        Double tBT = 0.0;
        for(PCB i :  pcb) {
            tBT += i.getBurstTime();
        }
        totalBurstTime = tBT;
    }
    private static Double getTotalBurstTime(){
        return totalBurstTime;
    }
    private static void setTotalTime(PCB [] pcb){
        //total wit + execution for all PCB in scheduler
        Double tWT = 0.0;
        Double tET = 0.0;
        for(PCB x : pcb){
            tWT += x.getWaitTime();
            tET += x.getExecuted();
        }
        totalTime = tWT + tET;
    }
    private static Double getTotalTime(){
        return totalTime;
    }
    private static String indentation(String text, int limit) {
        //implement...
        return text;
    }
    abstract void normalization();
    //implement...
    //this.normalization = normalization;
    abstract void sortPcb();
    //implement...
    abstract void resultTable();
    //implement...
    abstract void preemptionTable(int i, int j, Double time);
    //implement...
}
