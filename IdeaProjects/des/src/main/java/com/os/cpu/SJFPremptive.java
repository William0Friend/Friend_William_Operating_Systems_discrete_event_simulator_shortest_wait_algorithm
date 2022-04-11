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
    ArrayList<PCB> sjfpQ = new ArrayList<PCB>();
    PCB[] pcb_copy;
    Double [] tat;
    public SJFPremptive(PCB[] pcb) {
        super(pcb);
        pcb_copy = new PCB[pcb.length];
        pcb_copy = pcb;
    }
    public static void findWaitingTime(PCB pcb[], int n,
                                       Double wt[])
    {
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
            // current time`
            for (int j = 0; j < n; j++)
            {
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
                                   Double[] wt, Double[] tat)
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            //will store turn around times in order in whatever array is parameter 4, parameter 4 will be overwritten
            tat[i] = pcb[i].getBurstTime() + wt[i];
    }

    // Method to calculate average time
    public static void findavgTimeAndResultTable(PCB pcb[], int n)
    {
        Double wt[] = new Double[n], tat[] = new Double[n];
        Double  total_wt = 0.0, total_tat = 0.0;

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
                total_wt / (float)n);
        System.out.println("Average turn around time = " +
                total_tat / (float)n);
    }

    @Override
    void normalization() {

    }

    @Override
    void sortPcb() {

    }

    @Override
    void resultTable() {

    }

    @Override
    void preemptionTable(int i, int j, Double time) {

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