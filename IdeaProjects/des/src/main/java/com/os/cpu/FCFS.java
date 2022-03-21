package com.os.cpu;

public class FCFS extends Scheduler{
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
    public FCFS(PCB[] pcb) {
        super(pcb);
    }
    /**Methods
     * ~ FCFS(pcb: PCB[])
     * ~ calcAllFcfs(): void
     * ~ run(): void
     */
}
