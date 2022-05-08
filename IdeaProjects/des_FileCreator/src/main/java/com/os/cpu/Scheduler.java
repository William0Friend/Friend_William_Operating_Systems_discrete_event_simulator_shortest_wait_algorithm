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
    private int nProcess;
    //to keep track of time
    private Double timeline = 0.0;
    //returns average execution time of all processes OR
    // (total execution) / nProcesses
    private Double avgExecutionTime;
    //returns average wait time of all processes OR
    // (total wait) / nProcesses
    private Double avgWaitTime;
    // total burst time
    private Double totalBurstTime;
    private Double avgBurstTime;
    //total time (end - begin) time
    private Double totalTime;
    //avg time = ((end - begin) / nProcess) time
    private Double avgTime;
    //for normalizing time when it gets out of control
    //private Double normalization = 0.0;

    private Double avgTurn;
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
        this.nProcess = pcb.length;
        this.avgExecutionTime = 0.0;
        this.avgWaitTime = 0.0;
        this.totalBurstTime = 0.0;
        this.avgTime = 0.0;
        this.totalTime = 0.0;
        this.avgTurn = 0.0;
    }
    /*
    public Scheduler(PCB [] pcb) {
        this.setnProcess(pcb);
        //this.nProcess = pcb.length;
        this.setAvgExecutionTime(pcb);
        this.avgExecutionTime = 0.0;
        this.setAvgWaitTime(pcb);
        this.setTotalBurstTime(pcb);
        this.setAverageTime(pcb);
        this.setTotalTime(pcb);
        //this.normalization = 0.0;
    }

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
        return this.timeline;
    }
    public void setnProcess(PCB[] pcb) {
        //int nP = 0;
        for(int i = 0; i < pcb.length; i++) {
            this.nProcess += 1;
        }
        //return nP;
    }
    public int getnProcess(PCB[] pcb) {
        return this.nProcess;
    }
    public void setAvgWaitTime(PCB [] pcb){
        Double aWT = 0.0;
        for(int i = 0; i < pcb.length; i++) {
            aWT = pcb[i].getWaitTime() + aWT;
        }
        this.avgWaitTime = aWT / this.nProcess;
        //return aWT /= nProcess;
    }
    public Double getAvgWaitTime(){
        return this.avgWaitTime;

    }
    public void setAvgTurn(PCB [] pcb){
        Double aT = 0.0;
        for(int i = 0; i < pcb.length; i++) {
            aT = pcb[i].getTurnAroundTime() + aT;
        }
        this.avgTurn = aT / this.nProcess;
        //return aWT /= nProcess;
    }

    public Double getAvgTurn(){
        return avgTurn;
    }

    public void setAvgExecutionTime(PCB [] pcb){
            //Double aET = null;
            for (int i = 0; i < pcb.length; i++) {
                //aET += i.getExecuted();
                this.avgExecutionTime += pcb[i].getExecuted();
            }
            if(this.avgExecutionTime != 0 && this.nProcess != 0) {
                this.avgExecutionTime = this.avgExecutionTime / this.nProcess;
            }

        //return aET /= nProcess;
    }

    public Double getAvgExecutionTime(){
        return this.avgExecutionTime;
    }
    public void setAvgBurstTime(PCB [] pcb){
        Double aBT = 0.0;
        for(int i = 0; i < pcb.length; i++) {
            aBT += pcb[i].getExecuted();
        }
        this.avgBurstTime = aBT / this.nProcess;
        //return aBT /= nProcess;
    }
    public Double getAvgBurstTime(){
        return this.avgBurstTime;
    }
    public void setAverageTime(PCB [] pcb){
        //implement...
        //avgWait + avgExe = ave?
        Double aT = this.avgExecutionTime + this.avgWaitTime;
        this.avgTime = aT;
        //System.out.println(aT);
        //return aT;
    }
    public Double getAverageTime(){
        //implement...
        return this.avgTime;
    }
    public void setTotalBurstTime(PCB [] pcb){
        Double tBT = 0.0;
        for(int i = 0; i < pcb.length; i++) {
            tBT += pcb[i].getBurstTime();
        }
        this.totalBurstTime = tBT;
    }
    public Double getTotalBurstTime(){
        return this.totalBurstTime;
    }
    public void setTotalTime(PCB [] pcb){
        //total wit + execution for all PCB in scheduler
        Double tWT = 0.0;
        Double tET = 0.0;
        for(int i = 0; i < pcb.length; i++){
            tWT += pcb[i].getWaitTime();
            tET += pcb[i].getExecuted();
        }
        this.totalTime = tWT + tET;
    }
    public Double getTotalTime(){
        return this.totalTime;
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