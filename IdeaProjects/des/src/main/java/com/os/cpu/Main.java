package com.os.cpu;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Main {
    public static void main(String[] args) {
//write process.json
/*
//processesJSON2.json
*/
        //First Process
        JSONObject processDetailsObj0 = new JSONObject();
        processDetailsObj0.put("ProcessN", "1");
        processDetailsObj0.put("waitTime", 0.000);
        processDetailsObj0.put("executionTime", 2.435);
        processDetailsObj0.put("arrivalTime", 0.000);
        processDetailsObj0.put("burstTime", 3.767);

        JSONObject jsonProcessObject0 = new JSONObject();
        jsonProcessObject0.put("process", processDetailsObj0);

        //Second Process
        JSONObject processDetailsObj1 = new JSONObject();
        processDetailsObj1.put("ProcessN", "2");
        processDetailsObj1.put("waitTime", 0.000);
        processDetailsObj1.put("executionTime", 4.763);
        processDetailsObj1.put("arrivalTime", 0.000);
        processDetailsObj1.put("burstTime", 1.904);

        JSONObject jsonProcessObject1 = new JSONObject();
        jsonProcessObject1.put("process", processDetailsObj1);

        //Third Process
        JSONObject processDetailsObj2 = new JSONObject();
        processDetailsObj2.put("ProcessN", "3");
        processDetailsObj2.put("waitTime", 0.000);
        processDetailsObj2.put("executionTime", 3.768);
        processDetailsObj2.put("arrivalTime", 0.000);
        processDetailsObj2.put("burstTime", 3.768);

        JSONObject jsonProcessObject2 = new JSONObject();

        jsonProcessObject2.put("process", processDetailsObj2);
        //Add employees to list
        JSONArray processArrayList = new JSONArray();
        processArrayList.add(jsonProcessObject0);
        processArrayList.add(jsonProcessObject1);
        processArrayList.add(jsonProcessObject2);

        //Write JSON file
        try (FileWriter file = new FileWriter("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/processesJSON2.json")) {
            //try (FileWriter file = new FileWriter("employees.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(processArrayList.toJSONString());
            file.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
// read processesJSON2.json
 */
//JSON parser object to parse read file
        JSONParser jsonParser2 = new JSONParser();

        try (FileReader reader = new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/processesJSON2.json")) {
            //try (FileReader reader = new FileReader("employees.json"))
            //{
            //Read JSON file
            Object obj = jsonParser2.parse(reader);
            JSONArray processArrayList2 = (JSONArray) obj;
            System.out.println(processArrayList2);
            //Iterate over process array
            processArrayList2.forEach(pro -> parseProcessObject((JSONObject) pro));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<PCB> pcbAL = new ArrayList<>();
        Queue<PCB> rQueue = new PriorityQueue<>();
        Queue<PCB> wQueue = new PriorityQueue<>();
        Queue<PCB> aQueue = new PriorityQueue<>();
        //Contents of array 'pcbArr' are written to, but never read
        /* Example JSON
          {"executionTime":3.767,"arrivalTime":0.0,"burstTime":3.767,"processN":"Process0","waitTime":0.0}
          {"executionTime":1.904,"arrivalTime":4.763,"burstTime":1.904,"processN":"Process1","waitTime":0.0}
          {"executionTime":3.767,"arrivalTime":0.0,"burstTime":3.767,"processN":"Process2","waitTime":0.0}
         */
        //Create 3 PCB to hold the processes
        PCB pcb0 = new PCB();
        PCB pcb1 = new PCB();
        PCB pcb2 = new PCB();
        //Create PCB array for easy looping
        List<PCB> pcbArrList = new ArrayList<PCB>();
        pcbArrList.add(pcb0);//pcb0); //=pcb0;
        pcbArrList.add(pcb1); //=pcb1;
        pcbArrList.add(pcb2); //=pcb2;
        for (PCB x : pcbArrList) {
            System.out.println("Starting \"Check PCB Object\"...");
            x.print();
            System.out.println("Ending \"Check PCB Object\"...");
        }
    /*
    JSONObject jObj = jsonParser2.parse(reader);
        for(PCB y : pcbArrList){
            setProcessControlBlock(y);
        }
*/
        JSONParser jsonParser3 = new JSONParser();

        List<PCB> pcbArray = null;
        try (FileReader jsonReader = new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/processesJSON2.json")) {
            //try (FileReader reader = new FileReader("employees.json"))
            //{
            //Read JSON file
            Object obj = jsonParser2.parse(jsonReader);
            //JSONObject jsonObj = (JSONObject) obj;
            //System.out.println(jsonObj);
            JSONArray jsonArray = (JSONArray) obj;
            //PCB array to hold return
            pcbArray = new ArrayList<PCB>();
            //Iterate over process array
            //Lambda should use final so created constant copy
            List<PCB> finalPcbArray = pcbArray;
            jsonArray.forEach(json -> finalPcbArray.add(createProcessControlBlockFromJson((JSONObject) json)));
            //Print out and verify pcbArray which can now be given to scheduler
            System.out.println("Starting \"Verifying pcbArray and createPCB()\"...");
            int count = 0;
            for (PCB x : pcbArray) {
                System.out.println("Start PCB at index " + count + ": ");
                x.print();
                System.out.println("End PCB at index " + count);
                count += 1;
                pcbArray = finalPcbArray;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(PCB pcb : pcbArray){
            pcb.print();
        }
        System.out.println("\n\n\n FCFS TEST...\n\n\n");
        /////
        //create static array
        /////////
        PCB [] pcb = new PCB[3];
        //dynamically create array size of array list option
        //PCB [] pcbd = new PCB[pcbArray.size()];
        ////
        //fill static array
        ///
        int i = 0;
        for(PCB j : pcbArray){
            pcb[i] = j;
            i++;
        }
        //test scheduler class
        FCFS f = new FCFS(pcb);
        //f.resultTable();

        f.simpleResultTable();

    }
    private static void parseProcessObject(JSONObject proc) {
        //Get
        JSONObject processObject = (JSONObject) proc.get("process");
        //Get
        String name = (String) processObject.get("ProcessN");
        System.out.println(name);
        //Get
        Double waitTime = (Double) processObject.get("waitTime");
        System.out.println(waitTime);
        //Get
        Double executionTime = (Double) processObject.get("executionTime");
        System.out.println(executionTime);
        //Get
        Double arrivalTime = (Double) processObject.get("arrivalTime");
        System.out.println(arrivalTime);
        //Get
        Double burstTime = (Double) processObject.get("burstTime");
        System.out.println(burstTime);
    }
    private static void setProcessControlBlock(JSONObject proc, PCB pcb) {
        //PCB pcb = new PCB();
        //Get
        System.out.println("Started setPCB()...");
        JSONObject processObject = (JSONObject) proc.get("process");
        //Get
        String name = (String) processObject.get("ProcessN");
        pcb.setName(name);
        System.out.println("ProcessN: " + name + " , ");
        //Get
        Double waitTime = (Double) processObject.get("waitTime");
        pcb.setWaitTime(waitTime);
        System.out.println("waitTime: " + waitTime + " , ");
        //Get
        Double executionTime = (Double) processObject.get("executionTime");
        pcb.setExecuted(executionTime);
        System.out.println("executionTime: " + executionTime + " , ");
        //Get
        Double arrivalTime = (Double) processObject.get("arrivalTime");
        pcb.setArrivalTime(arrivalTime);
        System.out.println("arrivalTime: " + arrivalTime + " , ");
        //Get
        Double burstTime = (Double) processObject.get("burstTime");
        pcb.setBurstTime(burstTime);
        System.out.println("burstTime: " + burstTime + " , ");
        //Verify
        System.out.println("Successfully Created from JSON!");
    }
    private static PCB createProcessControlBlockFromJson(JSONObject proc) {
        //
        System.out.println("Starting createPCBFromJSON...");
        // create new pcb
        PCB pcb = new PCB();
        //Get
        JSONObject processObject = (JSONObject) proc.get("process");
        //Get
        String name = (String) processObject.get("ProcessN");
        pcb.setName(name);
        System.out.println("ProcessN: " + name + " , ");
        //Get
        Double waitTime = (Double) processObject.get("waitTime");
        pcb.setWaitTime(waitTime);
        System.out.println("waitTime: " + waitTime + " , ");
        //Get
        Double executionTime = (Double) processObject.get("executionTime");
        pcb.setExecuted(executionTime);
        System.out.println("executionTime: " + executionTime + " , ");
        //Get
        Double arrivalTime = (Double) processObject.get("arrivalTime");
        pcb.setArrivalTime(arrivalTime);
        System.out.println("arrivalTime: " + arrivalTime + " , ");
        //Get
        Double burstTime = (Double) processObject.get("burstTime");
        pcb.setBurstTime(burstTime);
        System.out.println("burstTime: " + burstTime + " , ");
        //Verify
        System.out.println("Successfully Created from JSON!");
        //return new PCB
        return pcb;
    }

    private static void jsonCreate1() {

/*
//processesJSON1
*/
        //First Process
        JSONObject processDetails0 = new JSONObject();
        processDetails0.put("ProcessN", "Process0");
        processDetails0.put("waitTime", 0.000);
        processDetails0.put("executionTime", 3.767);
        processDetails0.put("arrivalTime", 0.000);
        processDetails0.put("burstTime", 3.767);
        //Second Process
        JSONObject processDetails1 = new JSONObject();
        processDetails1.put("ProcessN", "Process1");
        processDetails1.put("waitTime", 0.000);
        processDetails1.put("executionTime", 1.904);
        processDetails1.put("arrivalTime", 4.763);
        processDetails1.put("burstTime", 1.904);
        // Third Process
        JSONObject processDetails2 = new JSONObject();
        processDetails2.put("ProcessN", "Process2");
        processDetails2.put("waitTime", 0.000);
        processDetails2.put("executionTime", 3.768);
        processDetails2.put("arrivalTime", 0.000);
        processDetails2.put("burstTime", 3.768);
        //Add processes to list
        JSONArray processList = new JSONArray();
        processList.add(processDetails0);
        processList.add(processDetails1);
        processList.add(processDetails2);
        //Write JSON file
        try (FileWriter file = new FileWriter("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/processesJSON1.json")) {
            //try (FileWriter file = new FileWriter("processes.json")) {
            file.write(processList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

