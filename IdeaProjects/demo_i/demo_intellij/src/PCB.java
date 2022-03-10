import java.util.*;
import com.github.cliftonlabs.json_simple.*;

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
    private float remainingTime;
    private float executed;
    private float beginTime;
    private float endTime;
    private float waitTime;

    //clock
    private int clock = 0;
    private int clockcycles = 0;

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
     */
    public void getArrivalTime(int arrivalTime) {
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
    public PCB(String name, float burstTime, float arrivalTime){
        this.name = name;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
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
