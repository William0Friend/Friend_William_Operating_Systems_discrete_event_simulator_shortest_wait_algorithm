package com.os.cpu;
import com.opencsv.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
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
   ArrayList<Double> rrT  = new ArrayList<>();
    private Double totalBurstTime;
boolean completed = false;
File f = new File("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/RR_demo.txt");
FileWriter w = new FileWriter(f);
ArrayList<PCB> doneQ = new ArrayList<>();
//4. Now we execute the first process until the time quanta and during that time quanta, we check
//   whether any other process has arrived or not and if it has then we add the index in the queue
//   (by calling the fxn. queueUpdation()).
   
//5. Now, after doing the above steps if a process has finished, we store its exit time and
   //execute the next process in the queue array. Else, we move the currently executed process at
//  the end of the queue (by calling another fxn. queueMaintainence()) when the time slice expires.
   
//6. The above steps are then repeated until all the processes have been completely executed. If a
//   scenario arises where there are some processes left but they have not arrived yet, then we
//   shall wait and the CPU will remain idle during this interval.


    public RR(PCB[] pcb) throws IOException {
        super(pcb);
        //Double timer = 0.0;
        //arrival = pcb;
        temp_burstD = new Double[pcb.length];
        arrivalD = new Double[pcb.length];
        burstD = new Double[pcb.length];
        waitD  = new Double[pcb.length];
        turnD = new Double[pcb.length];
        nameD = new String[pcb.length];
        queueD = new int[pcb.length];
        File file = new File("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/RR_demo.txt");
        file.createNewFile();
        FileWriter writer = null;
        writer = new FileWriter(file);
        /*
        assignTempBurstD(pcb);
        assignArrivalD(pcb);
        assignBurstD(pcb);
        assignWaitD(pcb);
        assignTurnD(pcb);
         */
    }
    @Override
    void normalization() {
    }

    void sortPcb() {
    }


    @Override
    void preemptionTable(int i, int j, Double time) {
    }
    @Override
void resultTable(){

}

    //Driver Code
    public void sortPcb(PCB [] pcb) throws IOException {


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
                int ctr = 0;
                //process arries only dont add to array
                while((ctr < tq) && (temp_burst[(int) (queue[0]-1)] > 0)){
                    temp_burst[queue[0]-1] -= 1;
                    timer += 1;
                    ctr++;

                    //Updating the ready queue until all the processes arrive
                    checkNewArrival(timer, arrival, n, maxProccessIndex, queue);
                }
                if((temp_burst[queue[0]-1] == 0) && (complete[queue[0]-1] == false)){
                    turn[queue[0]-1] = timer;        //turn currently stores exit times
                    complete[queue[0]-1] = true;
                    doneQ.add(pcb[queue[0]-1]);
                }

                //checkCompleted(complete);
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
        //fill these
        for(int i = 0; i < n; i++){
            //adjust turn around time for arrival time
            turn[i] = turn[i] - arrival[i];
            //create waiiting times with adjusted turn around time
            wait[i] = turn[i] - burst[i];
        }
  //save to copy arrays for more manipulation
        temp_burstD = temp_burst;
        arrivalD = arrival;
        burstD = burst;
        waitD  = wait;
        turnD = turn;
        queueD = queue;
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
        if(timer != 0 || arrival[n-1] >= 0 || arrival[n-1] != null){//<= arrival[n-1]){
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
            //cleanup
            for(int i = 0; i < arrival.length; i++){
                if(arrival[n-1] < timer){
                    queueUpdation(queue,timer,arrival,n, maxProccessIndex);
                }
                else{
                    break;
                }
            }
        }
    }

    public void queueMaintainence(int queue[], int n) throws IOException {
        int qCount = 0;
        for(int i = 0; (i < n-1) && (queue[i+1] != 0) ; i++){
            int temp = queue[i];
            queue[i] = queue[i+1];
            queue[i+1] = temp;
        }

        }
        /*
        else if (rrQ.size() == 2){
            int one = rrQ.get(0);
            int two = rrQ.get(1);
            System.out.println("-> Context Change at time = " + tempQuantum);
            writer.write("-> Context Change at time = " + tempQuantum);
            System.out.println("---------------------------------------");
            writer.write("---------------------------------------");
            System.out.println("| Exits            | Enters           |");
            writer.write("| Exits            | Enters           |");
            System.out.println("---------------------------------------");
            writer.write("---------------------------------------");
            System.out.println("| " + one + "            | " + two + "            | ");
            writer.write("| " + one + "            | " + two + "            | ");
            //increment Quantum
            tempQuantum += 2;
            //clear rrQ
            rrQ.clear();
        }
*/
    }

