package com.os.cpu;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class RR2 extends Scheduler {
    // round robin needs a quantum
    private static final int quantum = 2;
    int tempQuantum = quantum;
    /*
     * Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     * ~ preemptionTable(i: int, j: int, time: Double): void
     *
     */
    Double[] arrivalD;
    Double[] burstD;
    Double[] waitD;
    Double[] turnD;
    Double[] temp_burstD;
    String[] nameD;
    int[] queueD;
    Double averageWait;
    Double averageTurnAroundTime;
    ArrayList<Integer> rrQ = new ArrayList<>();
    ArrayList<Double> rrT = new ArrayList<>();
    private Double totalBurstTime;
    boolean completed = false;
    File f = new File("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/RR_demo.txt");
    FileWriter w = new FileWriter(f);
    ArrayList<PCB> doneQ = new ArrayList<>();
    private static Scanner inp = new Scanner(System.in);
    PCB[] pcb_copy;

    public RR2(PCB[] pcb) throws IOException {
        super(pcb);
        //Double timer = 0.0;
        //arrival = pcb;
        temp_burstD = new Double[pcb.length];
        arrivalD = new Double[pcb.length];
        burstD = new Double[pcb.length];
        waitD = new Double[pcb.length];
        turnD = new Double[pcb.length];
        nameD = new String[pcb.length];
        queueD = new int[pcb.length];
        pcb_copy = pcb;
        /*
        assignTempBurstD(pcb);
        assignArrivalD(pcb);
        assignBurstD(pcb);
        assignWaitD(pcb);
        assignTurnD(pcb);
         */
    }

    //Driver Code
    public void sortAndTable(PCB[] pcb) {

        int maxProccessIndex = 0;
        Double timer = 0.0;
        Double avgWait = 0.0;
        Double avgTT = 0.0;
        int tq = quantum;
        int n = pcb.length;
        Double arrival[] = new Double[n];
        Double burst[] = new Double[n];
        Double wait[] = new Double[n];
        Double turn[] = new Double[n];
        int queue[] = new int[n];
        Double temp_burst[] = new Double[n];
        boolean complete[] = new boolean[n];
//arrival[]
        pcb_copy = pcb;
        for (int i = 0; i < pcb_copy.length; i++) {
            arrival[i] = pcb_copy[i].getArrivalTime();
        }
        System.out.print("\nInitialized scheduler with " + n + " processes.");
        System.out.print("\nPolicy: Round Robin ");
        System.out.print("\nQuantum ");
        System.out.print(quantum + "\n");
//burst[]
        for (int i = 0; i < pcb_copy.length; i++) {
            burst[i] = pcb_copy[i].getBurstTime();
            temp_burst[i] = burst[i];
        }
//initialize queue[] and complete[]
        for (int i = 0; i < pcb_copy.length; i++) {    //Initializing the queue and complete array
            complete[i] = false;
            queue[i] = 0;
        }
        n=0;
        while (timer < arrival[0])    //Incrementing Timer until the first process arrives
            timer++;
        queue[0] = 1;

/*
        int n, tq, timer = 0, maxProccessIndex = 0;
        float avgWait = 0, avgTT = 0;
        System.out.print("\nEnter the time quanta : ");
        tq = inp.nextInt();
        System.out.print("\nEnter the number of processes : ");
        n = inp.nextInt();
        int arrival[] = new int[n];
        int burst[] = new int[n];
        int wait[] = new int[n];
        int turn[] = new int[n];
        int queue[] = new int[n];
        int temp_burst[] = new int[n];
        boolean complete[] = new boolean[n];
        System.out.print("\nEnter the arrival time of the processes : ");
        for (int i = 0; i < n; i++)
            arrival[i] = inp.nextInt();

        System.out.print("\nEnter the burst time of the processes : ");
        for (int i = 0; i < n; i++) {
            burst[i] = inp.nextInt();
            temp_burst[i] = burst[i];
        }
        for (int i = 0; i < n; i++) {    //Initializing the queue and complete array
            complete[i] = false;
            queue[i] = 0;
        }
        while (timer < arrival[0])    //Incrementing Timer until the first process arrives
            timer++;
        queue[0] = 1;
*/

        while (true) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (temp_burst[i] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                break;

            for (int i = 0; (i < n) && (queue[i] != 0); i++) {
                int ctr = 0;
                while ((ctr < tq) && (temp_burst[queue[0] - 1] > 0)) {
                    temp_burst[queue[0] - 1] -= 1;
                    timer += 1;
                    ctr++;

                    //Updating the ready queue until all the processes arrive
                    checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
                }
                if ((temp_burst[queue[0] - 1] == 0) && (complete[queue[0] - 1] == false)) {
                    turn[queue[0] - 1] = timer;        //turn currently stores exit times
                    complete[queue[0] - 1] = true;
                }

                //checks whether or not CPU is idle
                boolean idle = true;
                if (queue[n - 1] == 0) {
                    for (int k = 0; k < n && queue[k] != 0; k++) {
                        if (complete[queue[k] - 1] == false) {
                            idle = false;
                        }
                    }
                } else
                    idle = false;

                if (idle) {
                    timer++;
                    checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
                }

                //Maintaining the entries of processes after each premption in the ready Queue
                queueMaintainence(queue, n);
            }
        }

        for (int i = 0; i < n; i++) {
            turn[i] = turn[i] - arrival[i];
            wait[i] = turn[i] - burst[i];
        }

        System.out.print("\nProgram No.\tArrival Time\tBurst Time\tWait Time\tTurnAround Time"
                + "\n");
        n=pcb_copy.length;
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t" + arrival[i] + "\t\t" + burst[i]
                    + "\t\t" + wait[i] + "\t\t" + turn[i] + "\n");
        }
        for (int i = 0; i < n; i++) {
            avgWait += wait[i];
            avgTT += turn[i];
        }
        System.out.print("\nAverage wait time : " + (avgWait / n)
                + "\nAverage Turn Around Time : " + (avgTT / n));
    }

    public static void queueUpdation(int[] queue, Double timer, Double[] arrival, int n, int maxProccessIndex){
        int zeroIndex = -1;
        for(int i = 0; i < n; i++){
            if(queue[i] == 0){
                zeroIndex = i;
                break;
            }
        }
        if(zeroIndex == -1)
            return;
        queue[zeroIndex] = maxProccessIndex + 1;
    }

    public static void checkNewArrival(Double timer, Double[] arrival, int n, int maxProccessIndex, int[] queue){
        if(timer <= arrival[n-1]){
            boolean newArrival = false;
            for(int j = (maxProccessIndex+1); j < n; j++){
                if(arrival[j] <= timer){
                    if(maxProccessIndex < j){
                        maxProccessIndex = j;
                        newArrival = true;
                    }
                }
            }
            if(newArrival)    //adds the index of the arriving process(if any)
                queueUpdation(queue,timer,arrival,n, maxProccessIndex);
        }
    }

    public static void queueMaintainence(int queue[], int n){

        for(int i = 0; (i < n-1) && (queue[i+1] != 0) ; i++){
            int temp = queue[i];
            queue[i] = queue[i+1];
            queue[i+1] = temp;
        }
    }
    public void run(){
        //implement...
        int max = rrQ.size()-1;
        int one, two;
        for(int i = 0; i < max; i++) {
            //-> Context Change at time = 9.329
            if(i == 0){
                one = 0;
                two = rrQ.get(i);
                tempQuantum = tempQuantum+quantum;
            }
            else if (i == rrQ.size()-1){
                //    one = rrQ.get(max-1);
                //    two = 99;
                break;
            }
            else{
                one = rrQ.get(i-1);
                two = rrQ.get(i);
                tempQuantum = tempQuantum+quantum;
            }
            System.out.println("-> Context Change at time = " + tempQuantum);
            System.out.println("---------------------------------------");
            System.out.println("| Exits            | Enters           |");
            System.out.println("---------------------------------------");
            System.out.println("| " + one + "            | " + two + "            | ");
            //increment Quantum
        }
        tempQuantum = tempQuantum+quantum;
        System.out.println("-> Context Change at time = " + tempQuantum);
        System.out.println("---------------------------------------");
        System.out.println("| Exits            | Enters           |");
        System.out.println("---------------------------------------");
        System.out.println("| " +  rrQ.get(max-1) + "            | " + "end" + "            | ");
    }

    public void resultTable(PCB [] pcb) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time | Completion Time | Turn Around Time |");
        System.out.println("---------------------------------------------------------------------------");
        //
        //System.out.println("|"+"Process0"+"        "+"|"+"0.000"+"      "+"|"+"3.767"+"           "+"|"+"0.000"+"         "+"|"+"3.767"+"       "+"|"+"");
        for (int i = 0; i < pcb.length; i++) {
            //System.out.print(i+1+"\t\t\t"+arrival[i]+"\t\t\t"+burst[i]
            //      +"\t\t\t"+wait[i]+"\t\t\t"+turn[i]+ "\n");
            System.out.println("|" + (i+1) + "               " +
                    "| " + arrivalD[i] + "          " +
                    "| " + burstD[i] + "          " +
                    "| " + arrivalD[i] + "         " +
                    "| " + burstD[i] + "          " +
                    "| " + 0 + "           " +
                    "| " + turnD[i] + " |");
        }

        Double aveWait = 0.0;
        Double aveTurn = 0.0;
        Double aveBurst = 0.0;
        Double totalBurst = 0.0;

        //get average turn around time
        for (int i = 0; i < turnD.length; i++) {
            aveTurn += turnD[i];
        }
        aveTurn /= turnD.length;
        //get average burst time
        for (int i = 0; i < burstD.length; i++) {
            aveBurst += burstD[i];
        }
        aveBurst /= burstD.length;
        //get total burst time
        for (int i = 0; i < burstD.length; i++) {
            totalBurst += burstD[i];
        }
        //get average wait time
        for (int i = 0; i < waitD.length; i++) {
            aveWait += waitD[i];
        }
        aveWait /= waitD.length;


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+"|");
        System.out.println("|Average         "+
                "|"+ aveWait +"      |"+
                aveBurst+"           "+"|Total Burst"+
                "   "+"|"+ totalBurst +"       "+
                "Avg Turn Around:" +
                aveTurn + " | ");
        System.out.println("---------------------------------------------------------------------------");
    }

    /*
        public boolean checkCompleted(boolean complete []){
        boolean flag = false;
            for(int i=0; i<complete.length; i++){
                if(complete[i] == false) {
                    flag = false;
                }
                else {
                    flag = true;
                }
            }
            completed = flag;
            return  flag;
        }
    */
    public void fileResults(PCB [] pcb) {

//Instantiating the CSVWriter class
        CSVWriter writer = null;
        try {
            //writer = new CSVWriter(new FileWriter("home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/rr.csv"));
            writer = new CSVWriter(new FileWriter("rr.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Instantiating the List Object
        List list = new ArrayList();
        //Writing data to a csv file
        String line1[] = {"id", "arrival", "burst", "wait", "burst", "turn"};
        list.add(line1);
        for (int i = 0; i < pcb.length; i++) {
            //System.out.print(i+1+"\t\t\t"+arrival[i]+"\t\t\t"+burst[i]
            //      +"\t\t\t"+wait[i]+"\t\t\t"+turn[i]+ "\n");
            String id = Integer.toString(i+1);
            String arrival = Double.toString(arrivalD[i]);
            String exe = Double.toString(burstD[i]);
            String wait = String.valueOf(waitD[i]);
            String burst = String.valueOf(burstD[i]);
            String turn = Double.toString(turnD[i]);
            String line2[] = {id, arrival, exe, wait, burst, turn};
            list.add(line2);
        }
        Double aveWait = 0.0;
        Double aveTurn = 0.0;
        Double aveBurst = 0.0;
        Double totalBurst = 0.0;

        //get average turn around time
        for (int i = 0; i < turnD.length; i++) {
            aveTurn += turnD[i];
        }
        aveTurn /= turnD.length;
        //get average burst time
        for (int i = 0; i < burstD.length; i++) {
            aveBurst += burstD[i];
        }
        aveBurst /= burstD.length;
        //get total burst time
        for (int i = 0; i < burstD.length; i++) {
            totalBurst += burstD[i];
        }
        //get average wait time
        for (int i = 0; i < waitD.length; i++) {
            aveWait += waitD[i];
        }
        aveWait /= waitD.length;

        String aveW = Double.toString(aveWait);
        String aveT = Double.toString(aveTurn);
        String aveB = Double.toString(aveBurst);
        String totalB = Double.toString(totalBurst);
        String line3[] = {aveW, aveT, aveB, totalB};
        //Writing data to the csv file
        writer.writeAll(list);
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data entered");

    }

    public void fileResultss(PCB [] pcb) {

//Instantiating the CSVWriter class
        CSVWriter writer = null;
        try {
            //writer = new CSVWriter(new FileWriter("home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/rr.csv"));
            writer = new CSVWriter(new FileWriter("rr2ss.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Instantiating the List Object
        List list = new ArrayList();
        //Writing data to a csv file
        String line1[] = {"id", "arrival", "burst", "wait", "burst", "turn"};
        list.add(line1);
        for (int i = 0; i < pcb.length; i++) {
            //System.out.print(i+1+"\t\t\t"+arrival[i]+"\t\t\t"+burst[i]
            //      +"\t\t\t"+wait[i]+"\t\t\t"+turn[i]+ "\n");
            String id = Integer.toString(i+1);
            String arrival = Double.toString(doneQ.get(i).getArrivalTime());
            String exe = Double.toString(doneQ.get(i).getExecuted());
            String wait = String.valueOf(doneQ.get(i).getWaitTime());
            String burst = String.valueOf(doneQ.get(i).getBurstTime());
            String turn = Double.toString(doneQ.get(i).getTurnAroundTime());
            String line2[] = {id, arrival, exe, wait, burst, turn};
            list.add(line2);
        }
        Double aveWait = 0.0;
        Double aveTurn = 0.0;
        Double aveBurst = 0.0;
        Double totalBurst = 0.0;

        //get average turn around time
        for (int i = 0; i < turnD.length; i++) {
            aveTurn += turnD[i];
        }
        aveTurn /= turnD.length;
        //get average burst time
        for (int i = 0; i < burstD.length; i++) {
            aveBurst += burstD[i];
        }
        aveBurst /= burstD.length;
        //get total burst time
        for (int i = 0; i < burstD.length; i++) {
            totalBurst += burstD[i];
        }
        //get average wait time
        for (int i = 0; i < waitD.length; i++) {
            aveWait += waitD[i];
        }
        aveWait /= waitD.length;

        String aveW = Double.toString(aveWait);
        String aveT = Double.toString(aveTurn);
        String aveB = Double.toString(aveBurst);
        String totalB = Double.toString(totalBurst);
        String line3[] = {aveW, aveT, aveB, totalB};
        //Writing data to the csv file
        writer.writeAll(list);
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data entered");

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