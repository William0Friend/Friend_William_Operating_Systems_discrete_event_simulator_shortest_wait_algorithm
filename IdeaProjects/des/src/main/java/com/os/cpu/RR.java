package com.os.cpu;

class RR extends Scheduler {
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
