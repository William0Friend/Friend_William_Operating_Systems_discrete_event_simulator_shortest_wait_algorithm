package com.os.cpu;


public class PCB{

// Process State: Can be ready, running, etc.
private boolean ready;
private boolean running;
// CPU registers: Like the Program Counter (CPU registers must be saved and
//            restored when a process is swapped in and out of CPU)
//      I/O status information: For example, devices allocated to the process,
//    open files, etc
    public boolean ioStatus;
//CPU scheduling information:
    private int priority;

    /**
     *  Data
     * - name : String
     * - burstTime: Double
     * - arrivalTime: Double
     * - remainingTime: Double
     * - executed: Double
     * - beginTime: Double
     * - endTime: Double
     * - waitTime: Double
     * - clock: int
     * - clockcycles: int
     */
    private String name;
    private Double burstTime;
    private Double arrivalTime;
    private Double executed = 0.0;
    private Double waitTime = 0.0;
    private Double remainingTime = 0.0;
    private Double beginTime = 0.0;
    private Double endTime = 0.0;
    public PCB(String name) {
        this.name = name;
    }



    //Completion Time: Time at which process completes its execution.
    private Double completionTime;

    //Turn Around Time: Time Difference between completion time and arrival time. Turn Around Time = Completion Time – Arrival Time
    private Double turnAroundTime;
    //Waiting Time(W.T): Time Difference between turn around time and burst time.
    //Waiting Time = Turn Around Time – Burst Time
    private Double waitingTime;
    /**
     * Methods
     * ~ getWaitTime(): Double
     * ~ setWaitTime(waitTime: Double): void
     * ~ changeWaitTime(value: Double): void
     * ~ PCB(name: String, burstTime: Double, arrivalTime: Double)
     * ~ getName(): String
     * ~ setName(String name)
     * ~ getBurstTime(): Double
     * # setBurstTime(burstTime: Double): void
     * ~ getBeginTime(): Double
     * ~ setBeginTime(beginTime: Double): void
     * ~ getEndTime(): Double
     * ~ setEndTime(endTime: Double): void
     * ~ getRemainingTime(): Double
     * ~ setRemainingTime(remainingTime: Double): void
     * ~ getExecuted(): Double
     * ~ setExecuted(executed: Double): void
     * ~ getArrivalTime(arrivalTime: int) void
     *{"executionTime":3.767,"arrivalTime":0.0,"burstTime":3.767,"processN":"Process0","waitTime":0.0}
     * {"executionTime":1.904,"arrivalTime":4.763,"burstTime":1.904,"processN":"Process1","waitTime":0.0}
     * {"executionTime":3.767,"arrivalTime":0.0,"burstTime":3.767,"processN":"Process2","waitTime":0.0}
    */
    public PCB(){
        this.name = "99";
        this.waitTime = 99.0;
        this.executed = 99.0;
        this.arrivalTime = 99.0;
        this.burstTime = 99.0;
        //this.remainingTime = remainingTime;
        //this.beginTime = beginTime;
        //this.endTime = endTime;
    }
    public PCB(String name, Double waitTime,Double executed, Double arrivalTime, Double burstTime ){
        this.name = name;
        this.waitTime = waitTime;
        this.executed = executed;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        //this.beginTime = beginTime;
        this.beginTime =  0.0;
        //this.endTime = endTime;
        this.endTime = 0.0;
        //this.remainingTime = remainingTime;
        this.remainingTime = 0.0;
    }
    public PCB (PCB p){
        name = p.name;
        burstTime = p.burstTime;
        arrivalTime = p.arrivalTime;
        executed = p.executed;
        waitTime = p.waitTime;
        remainingTime = p.remainingTime;
        beginTime = p.beginTime;
        endTime = p.endTime;
    }
    public void print(){
        System.out.println("[");
        System.out.print("Name: " + this.name + "\n" +
                         "WaitTime: " + this.waitTime + "\n" +
                         "Executed: " + this.executed + "\n" +
                         "ArrivalTime: " + this.arrivalTime + "\n" +
                         "BurstTime: " + this.burstTime + "\n" +
                         "RemainingTime: " + this.remainingTime + "\n" +
                         "BeginTime: " + this.beginTime + "\n" +
                         "EndTime: " + this.endTime + "\n");
        System.out.println("]");
        System.out.println();
    }
    public void setArrivalTime(Double arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    public Double getArrivalTime() {
        return this.arrivalTime;
    }
    public void setExecuted(Double executed) {
        this.executed = executed;
    }
    public Double getExecuted(){
        return this.executed;
    }
    public void setRemainingTime(Double remainingTime){
        this.remainingTime = remainingTime;
    }
    public Double getRemainingTime(){
        return this.remainingTime;
    }
    public void setEndTime(Double endTime){
        //Instant i = Instant.now();
        this.endTime = endTime;
    }
    public Double getEndTime() {
        return endTime;
    }
    public void setBeginTime(Double beginTime){
        this.beginTime = beginTime;
    }
    public Double getBeginTime(){
        return this.beginTime;
    }
    public void setBurstTime(Double burstTime) {
        this.burstTime = burstTime;
    }
    public Double getBurstTime(){
        return this.burstTime;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        if(name != null)
        return this.name;
        else{
            return  "null getName()";
        }
    }
    public void setWaitTime(Double waitTime) {
        this.waitTime = waitTime;
    }
    public Double getWaitTime(){
        return this.waitTime;
    }
    public void setCompletionTime(Double completionTime) {
        this.completionTime = completionTime;
    }
    public Double getCompletionTime(){
        return this.completionTime;
    }
    public void autoSetCompletionTime() {
        this.completionTime = waitTime + burstTime;
    }
    public void setTurnAroundTime(Double turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }
    public void autoSetTurnAroundTime() {
        this.turnAroundTime = this.waitTime + this.burstTime;
    }
    public Double getTurnAroundTime() {
        return this.turnAroundTime;
    }
}
