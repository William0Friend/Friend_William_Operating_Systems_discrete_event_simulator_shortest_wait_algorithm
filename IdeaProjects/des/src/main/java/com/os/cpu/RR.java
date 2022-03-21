package com.os.cpu;

public class RR extends Scheduler {
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
    public RR(PCB[] pcb) {
        super(pcb);
    }
    /**Data
     * - quantum: float
     */

    /**Methods
     * ~ RR(quantum: float, pcb: PCB[])
     * - setParameters() : void
     * - timeLineCalc(): float
     * ~ run(): void
     */
}
