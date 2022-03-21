package com.os.cpu;

import java.util.PriorityQueue;
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
    public SJF(PCB[] pcb) {
        super(pcb);


    }


    public PCB [] pcb;
    public Queue<PCB> readyQueue = new PriorityQueue<>();
    public Queue<PCB> waitQueue = new PriorityQueue<>();

}
