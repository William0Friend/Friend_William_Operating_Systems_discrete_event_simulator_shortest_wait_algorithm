package com.os.cpu;

import java.util.ArrayList;

public class SJFPremptive extends Scheduler {
    /**
     * Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     * ~ preemptionTable(i: int, j: int, time: Double): void
     *
     * @param pcb
     */
    //ArrayList<PCB> sjfpQ = new ArrayList<PCB>();
    PCB[] pcb_copy;
    PCB[] pcb_log;
//    Double[] tat;

    public SJFPremptive(PCB[] pcb) {
        super(pcb);
        //pcb_copy = new PCB[pcb.length];
        pcb_copy = pcb;
    }

    public static void findWaitingTime(PCB pcb[], int n, Double wt[]) {
        Double rt[] = new Double[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = pcb[i].getBurstTime();
        int complete = 0, t = 0;
        Double minm = Double.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;
        // Process until all processes gets
        // completed
        while (complete != n) {
            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time
            for (int j = 0; j < n; j++) {
                if ((pcb[j].getArrivalTime() <= t) &&
                        (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }
            if (check == false) {
                t++;
                continue;
            }
            // Reduce remaining time by one
            rt[shortest]--;
            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Double.MAX_VALUE;
            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {
                // Increment complete
                complete++;
                check = false;
                // Find finish time of current
                // process
                finish_time = t + 1;
                // Calculate waiting time
                wt[shortest] = finish_time -
                        pcb[shortest].getBurstTime() -
                        pcb[shortest].getBurstTime();
                if (wt[shortest] < 0)
                    wt[shortest] = 0.0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(PCB[] pcb, int n,
                                   Double[] wt, Double[] tat) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            //will store turn around times in order in whatever array is parameter 4, parameter 4 will be overwritten
            tat[i] = pcb[i].getBurstTime() + wt[i];
    }

    // Method to calculate average time
    public static void findavgTimeAndResultTable(PCB pcb[], int n) {
        Double wt[] = new Double[n], tat[] = new Double[n];
        Double total_wt = 0.0, total_tat = 0.0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(pcb, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(pcb, n, wt, tat);

        // Display processes along with all
        // details
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + pcb[i].getName() + "\t\t"
                    + pcb[i].getBurstTime() + "\t\t " + wt[i]
                    + "\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " +
                total_wt / (float) n);
        System.out.println("Average turn around time = " +
                total_tat / (float) n);
    }
    public void run() {
        //implement...
        //Initialized scheduler with 3 processes.
        System.out.println("Initialized scheduler with " + pcb_copy.length + " processes.");
        //Policy: Round Robin
        System.out.println("Policy: Shortest Job First Preemptive");
        String one = null, two = null;
        int max = pcb_copy.length;
        Double currentBurst = 0.0;
        for (int i = 0; i < max; i++) {
            //-> Context Change at time = 9.329
            System.out.println("-> Context Change at time = " + currentBurst);
            if (i == 0) {
                one = "start";
                two = pcb_copy[i].getName();
            } else if (i > 0 && i < max) {
                one = pcb_copy[i - 1].getName();
                two = pcb_copy[i].getName();
            } else if (i == (max)) {
                one = pcb_copy[max].getName();
                two = "end";
                break;
            }
            System.out.println("---------------------------------------");
            System.out.println("| Exits            | Enters           |");
            System.out.println("---------------------------------------");
            System.out.println("| " + one + "            | " + two + "            | ");
            currentBurst += pcb_copy[i].getBurstTime();
        }
        System.out.println("-> Context Change at time = " + getTotalBurstTime());
        System.out.println("---------------------------------------");
        System.out.println("| Exits            | Enters           |");
        System.out.println("---------------------------------------");
        System.out.println("| " + pcb_copy[max - 1].getName() + "            | " + "end" + "            | ");
    }

    @Override
    public void resultTable() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time | Completion Time | Turn Around Time |");
        System.out.println("---------------------------------------------------------------------------");
        //
        //System.out.println("|"+"Process0"+"        "+"|"+"0.000"+"      "+"|"+"3.767"+"           "+"|"+"0.000"+"         "+"|"+"3.767"+"       "+"|"+"");
        for (int i = 0; i < pcb_copy.length; i++) {
            System.out.println("|" + pcb_copy[i].getName() + "               " +
                    "| " + pcb_copy[i].getWaitTime() + "          " +
                    "| " + pcb_copy[i].getExecuted() + "          " +
                    "| " + pcb_copy[i].getArrivalTime() + "         " +
                    "| " + pcb_copy[i].getExecuted() + "          " +
                    "| " + pcb_copy[i].getCompletionTime() + "           " +
                    "| " + pcb_copy[i].getTurnAroundTime() + " |");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + "|");
        System.out.println("|Average         " + "|" + this.getAvgWaitTime() + "      |" + this.getAvgExecutionTime() + "           " + "|Total Burst" + "   " + "|" + this.getTotalBurstTime() + "       " + "|");
        System.out.println("---------------------------------------------------------------------------");
    }

    public void setUniversalArrivalTime() {
        for (int i = 0; i < pcb_copy.length; i++) {
            pcb_copy[i].setArrivalTime(0.0);
        }
    }

    public void setWaitTimes() {
        pcb_copy[0].setWaitTime(0.00);
        for (int i = 1; i < pcb_copy.length; i++) {
            pcb_copy[i].setWaitTime(pcb_copy[i - 1].getWaitTime() + pcb_copy[i - 1].getBurstTime());
        }
    }

    public void setTurnAroundTimes() {
        for (int i = 0; i < pcb_copy.length; i++) {
            pcb_copy[i].setTurnAroundTime(pcb_copy[i].getBurstTime() + pcb_copy[i].getWaitTime());
        }
    }

    public void setCompletionTimes() {
        for (int i = 0; i < pcb_copy.length; i++) {
            pcb_copy[i].setCompletionTime(pcb_copy[i].getTurnAroundTime() +
                    pcb_copy[i].getArrivalTime());
        }
    }
    public void sortPcbOld() {

        for (int i = 0; i < pcb_copy.length; i++) {
            if (pcb_copy[i].getBurstTime() < pcb_copy[i + 1].getBurstTime()) {
                for (int j = 0; j < pcb_copy.length - 1; j++) {
                    if (pcb_copy[j].getBurstTime() > pcb_copy[j + 1].getBurstTime()) {
                        PCB temp = pcb_copy[j];
                        pcb_copy[j] = pcb_copy[j + 1];
                        pcb_copy[j + 1] = temp;
                    }
                }
            }
        }
    }
    @Override
    void normalization() {
    }

    @Override
    void sortPcb() {
        int clock = 0;
        int n = pcb_copy.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                //clock++;
                //if (pcb_copy[j].getArrivalTime() > clock)
                if (pcb_copy[j].getBurstTime() > pcb_copy[j + 1].getBurstTime()) {
                    // swap temp and arr[i]
                    PCB temp = pcb_copy[j];
                    pcb_copy[j] = pcb_copy[j + 1];
                    pcb_copy[j + 1] = temp;
                }
            }
        }
        return;
    }

    public boolean ascending(PCB arr[]) {
        boolean token = false;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getBurstTime() < arr[i + 1].getBurstTime()) {
                token = true;
            }
        }
        return token;
    }

    /*
    public void SortPcb(PCB[] pcb) {
        PCB leastPCB = pcb[0];
        for(int i = 0; i < pcb.length; i++){
            if((pcb[i].getBurstTime() <= leastPCB.getBurstTime())){

            }
            else{
                leastPCB = pcb[i];
            }
            readyQueue.add();
        }
    }
*/

    @Override
    void preemptionTable(int i, int j, Double time) {

    }

    public void setValues() {
        setAvgWaitTime(pcb_copy);

        setUniversalArrivalTime();

        //set values
        setnProcess(pcb_copy);

        setTotalTime(pcb_copy);

        setTotalBurstTime(pcb_copy);

        setWaitTimes();

        setTurnAroundTimes();

        setCompletionTimes();

        //set Averages
        setAvgExecutionTime(pcb_copy);

        setAvgBurstTime(pcb_copy);

        setAvgWaitTime(pcb_copy);

        setAverageTime(pcb_copy);

    }

    public String indentation(String text, int limit) {
        //implement...
        return text;
    }

}
// Java program to implement Shortest Remaining Time First
// Shortest Remaining Time First (SRTF)
/*
class Process
{
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time

    public Process(int pid, int bt, int art)
    {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}

public class GFG
{
    // Method to find the waiting time for all
    // processes
//took out here
    // Driver Method
    public static void main(String[] args)
    {
       Process proc[] = { new Process(1, 6, 1),
                new Process(2, 8, 1),
                new Process(3, 7, 2),
                new Process(4, 3, 3)};

        findavgTime(proc, proc.length);
    }
}

 */