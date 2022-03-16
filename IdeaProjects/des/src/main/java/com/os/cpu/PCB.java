package com.os.cpu;




public class PCB {
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
    private Double executed;
    private Double waitTime;
    private Double remainingTime = 0.0;
    private Double beginTime = 0.0;
    private Double endTime = 0.0;
    public PCB(String name) {
        this.name = name;
    }

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
        this.name = "null";
        this.waitTime = 0.0;
        this.executed = 0.0;
        this.arrivalTime = 0.0;
        this.burstTime = 0.0;
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
        //this.remainingTime = remainingTime;
        //this.beginTime = beginTime;
        //this.endTime = endTime;
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
        return executed;
    }
    public void setRemainingTime(Double remainingTime){
        this.remainingTime = remainingTime;
    }
    public Double getRemainingTime(){
        return this.remainingTime;
    }
    public void setEndTime(Double endTime){
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
        return this.name;
    }

    public Double getWaitTime() {
        return this.waitTime;
    }
    public void setWaitTime(Double waitTime) {
        this.waitTime = waitTime;
    }
    public void changeWaitTime(Double value) {
        this.waitTime = value;
    }

}
