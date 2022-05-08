package com.os.cpu;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import com.opencsv.CSVWriter;
import java.io.IOException;
import java.util.List;
public class SJF extends Scheduler {

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

    //data structure to load use loaded PCB array with
    //Queue<PCB> arrivalQueue = new LinkedList<PCB>();
    //Queue to hold all processes once processed

    //ArrayList<PCB> sjfQ = new ArrayList<PCB>();
    PCB[] pcb_copy;

    public SJF(PCB[] pcb) {
        super(pcb);
        //pcb_copy = new PCB[pcb.length];
        pcb_copy = pcb;
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

    public void run() {
        //implement...
        //Initialized scheduler with 3 processes.
        System.out.println("Initialized scheduler with " + pcb_copy.length + " processes.");
        //Policy: Round Robin
        System.out.println("Policy: Shortest Job First ");
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
    public void resultTableFile() {
        System.out.println("| Process        | Wait Time | Execution Time | Arrival Time | Burst Time | Completion Time | Turn Around Time |");
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
    public void fileResults() {
//Instantiating the CSVWriter class
        CSVWriter writer = null;
        try {
            //writer = new CSVWriter(new FileWriter("home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/rr.csv"));
            writer = new CSVWriter(new FileWriter("sjf.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Instantiating the List Object
        List list = new ArrayList();
        //Writing data to a csv file
        String line1[] = {"id", "arrival", "burst", "wait", "burst", "turn"};
        list.add(line1);
        for(PCB x : pcb_copy) {

            String id = x.getName();
            String arrival = Double.toString(x.getArrivalTime() );
            String exe = Double.toString(x.getExecuted());
            String wait = String.valueOf(x.getWaitTime());
            String burst = String.valueOf(x.getExecuted());
            String turn = Double.toString(x.getTurnAroundTime());
            String line2[] = {id, arrival, exe, wait, burst, turn};
            list.add(line2);
        }
        Double aveWait = 0.0;
        Double aveTurn = 0.0;
        Double aveBurst = 0.0;
        Double totalBurst = 0.0;
        Double totalTurn = 0.0;
        //get average turn around time
        //setAvgTurn(pcb_copy);
        //aveTurn = getAvgTurn();
        aveTurn= 0.0;
        //get average burst time
        setAvgBurstTime(pcb_copy);
        aveBurst += getAvgBurstTime();
        //get total burst tim
        setTotalBurstTime(pcb_copy);
        totalBurst += getTotalBurstTime();
        //get average wait time
        setAvgWaitTime(pcb_copy);
        aveWait =  getAvgWaitTime();
        String line3[] = {"aveW", "aveT", "aveB", "totalB", "totalT"};
        list.add(line3);
        String aveW = Double.toString(aveWait);
        String aveT = Double.toString(aveTurn);
        String aveB = Double.toString(aveBurst);
        String totalB = Double.toString(totalBurst);
        String totalT = Double.toString(totalTurn);
        String line4[] = {aveW, aveT, aveB, totalB, totalT};
        list.add(line4);
        //Writing data to the csv file
        writer.writeAll(list);
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data entered");

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
