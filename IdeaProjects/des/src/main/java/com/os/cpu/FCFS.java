package com.os.cpu;
import java.util.*;
//import java.util.PriorityQueue;

public abstract class FCFS extends Scheduler{
    /**
     * Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     *
     * @param pcb
     */
    //data structure to load use loaded PCB array with
    Queue<PCB> arrivalQueue = new LinkedList<PCB>();
    //Queue to hold all processes once processed
    Queue<PCB> readyQueue = new LinkedList<PCB>();

    public FCFS(PCB[] pcb) {
        super(pcb);
        //add all process to arrival queue on creation
        for(PCB p : pcb) {
            arrivalQueue.add(p);
        }
        //Since FCFS ready queue and arrival queue are identical in this instance
        readyQueue = arrivalQueue;
    }

    /**Methods
     * ~ FCFS(pcb: PCB[])
     * ~ calcAllFcfs(): void
     * ~ run(): void
     */

    public void normalization() {
        //implement...
        //this.normalization = normalization;
    }
    public void sortPcb(){
        //implement...
    }

    public void resultTable(){
        //implement...
        //Initialized scheduler with 3 processes.
        System.out.println("Initialized scheduler with 3 processes.");
        //Policy: Round Robin
        System.out.println("Policy: First Come First Serve ");
        //-> Context Change at time = 9.329
        //implement...
        //---------------------------------------
        System.out.println("---------------------------------------");
        System.out.println("| Exits            | Enters           |");
        System.out.println("---------------------------------------");
        //for(PCB x : arrivalQueue)
          //  System.out.println()
        System.out.println("| "+ arrivalQueue.peek().getName() + "            | " + arrivalQueue.poll().getName() + "           |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time |");
        System.out.println("---------------------------------------------------------------------------");
        //
        //System.out.println("|"+"Process0"+"        "+"|"+"0.000"+"      "+"|"+"3.767"+"           "+"|"+"0.000"+"         "+"|"+"3.767"+"       "+"|"+"");
        for(PCB x : arrivalQueue) {
            System.out.println("|" + x.getName() + "        " + "|" + x.getWaitTime() + "      " + "|" + x.getExecuted() + "           " + "|" + x.getArrivalTime() + "         " + "|" + x.getBurstTime() + "       " + "|" + "");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+"|");
        System.out.println("|Average         "+"|"+getAvgWaitTime()+"      |"++"           "+"|Total Burst"+"   "+"|"+"9.502"+"       "+"|");
                ---------------------------------------------------------------------------
    }
    abstract void preemptionTable(int i, int j, Double time);//{//implement...}
}
