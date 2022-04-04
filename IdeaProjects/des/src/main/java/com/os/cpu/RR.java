package com.os.cpu;

class RR extends Scheduler {
    // round robin needs a quantum
    private static final int quantum = 5;
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

}
