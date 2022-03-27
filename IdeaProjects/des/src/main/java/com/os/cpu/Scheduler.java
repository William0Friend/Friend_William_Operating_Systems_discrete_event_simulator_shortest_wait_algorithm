package com.os.cpu;

abstract class Scheduler {
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
    public Scheduler(PCB [] pcb) {
        //this.nProcess = 0;
        //this.nProcess = setnProcess(pcb);
        setnProcess(pcb);
        //this.timeline = 0.0;
        //this.timeline = setTimeline(pcb);
        setTimeline(pcb);
        //this.avgExecutionTime = 0.0;
        //this.avgExecutionTime = setAvgExecutionTime(pcb);
        setAvgExecutionTime(pcb);
        //this.avgWaitTime = 0.0;
        //this.avgWaitTime = setAvgWaitTime(pcb);
        setAvgWaitTime(pcb);
        //this.totalBurstTime = setTotalBurstTime(pcb);
        setTotalBurstTime(pcb);
        //this.averageTime = setAverageTime();
        setAverageTime(pcb);
        //this.totalTime = setTotalTime();
        setTotalTime(pcb);
        //normalization still needs implementation
        this.normalization = 0.0;
    }
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
    private static Double getAvgExecutionTime(){
        return avgExecutionTime;
    }
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
/**
     * void cancelAll(Collection<TimerTask> c) {
        *     for (Iterator<TimerTask> i = c.iterator(); i.hasNext(); )
        *         i.next().cancel();
        * }
        *
        * void cancelAll(Collection<TimerTask> c) {
        *     for (TimerTask t : c)
        *         t.cancel();
        * }
        *
        * List suits = ...;
        * List ranks = ...;
        * List sortedDeck = new ArrayList();
        *
        * // BROKEN - throws NoSuchElementException!
        * for (Iterator i = suits.iterator(); i.hasNext(); )
        *     for (Iterator j = ranks.iterator(); j.hasNext(); )
        *         sortedDeck.add(new Card(i.next(), j.next()));
        *
        * // Fixed, though a bit ugly
        * for (Iterator i = suits.iterator(); i.hasNext(); ) {
        *     Suit suit = (Suit) i.next();
        *     for (Iterator j = ranks.iterator(); j.hasNext(); )
        *         sortedDeck.add(new Card(suit, j.next()));
        * }
        *
        * for (Suit suit : suits)
        *     for (Rank rank : ranks)
        *         sortedDeck.add(new Card(suit, rank));
        *
        * >// Returns the sum of the elements of a>
        * int sum(int[] a) {
        *     int result = 0;
        *     for (int i : a)
        *         result += i;
        *     return result;
        * }
*/