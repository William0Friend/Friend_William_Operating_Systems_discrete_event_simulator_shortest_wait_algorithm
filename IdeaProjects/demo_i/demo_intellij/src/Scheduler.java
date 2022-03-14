import java.io.*;
import java.util.*;
import com.github.cliftonlabs.json_simple.*;
import PCB.java
        ;
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
        this.nProcess = getnProcess(PCB [] pcb);
        this.timeline = getTimeline(PCB [] pcb);
        this.avgExecutionTime = getAvgExecutionTime(PCB [] pcb);
        this.avgWaitTime = getAvgWaitTime(PCB [] pcb)l
        //this.normalization = pcb[0];
    }

    public float getAvgWaitTime(PCB [] pcb){
        float aWT = 0;
        for(int x : pcb[x]) {
            aWT += pcb[x].getWaitTime();
        }
        return aWT /= nProcess;
    }
    public float getTimeline(PCB [] pcb){
        float tl = 0;
        for(int x : pcb[x]) {
            tl = pcb[x].getEndTime() - pcb[x].getBeginTime();
        }
        return tl;
    }
    public int getnProcess(PCB[] pcb) {
        int nP = 0;
        for (int x : pcb[x]){
            nP += 1;
        }
        return nP;
    }
    public float getAvgExecutionTime(PCB [] pcb){
        float aET = 0;
        for(int x : pcb[x]) {
            aET += pcb[x].getExecuted();
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
    }
    private String indentation(String text, int limit)
    {
        //implement...
    }
    public void resultTable(){
        //implement...
    }
    public void preemptionTable(int i, int j, float time){
        //implement...
    }
}
