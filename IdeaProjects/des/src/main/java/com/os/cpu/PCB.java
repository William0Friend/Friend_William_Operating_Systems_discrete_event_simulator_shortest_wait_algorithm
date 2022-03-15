package com.os.cpu;




public class PCB {
    /**
     *  Data
     * - name : String
     * - burstTime: float
     * - arrivalTime: float
     * - remainingTime: float
     * - executed: float
     * - beginTime: float
     * - endTime: float
     * - waitTime: float
     * - clock: int
     * - clockcycles: int
     */
    private String name;
    private float burstTime;
    private float arrivalTime;
    private float executed;
    private float waitTime;
    private float remainingTime = 0;
    private float beginTime = 0;
    private float endTime = 0;
    public PCB(String name) {
        this.name = name;
    }

    /**
     * Methods
     * ~ getWaitTime(): float
     * ~ setWaitTime(waitTime: float): void
     * ~ changeWaitTime(value: float): void
     * ~ PCB(name: String, burstTime: float, arrivalTime: float)
     * ~ getName(): String
     * ~ setName(String name)
     * ~ getBurstTime(): float
     * # setBurstTime(burstTime: float): void
     * ~ getBeginTime(): float
     * ~ setBeginTime(beginTime: float): void
     * ~ getEndTime(): float
     * ~ setEndTime(endTime: float): void
     * ~ getRemainingTime(): float
     * ~ setRemainingTime(remainingTime: float): void
     * ~ getExecuted(): float
     * ~ setExecuted(executed: float): void
     * ~ getArrivalTime(arrivalTime: int) void
     *{"executionTime":3.767,"arrivalTime":0.0,"burstTime":3.767,"processN":"Process0","waitTime":0.0}
     * {"executionTime":1.904,"arrivalTime":4.763,"burstTime":1.904,"processN":"Process1","waitTime":0.0}
     * {"executionTime":3.767,"arrivalTime":0.0,"burstTime":3.767,"processN":"Process2","waitTime":0.0}
    */

    public PCB(){
        this.name = "null";
        this.waitTime = 0;
        this.executed = 0;
        this.arrivalTime = 0;
        this.burstTime = 0;
        //this.remainingTime = remainingTime;
        //this.beginTime = beginTime;
        //this.endTime = endTime;
    }
    public PCB(String name, float waitTime,float executed, float arrivalTime, float burstTime ){
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
                         "BurstTime" + this.burstTime + "\n" +
                         "RemainingTime" + this.remainingTime + "\n" +
                         "BeginTime: " + this.beginTime + "\n" +
                         "EndTime: " + this.endTime + "\n");
        System.out.println("]");
        System.out.println();
    }
    public void setArrivalTime(float arrivalTime){
        this.arrivalTime = arrivalTime;
    }
    public float getArrivalTime() {
        return this.arrivalTime;
    }
    public void setExecuted(float executed) {
        this.executed = executed;
    }
    public float getExecuted(){
        return executed;
    }
    public void setRemainingTime(float remainingTime){
        this.remainingTime = remainingTime;
    }
    public float getRemainingTime(){
        return this.remainingTime;
    }
    public void setEndTime(float endTime){
        this.endTime = endTime;
    }
    public float getEndTime() {
        return endTime;
    }
    public void setBeginTime(float beginTime){
        this.beginTime = beginTime;
    }
    public float getBeginTime(){
        return this.beginTime;
    }

    public void setBurstTime(float burstTime) {
        this.burstTime = burstTime;
    }
    public float getBurstTime(){
        return this.burstTime;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public float getWaitTime() {
        return this.waitTime;
    }
    public void setWaitTime(float waitTime) {
        this.waitTime = waitTime;
    }
    public void changeWaitTime(float value) {
        this.waitTime = value;
    }

}
