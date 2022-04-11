package com.os.cpu;
import java.util.*;
//import java.util.PriorityQueue;

public class FCFS extends Scheduler{
    /**
     * Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     *
     */
    //data structure to load use loaded PCB array with
    Queue<PCB> arrivalQueue = new LinkedList<>();
    //Queue to hold all processes once processed
    Queue<PCB> readyQueue;

    PCB [] pcbl;

    public FCFS(PCB[] pcb) {
        super(pcb);
        //add all process to arrival queue on creation
        Collections.addAll(arrivalQueue, pcb);
        //create duplicate array
        pcbl = pcb;
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
        System.out.println("FCFS needs no normalization");
    }
    public void sortPcb(){
        //implement...
        //in FCFS the order in which process arrives
        //is the sorted order
        //here arrival queue essentially does not need be sorted.
        // it can just be copied
        readyQueue = arrivalQueue;
    }
    public void run(){
        //implement...
        //Initialized scheduler with 3 processes.
        System.out.println("Initialized scheduler with "+ pcbl.length +" processes.");
        //Policy: Round Robin
        System.out.println("Policy: First Come First Serve ");
        String one, two;
        int max = pcbl.length;
        Double currentBurst = 0.0;
        for(int i = 0; i < pcbl.length; i++) {
            //-> Context Change at time = 9.329
            System.out.println("-> Context Change at time = " + currentBurst);
            if(i == 0){
                one = "start";
                two = pcbl[i].getName();
            }
            else if(i < max)
            {
                one = pcbl[i-1].getName();
                two = pcbl[i].getName();
            }
            else if(i == (max)){
                one = pcbl[max].getName();
                two = "end";
            }
            else{
                one = "start";
                two = pcbl[i].getName();
            }
            System.out.println("---------------------------------------");
            System.out.println("| Exits            | Enters           |");
            System.out.println("---------------------------------------");
            System.out.println("| " + one + "            | " + two + "            | ");
            currentBurst += pcbl[i].getBurstTime();
        }
        System.out.println("-> Context Change at time = " + getTotalBurstTime());
        System.out.println("---------------------------------------");
        System.out.println("| Exits            | Enters           |");
        System.out.println("---------------------------------------");
        System.out.println("| " +  pcbl[max-1].getName() + "            | " + "end" + "            | ");
    }
    public void resultTable(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time | Completion Time | Turn Around Time |");
        System.out.println("---------------------------------------------------------------------------");
        //
        //System.out.println("|"+"Process0"+"        "+"|"+"0.000"+"      "+"|"+"3.767"+"           "+"|"+"0.000"+"         "+"|"+"3.767"+"       "+"|"+"");
        for(PCB x : arrivalQueue) {
            System.out.println("|" + x.getName() + "               " +
                    "| " + x.getWaitTime() + "          " +
                    "| " + x.getExecuted() + "          " +
                    "| " + x.getArrivalTime() + "         " +
                    "| " + x.getExecuted() + "          " +
                    "| " + x.getCompletionTime() + "           " +
                    "| " + x.getTurnAroundTime() + " |");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+"|");
        System.out.println("|Average         "+"|"+this.getAvgWaitTime()+"      |"+this.getAvgExecutionTime()+"           "+"|Total Burst"+"   "+"|"+this.getTotalBurstTime()+"       "+"|");
        System.out.println("---------------------------------------------------------------------------");
    }

    public void setWaitTimes(PCB [] pcb){
        pcb[0].setWaitTime(0.00);
        for(int i=1; i<pcb.length; i++){
            pcb[i].setWaitTime(pcb[i-1].getWaitTime()+pcb[i-1].getBurstTime());
        }
    }
    public void setTurnAroundTimes(PCB [] pcb){
        for(int i=0; i<pcb.length; i++){
            pcb[i].setTurnAroundTime(pcb[i].getBurstTime() + pcb[i].getWaitTime());
        }
    }
    public void setCompletionTimes(PCB [] pcb){
        for(int i=0; i<pcb.length; i++){
            pcb[i].setCompletionTime(pcb[i].getTurnAroundTime() +
                    pcb[i].getArrivalTime());
        }
    }
    @Override
    public void preemptionTable(int i, int j, Double time){
        //implement
        System.out.println("FCFS is none preemptive, so there is no preemptionTable");
    }
    public void ResultTable(){
        //implement...
        //Initialized scheduler with 3 processes.
        System.out.println("Initialized scheduler with "+ pcbl.length + " processes.");
        //Policy: Round Robin
        System.out.println("Policy: First Come First Serve ");
        //-> Context Change at time = 9.329
        //implement...
        //---------------------------------------
        String one, two;
        for(int i = 0; i < pcbl.length; i++) {
            if(pcbl[i - 1] == null ) {
                one = "";
            }
            else {
                one = pcbl[i-1].getName();
            }
            if(pcbl[i] == null) {
                two = "";
            }
            else {
                two = pcbl[i].getName();
            }
            System.out.println("---------------------------------------");
            System.out.println("| Exits            | Enters           |");
            System.out.println("---------------------------------------");
            System.out.println("| " + one + "            | " + two + "            | ");
        }
        //for(PCB x : arrivalQueue)
        //  System.out.println()
        System.out.println("| "+ arrivalQueue.peek().getName() + "            | " + arrivalQueue.poll().getName() + "           |");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time |  Turn Around Time");
        System.out.println("---------------------------------------------------------------------------");
        //
        //System.out.println("|"+"Process0"+"        "+"|"+"0.000"+"      "+"|"+"3.767"+"           "+"|"+"0.000"+"         "+"|"+"3.767"+"       "+"|"+"");
        for(PCB x : arrivalQueue) {
            System.out.println("|" + x.getName() + "        " +
                    "|" + x.getWaitTime() + "      " +
                    "|" + x.getExecuted() + "           " +
                    "| " + x.getArrivalTime() + "         " +
                    "| " + x.getExecuted() + "       " + "| " +
                    x.getArrivalTime() + "| " +
                    x.getArrivalTime() + " | " +
                    x.getBurstTime() + " | " +
                    "       " + "|" +
                    x.getTurnAroundTime() + " |");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+"|");
        System.out.println("|Average         "+"|"+getAvgWaitTime()+"      |"+getAvgExecutionTime()+"           "+"|Total Burst"+"   "+"|"+getTotalBurstTime()+"       "+"|");
        System.out.println("---------------------------------------------------------------------------");
    }

}
