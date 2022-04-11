package com.os.cpu;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class RR extends Scheduler {
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
//Quantum time is 2 this means each process is only executing for 2 units of time at a time.
//How to compute these process requests:-
//1.Take the process which occurs first and start executing the process(for quantum time only).
//2.Check if any other process request has arrived.
//If a process request arrives during the quantum time in which another process is executing, then add the new process to the Ready queue
//3.After the quantum time has passed, check for any processes in the Ready queue. If the ready queue is empty then continue the current process.
//If the queue not empty and the current process is not complete, then add the current process to the end of the ready queue.

//4.Take the first process from the Ready queue and start executing it (same rules)
//5.Repeat all steps above from 2-4
//6.If the process is complete and the ready queue is empty then the task is complete
//After all these we get the three times which are:

//Completion Time: the time taken for a process to complete.
//1.Turn Around Time: total time the process exists in the system. (completion time – arrival time).
//2. Waiting Time: total time waiting for their complete execution. (turn around time – burst time ).
//3. How to implement in a programming language

//1. Declare arrival[], burst[], wait[], turn[] arrays and initialize them. Also declare a timer
//   variable and initialize it to zero. To sustain the original burst array create another
//   array (temp_burst[]) and copy all the values of burst array in it.
   PCB arrival [];
   PCB burst [];
   PCB wait[];
   PCB turn[];
   PCB temp_burst[];
   Double timer = 0.0;
   Double arrivalD [];
   Double burstD [];
   Double waitD[];
   Double turnD[];
   Double temp_burstD[];
   String nameD[];
   int queueD[];
   Double averageWait;
   Double averageTurnAroundTime;

   ArrayList<Integer> rrQ = new ArrayList<Integer>();
   ArrayList<Double> rrB = new ArrayList<Double>();
   ArrayList<Double> rrT  = new ArrayList<Double>();
//2. To keep a check we create another array of bool type which keeps the record of whether a
//   process is completed or not. we also need to maintain a queue array which contains the process
//   indices (initially the array is filled with 0).
   //Queue<PCB> indicies = (Queue<PCB>) new ArrayList<PCB>();
//3. Now we increment the timer variable until the first process arrives and when it does, we add the
//   process index to the queue array
    private boolean running = false;

    public void runnn(){
      running = true;
      while(running){
            running = false;
      }

  }
//4. Now we execute the first process until the time quanta and during that time quanta, we check
//   whether any other process has arrived or not and if it has then we add the index in the queue
//   (by calling the fxn. queueUpdation()).
   
//5. Now, after doing the above steps if a process has finished, we store its exit time and
   //execute the next process in the queue array. Else, we move the currently executed process at
//  the end of the queue (by calling another fxn. queueMaintainence()) when the time slice expires.
   
//6. The above steps are then repeated until all the processes have been completely executed. If a
//   scenario arises where there are some processes left but they have not arrived yet, then we
//   shall wait and the CPU will remain idle during this interval.


    public RR(PCB[] pcb) {
        super(pcb);
        //Double timer = 0.0;
        PCB arrival [] = new PCB[pcb.length];
        PCB burst [] = new PCB[pcb.length];
        PCB wait[] = new PCB[pcb.length];
        PCB turn[] = new PCB[pcb.length];
        PCB temp_burst[] = new PCB[pcb.length];

        arrival = pcb;
        burst = pcb;
        wait = pcb;
        turn = pcb;
        temp_burst = pcb;

        temp_burstD = new Double[pcb.length];
        arrivalD = new Double[pcb.length];
        burstD = new Double[pcb.length];
        waitD  = new Double[pcb.length];
        turnD = new Double[pcb.length];
        nameD = new String[pcb.length];
        queueD = new int[pcb.length];
        /*
        assignTempBurstD(pcb);
        assignArrivalD(pcb);
        assignBurstD(pcb);
        assignWaitD(pcb);
        assignTurnD(pcb);
         */
    }
    public void assignArrivalD(PCB [] pcb) {
        for (int i = 0; i < pcb.length; i++) {
            arrivalD[i] = pcb[i].getArrivalTime();
        }
    }
    public void assignTempBurstD(PCB [] pcb){
        for(int i=0; i<pcb.length; i++){
            temp_burstD[i] = pcb[i].getBurstTime();
        }
}
    public void assignBurstD(PCB [] pcb){
        for(int i=0; i<pcb.length; i++){
            burstD[i] = pcb[i].getBurstTime();
        }
    }
    public void assignWaitD(PCB [] pcb){
        for(int i=0; i<pcb.length; i++){
            waitD[i] = pcb[i].getWaitTime();
        }
    }

    public void assignTurnD(PCB [] pcb){
        for(int i=0; i<pcb.length; i++){
           turnD[i] = pcb[i].getTurnAroundTime();
        }

    }

public boolean checkCompleted(PCB p){
        if(p.getComplete()){
            return true;
        }
        else {
            return false;
        }
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


    //Driver Code
    public void runn(PCB [] pcb){
    // n = process amount
    // tq =
    // timer = simulation timer
    // tq = quantum
    // avgWait = average waiting time
    // avgTT = average turn around time
    // arrival[] = arrival queue
    // burst[] = burst queue
    // wait[] = waiting quee
    // turn[] = turn around time queue
    // temp_burst[] = mutiple burst queue
    // complete[] = complete state queue
    int n,tq, maxProccessIndex = 0;
    Double timer = 0.0;
    Double avgWait = 0.0, avgTT = 0.0;
        tq = quantum;
        n = pcb.length;
        Double arrival[] = new Double[n];
        Double burst[] = new Double[n];
        Double wait[] = new Double[n];
        Double turn[] = new Double[n];
        int queue[] = new int[n];
        Double temp_burst[] = new Double[n];
        boolean complete[] = new boolean[n];
//arrival[]
        for(int i = 0; i < n; i++) {
            arrival[i] = pcb[i].getArrivalTime();
        }
        System.out.print("\nInitialized scheduler with " + n + " processes.");
        System.out.print("\nPolicy: Round Robin ");
        System.out.print("\nQuantum " );
        System.out.print(quantum+ "\n");
//burst[]
        for(int i = 0; i < n; i++){
            burst[i] = pcb[i].getBurstTime();
            temp_burst[i] = burst[i];
        }
//initialize queue[] and complete[]
        for(int i = 0; i < n; i++){    //Initializing the queue and complete array
            complete[i] = false;
            queue[i] = 0;
        }
        while(timer < arrival[0])    //Incrementing Timer until the first process arrives
            timer++;
        queue[0] = 1;
        //add value to round robin queue
        rrQ.add(queue[0]);
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
                //rrQ.add(0,queue[i]);
                int tqCtr = 0;
                //process arries only dont add to array
                while((tqCtr < tq) && (temp_burst[(int) (queue[0]-1)] > 0)){
                    temp_burst[queue[0]-1] -= 1;
                    timer += 1;
                    tqCtr++;

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
                    rrT.add(timer);
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
            System.out.print(i+1+"\t\t\t"+arrival[i]+"\t\t\t"+burst[i]
                    +"\t\t\t"+wait[i]+"\t\t\t"+turn[i]+ "\n");
        }
        for(int i =0; i< n; i++){
            avgWait += pcb[i].getWaitTime();
            avgTT += pcb[i].getTurnAroundTime();
        }
        System.out.print("\nAverage wait time : "+(avgWait/n)
                +"\nAverage Turn Around Time : "+(avgTT/n));

  //save to copy arrays for more manipulation

        temp_burstD = temp_burst;
        arrivalD = arrival;
        burstD = burst;
        waitD  = wait;
        turnD = turn;
        queueD = queue;
        averageWait = (avgWait/n);
        averageTurnAroundTime = (avgTT/n);
  }
    public void run(){
        //implement...
        int max = rrQ.size();
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

    public void resultTable(PCB [] pcb){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time | Completion Time | Turn Around Time |");
        System.out.println("---------------------------------------------------------------------------");
        //
        //System.out.println("|"+"Process0"+"        "+"|"+"0.000"+"      "+"|"+"3.767"+"           "+"|"+"0.000"+"         "+"|"+"3.767"+"       "+"|"+"");
        for(int i = 0; i < pcb.length; i++) {
            //System.out.print(i+1+"\t\t\t"+arrival[i]+"\t\t\t"+burst[i]
              //      +"\t\t\t"+wait[i]+"\t\t\t"+turn[i]+ "\n");
            System.out.println("|" + (i+1)  + "               " +
                    "| " + arrivalD[i] + "          " +
                    "| " + burstD[i] + "          " +
                    "| " + arrivalD[i] + "         " +
                    "| " + burstD[i] + "          " +
                    "| " + 0 + "           " +
                    "| " + turnD[i] + " |");

        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+"|");
        System.out.println("|Average         "+
                "|"+ this.averageWait +"      |"+
                " ---- "+"           "+"|Total Burst"+
                "   "+"|"+ "---" +"       "+
                "Avg Turn Around:"+
                this.averageTurnAroundTime+ " | ");
        System.out.println("---------------------------------------------------------------------------");
    }


    public void queueUpdation(int queue[],Double timer, Double arrival[],int n, int maxProccessIndex){
        int zeroIndex = -1;
        for(int i = 0; i < n; i++){
            if(queue[i] == 0){
                zeroIndex = i;
                break;
            }
        }
        if(zeroIndex == -1)
            return;
        //where
        //where the processes arrive
        queue[zeroIndex] = maxProccessIndex + 1;
        //rrQ.add(0,queue[zeroIndex]);
    }

    public void checkNewArrival(Double timer, Double arrival[], int n, int maxProccessIndex,int queue[]){
        if(timer <= arrival[n-1]){
            boolean newArrival = false;
            //chack all process arrival times in arrival[]
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

    public void queueMaintainence(int queue[], int n){

        for(int i = 0; (i < n-1) && (queue[i+1] != 0) ; i++){
            //rrQ.add(0,queue[i+1]);
            //rrQ.add((Integer) queue[i]);
            //rrQ.add(queue[0]);
            int temp = queue[i];
            queue[i] = queue[i+1];
            queue[i+1] = temp;
            //add new process currently running to rrQ
        }
        rrQ.add(queue[0]);
    }
}