public class Scheduler extends PCB {
    /**Data
     * - nProcess: int
     * - timeline: float = 0
     * - avgExecutionTime : float
     * - avgWaitTime: float
     * - normalization: float
     *
     */
    private int nProcess;
    private float timeline;
    private float avgExecutionTime;
    private float avgWaitTime;
    private float normalization;

    /**Methods
     * # Scheduler(pcb: PCB[])
     * - normalization() void
     * ~ sortPcb(): void
     * ~ averageTime(): void
     * - indentation(text: String, limit: int): String
     * ~ resultTable(): void
     * ~ preemptionTable(i: int, j: int, time: float): void
     */
    public Scheduler(PCB [] pcb) {
        this.nProcess = pcb[0];
        this.timeline = pcb[1];
        this.avgExecutionTime = pcb[2];
        this.avgWaitTime = pcb[3];
        this.normalization = pcb[4];
    }
    private void normalization(){
        //implement...
        //this.normalization = normalization;
    }
    public void sortPcb(){
        //implement...
    }
    public void averageTime(){
        //implement...
        //avgWait + avgExe = ave?
    }
    private String indentation(String text, int limit)
    {
        //implement...
    }
    public void resultTable(){
        //implement...
    }
    public void preemptionTable(int i, int j, float time){
        //implement...
    }
}
