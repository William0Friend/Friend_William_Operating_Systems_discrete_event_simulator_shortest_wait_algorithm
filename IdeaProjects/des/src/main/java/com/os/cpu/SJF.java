package com.os.cpu;
import java.util.LinkedList;
import java.util.Queue;

public class SJF extends Scheduler{

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
    Queue<PCB> arrivalQueue = new LinkedList<PCB>();
    //Queue to hold all processes once processed
    Queue<PCB> readyQueue = new LinkedList<PCB>();

    PCB [] pcbl = new PCB [3];
    public SJF(PCB[] pcb) {
        super(pcb);
    }
    @Override
    void normalization() {
    }
    @Override
    void sortPcb(){
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
    void resultTable() {

    }

    @Override
    void preemptionTable(int i, int j, Double time) {

    }



}
