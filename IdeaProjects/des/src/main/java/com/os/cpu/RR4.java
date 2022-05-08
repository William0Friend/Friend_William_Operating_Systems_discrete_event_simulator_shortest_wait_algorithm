package com.os.cpu;

//JAVA Program for implementing
//Round Robin Algorithm

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class RR4{
    int a;
    int b;
    int w;
    int t;
    int q;
    int tb;
    boolean c;
    PCB[] pcb_copy;
    //private static Scanner inp = new Scanner(System.in);
    //Driver Code
    RR4 (PCB [] pcb){
        pcb_copy = pcb;
    }
    public void SortAndTable(PCB [] pcb){
        int n,tq, timer = 0, maxProccessIndex = 0;
        float avgWait = 0, avgTT = 0;
        //System.out.print("\nEnter the time quanta : ");
        //tq = inp.nextInt();
        tq = 2;
        //System.out.print("\nEnter the number of processess : ");
        //n = inp.nextInt();
        n = pcb.length;
        int arrival[] = new int[n];
        int burst[] = new int[n];
        int wait[] = new int[n];
        int turn[] = new int[n];
        int queue[] = new int[n];
        int temp_burst[] = new int[n];
        boolean complete[] = new boolean[n];

        //System.out.print("\nEnter the arrival time of the processess : ");
        for(int i = 0; i < n; i++) {
            arrival[i] = (int)(pcb_copy[i].getArrivalTime()*1);
        }

       //15 System.out.print("\nEnter the burst time of the processess : ");
        for(int i = 0; i < n; i++){
            burst[i] = (int)(pcb_copy[i].getBurstTime()*1);
            temp_burst[i] = burst[i];
        }

        for(int i = 0; i < n; i++){ //Initializing the queue and complete array
            complete[i] = false;
            queue[i] = 0;
        }
        while(timer < arrival[0]) //Incrementing Timer until the first process arrives
            timer++;
        queue[0] = 1;

        while(true){
            boolean flag = true;
            for(int i = 0; i < n; i++){
                if(temp_burst[i] != 0){
                    flag = false;
                    break;
                }
            }
            if(flag)
                break;

            for(int i = 0; (i < n) && (queue[i] != 0); i++){
                int ctr = 0;
                while((ctr < tq) && (temp_burst[queue[0]-1] > 0)){
                    temp_burst[queue[0]-1] -= 1;
                    timer += 1;
                    ctr++;

                    //Updating the ready queue until all the processes arrive
                    checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
                }
                if((temp_burst[queue[0]-1] == 0) && (complete[queue[0]-1] == false)){
                    turn[queue[0]-1] = timer;        //turn currently stores exit times
                    complete[queue[0]-1] = true;
                }

                //checks whether or not CPU is idle
                boolean idle = true;
                if(queue[n-1] == 0){
                    for(int k = 0; k < n && queue[k] != 0; k++){
                        if(complete[queue[k]-1] == false){
                            idle = false;
                        }
                    }
                }
                else
                    idle = false;

                if(idle){
                    timer++;
                    checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
                }

                //Maintaining the entries of processes after each premption in the ready Queue
                queueMaintainence(queue,n);
            }
        }

        for(int i = 0; i < n; i++){
            turn[i] = turn[i] - arrival[i];
            wait[i] = turn[i] - burst[i];
        }

        System.out.print("\nProgram No.\tArrival Time\tBurst Time\tWait Time\tTurnAround Time"
                + "\n");
        for(int i = 0; i < n; i++){
            System.out.print(i+1+"\t\t"+arrival[i]+"\t\t"+burst[i]
                    +"\t\t"+wait[i]+"\t\t"+turn[i]+ "\n");
        }
        for(int i =0; i< n; i++){
            avgWait += wait[i];
            avgTT += turn[i];
        }
        System.out.print("\nAverage wait time : "+(avgWait/n)
                +"\nAverage Turn Around Time : "+(avgTT/n));

        CSVWriter writer = null;
        try {
            //writer = new CSVWriter(new FileWriter("home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/rr.csv"));
            writer = new CSVWriter(new FileWriter("rr4.csv"));
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
            String arr = Integer.toString(arrival[i]);
            String exe = Integer.toString(burst[i]);
            String wai = Integer.toString(wait[i]);
            String bur = Integer.toString(burst[i]);
            String tur = Integer.toString(turn[i]);
            String line2[] = {id, arr, exe, wai, bur, tur};
            list.add(line2);
        }
        Double aveWait = 0.0;
        Double aveTurn = 0.0;
        Double aveBurst = 0.0;
        Double totalBurst = 0.0;
        Double totalTurn = 0.0;
        //get average turn around time
        for (int i = 0; i < turn.length; i++) {
            aveTurn += (double)(turn[i]);
        }
        aveTurn /= turn.length;
        //get average burst time
        for (int i = 0; i < burst.length; i++) {
            aveBurst += (double)(burst[i]);
        }
        aveBurst /= burst.length;
        //get total burst time
        for (int i = 0; i < burst.length; i++) {
            totalBurst += (double)(burst[i]);
        }
        //get average wait time
        for (int i = 0; i < wait.length; i++) {
            aveWait += (double)(wait[i]);
        }
        aveWait /= wait.length;
        //get total turn time
        for (int i = 0; i < turn.length; i++) {
            aveWait += turn[i];
        }
        aveWait /= (double)(turn.length);


        String aveW = Double.toString(aveWait);
        String aveT = Double.toString(aveTurn);
        String aveB = Double.toString(aveBurst);
        String totalB = Double.toString(totalBurst);
        String totalT = Double.toString(totalTurn);

        String line3[] = {"aveW", "aveT", "aveB", "totalB", "totalT"};
        list.add(line3);
        String line4[] = {aveW, aveT, aveB, totalB, totalT};
        list.add(line4);
        //Writing data to the csv file
        writer.writeAll(list);
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nData entered");

    }
/*
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
            String arrival = Integer.toString(arrival[i]);
            String exe = Integer.toString(burst[i]);
            String wait = Integer.toString(wait[i]);
            String burst = Integer.toString(burst[i]);
            String turn = Integer.toString(turn[i]);
            String line2[] = {id, arrival, exe, wait, burst, turn};
            list.add(line2);
        }
        Double aveWait = 0.0;
        Double aveTurn = 0.0;
        Double aveBurst = 0.0;
        Double totalBurst = 0.0;

        //get average turn around time
        for (int i = 0; i < turn.length; i++) {
            aveTurn += turnD[i];
        }
        aveTurn /= turn.length;
        //get average burst time
        for (int i = 0; i < burst.length; i++) {
            aveBurst += burstD[i];
        }
        aveBurst /= burst.length;
        //get total burst time
        for (int i = 0; i < burst.length; i++) {
            totalBurst += burstD[i];
        }
        //get average wait time
        for (int i = 0; i < wait.length; i++) {
            aveWait += waitD[i];
        }
        aveWait /= wait.length;

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
*/
    /*
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
*/
    public static void queueUpdation(int queue[],int timer,int arrival[],int n, int maxProccessIndex){
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

    public static void checkNewArrival(int timer, int arrival[], int n, int maxProccessIndex,int queue[]){
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
            if(newArrival) //adds the index of the arriving process(if any)
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

}
