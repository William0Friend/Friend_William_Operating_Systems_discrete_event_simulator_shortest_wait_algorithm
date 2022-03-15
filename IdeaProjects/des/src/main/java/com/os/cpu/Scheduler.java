package com.os.cpu;
import java.util.*;

public class Scheduler {
    /**Data
     * - nProcess: int
     * - timeline: float = 0
     * - avgExecutionTime : float
     * - avgWaitTime: float
     * - normalization: float
     *
     */
    private int nProcess;
    private float timeline;
    private float avgExecutionTime;
    private float avgWaitTime;
    private float normalization = 0;

    //clock
    private int clock = 0;
    private int clockCycles = 0;

    public PCB [] pcb;
    public Queue<PCB> readyQueue = new PriorityQueue<>();
    public Queue<PCB> waitQueue = new PriorityQueue<>();
    /**Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     * ~ preemptionTable(i: int, j: int, time: float): void
     */
    public Scheduler(PCB [] pcb) {
        this.nProcess = 0; //getnProcess();
        this.timeline = 0; //getTimeline();// [] pcb);
        this.avgExecutionTime = 0; //getAvgExecutionTime(PCB [] pcb);
        this.avgWaitTime = 0; //getAvgWaitTime(PCB [] pcb);
        //this.normalization = pcb[0];
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
     * @param pcb
     * @return
     */
    public float getAvgWaitTime(PCB [] pcb){
        float aWT = 0;
        for(PCB i :  pcb) {
            aWT += i.getWaitTime();
        }
        return aWT /= nProcess;
    }
    public float getTimeline(PCB [] pcb){
        float tl = 0;
        for(PCB i :  pcb) {
            tl = i.getEndTime() - i.getBeginTime();
        }
        return tl;
    }
    public int getnProcess(PCB[] pcb) {
        int nP = 0;
        for(PCB i :  pcb) {
            nP += 1;
        }
        return nP;
    }
    public float getAvgExecutionTime(PCB [] pcb){
        float aET = 0;
        for(PCB i :  pcb) {
            aET += i.getExecuted();
        }
        return aET /= nProcess;
    }
    private void normalization(){
        //implement...
        //this.normalization = normalization;
    }
    public void sortPcb(){
        //Sting []
        //implement...
        //call SJF
        //call RR
        //call FCFS
        //
    }
    public void averageTime(){
        //implement...
        //avgWait + avgExe = ave?
        float aT = avgExecutionTime + avgWaitTime;
        System.out.println(aT);
    }
    private String indentation(String text, int limit) {
        //implement...
        return text;
    }
    public void resultTable(){
        //implement...
    }
    public void preemptionTable(int i, int j, float time){
        //implement...
    }
}
