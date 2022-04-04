package com.os.cpu;

abstract class Scheduler {
    /**Data
     * - nProcess: int
     * - timeline: Double = 0
     * - avgExecutionTime : Double
     * - avgWaitTime: Double
     * - normalization: Double
     */
    //total number of processes
    private int nProcess = 0;
    //to keep track of time
    private Double timeline = 0.0;
    //returns average execution time of all processes OR
    // (total execution) / nProcesses
    private Double avgExecutionTime = 0.0;
    //returns average wait time of all processes OR
    // (total wait) / nProcesses
    private Double avgWaitTime = 0.0;
    // total burst time
    private Double totalBurstTime = 0.0;
    private Double avgBurstTime = 0.0;
    //total time (end - begin) time
    private Double totalTime = 0.0;
    //avg time = ((end - begin) / nProcess) time
    private Double avgTime = 0.0;
    //for normalizing time when it gets out of control
    //private Double normalization = 0.0;


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
    public Scheduler(PCB [] pcb) {
        this.setnProcess(pcb);
        this.setAvgExecutionTime(pcb);
        this.setAvgWaitTime(pcb);
        this.setTotalBurstTime(pcb);
        this.setAverageTime(pcb);
        this.setTotalTime(pcb);
        //this.normalization = 0.0;
    }
    /*
    private static void setTimeline(PCB [] pcb){
        Double tl = 0.0;
        for(PCB i :  pcb) {
            tl = i.getEndTime() - i.getBeginTime();
        }
        timeline = tl;
        //return tl;
    }
     */
    public Double getTimeline(){
        return timeline;
    }
    private void setnProcess(PCB[] pcb) {
        //int nP = 0;
        for(PCB i :  pcb) {
            this.nProcess += 1;
        }
        //return nP;
    }
    public int getnProcess(PCB[] pcb) {
        return nProcess;
    }
    public void setAvgWaitTime(PCB [] pcb){
        Double aWT = 0.0;
        for(PCB i :  pcb) {
            aWT += i.getWaitTime();
        }
        avgWaitTime = aWT / nProcess;
        //return aWT /= nProcess;
    }
    public Double getAvgWaitTime(){
        return avgWaitTime;
    }
    public void setAvgExecutionTime(PCB [] pcb){
        Double aET = 0.0;
        try {
            for (PCB i : pcb) {
                aET += i.getExecuted();
            }
            avgExecutionTime = aET / nProcess;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //return aET /= nProcess;
    }
    public Double getAvgExecutionTime(){
        return avgExecutionTime;
    }
    public void setAvgBurstTime(PCB [] pcb){
        Double aBT = 0.0;
        for(PCB i :  pcb) {
            aBT += i.getExecuted();
        }
        avgBurstTime = aBT / nProcess;
        //return aBT /= nProcess;
    }
    public Double getAvgBurstTime(){
        return avgBurstTime;
    }
    public void setAverageTime(PCB [] pcb){
        //implement...
        //avgWait + avgExe = ave?
        Double aT = avgExecutionTime + avgWaitTime;
        avgTime = aT;
        //System.out.println(aT);
        //return aT;
    }
    public Double getAverageTime(){
        //implement...
        return avgTime;
    }
    public void setTotalBurstTime(PCB [] pcb){
        Double tBT = 0.0;
        for(PCB i :  pcb) {
            tBT += i.getBurstTime();
        }
        totalBurstTime = tBT;
    }
    public Double getTotalBurstTime(){
        return totalBurstTime;
    }
    public void setTotalTime(PCB [] pcb){
        //total wit + execution for all PCB in scheduler
        Double tWT = 0.0;
        Double tET = 0.0;
        for(PCB x : pcb){
            tWT += x.getWaitTime();
            tET += x.getExecuted();
        }
        totalTime = tWT + tET;
    }
    public Double getTotalTime(){
        return totalTime;
    }
    public String indentation(String text, int limit) {
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