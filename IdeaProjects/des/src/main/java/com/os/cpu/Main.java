package com.os.cpu;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Main {
    public static void main(String[] args) {
//write process.json
//processesJSON2.json
        ArrayList<PCB> pcbArray = new ArrayList<PCB>(9);
        pcbArray = create10Process();


        //get process count
        System.out.println("Enter number of processes: ");
        Scanner scanner;
        scanner = new Scanner(System.in);
        int processCount;
        processCount = scanner.nextInt();
        ArrayList<PCB> pcbArrayRandom = new ArrayList<PCB>(processCount);
        pcbArrayRandom = createRandomProcess(processCount);

        /*
        for(PCB pcb : pcbArray){
            pcb.print();
        }

         */
        /////
        //create static array
        /////////
        PCB [] pcb = pcbArray.toArray(new PCB[0]);
       //random
        PCB [] pcbRandom = pcbArrayRandom.toArray(new PCB[0]);
        //dynamically create array size of array list option
        //PCB [] pcbd = new PCB[pcbArray.size()];
        ////
        //fill static array
        ///
        //pcbArray = Arrays.asList(pcb);
        //check pcb
        for (int i = 0; i < pcbRandom.length; i++){
            pcbRandom[i].print();
        }
        //random check pcb
        for (int i = 0; i < pcb.length; i++){
            pcb[i].print();
        }
//Check FCFS
        System.out.println("\n\n\n FCFS TEST...\n\n\n");
        //test scheduler class
        FCFS f = new FCFS(pcb);
        //f.resultTable();
        //set values
        f.setnProcess(pcb);
        f.setTotalTime(pcb);
        f.setTotalBurstTime(pcb);
        f.setWaitTimes(pcb);
        f.setTurnAroundTimes(pcb);
        f.setCompletionTimes(pcb);
        //set Averages
        f.setAvgExecutionTime(pcb);
        f.setAvgBurstTime(pcb);
        f.setAvgWaitTime(pcb);
        f.setAverageTime(pcb);
        //run simulator
        f.run();
        //print tableS
        f.resultTable();
//round robin
        RR r = new RR(pcb);
        r.runn(pcb);
        r.run();
        r.resultTable(pcb);
//Shortest job First
        SJF s = new SJF(pcb);
        //sort
        s.sortPcb();
        //set 0
        s.setValues();
        s.run();
        s.resultTable();
//Shorest job First Preemptive
        SJFPremptive p = new SJFPremptive(pcb);
        p.findavgTimeAndResultTable(pcb, pcb.length);
        //System.out.println("\n\n\n FCFSPriorityQueue TEST...\n\n\n");
        //sort
        p.sortPcb();
        //set 0
        p.setValues();
        p.run();
        p.resultTable();
        //test scheduler class
        //FCFSPriorityQueue fp = new FCFSPriorityQueue(pcb);
        //f.resultTable();
        //fp.simpleResultTable();

    }
    public static ArrayList<PCB> createRandomProcess(int processCount){

        JSONArray processArrayList = new JSONArray();
        //Create user chosen # of processes
        for (int i = 0; i < processCount; i++) {
            JSONObject processDetailsObj0 = new JSONObject();
            //DecimalFormat numberFormat = new DecimalFormat("##.###");

            DecimalFormat numberFormat = new DecimalFormat("#");
            String processName = numberFormat.format(i);
            processDetailsObj0.put("ProcessN", processName);
            processDetailsObj0.put("waitTime", 0.000);

            Double randomExecutionTime = Math.random();
            processDetailsObj0.put("executionTime", randomExecutionTime);

            Double randomArrivalTime = Double.valueOf(i);
            processDetailsObj0.put("arrivalTime", randomArrivalTime);

            Double randomBurstTime = randomExecutionTime;
            processDetailsObj0.put("burstTime", randomExecutionTime);

            JSONObject jsonProcessObject0 = new JSONObject();
            jsonProcessObject0.put("process", processDetailsObj0);
            //Add employees to list
            processArrayList.add(jsonProcessObject0);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/randomProcesses.json")) {
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
        //
//JSON parser object to parse read file
        JSONParser jsonParser2 = new JSONParser();
        try (FileReader reader = new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/randomProcesses.json")) {
            //try (FileReader reader = new FileReader("employees.json"))
            //{
            //Read JSON file
            Object obj = jsonParser2.parse(reader);
            JSONArray processArrayList2 = (JSONArray) obj;
            processArrayList2.forEach(pro -> parseProcessObject((JSONObject) pro));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<PCB> pcbArray = new ArrayList<PCB>();
        try (FileReader jsonReader = new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/randomProcesses.json")) {
            //Read JSON file
            Object obj = jsonParser2.parse(jsonReader);
            JSONArray jsonArray = (JSONArray) obj;
            //PCB array to hold return
            pcbArray = new ArrayList<PCB>(processCount);
            //Iterate over process array
            //Lambda should use final so created constant copy
            List<PCB> finalPcbArray = pcbArray;
            jsonArray.forEach(json -> finalPcbArray.add(createProcessControlBlockFromJson((JSONObject) json)));
            //Print out and verify pcbArray which can now be given to scheduler
            pcbArray = finalPcbArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (ArrayList<PCB>) pcbArray;
    }
    public static ArrayList<PCB> create10Process(){
        //First Process
        JSONObject processDetailsObj0 = new JSONObject();
        processDetailsObj0.put("ProcessN", "0");
        processDetailsObj0.put("waitTime", 0.000);
        processDetailsObj0.put("executionTime", 1.000);
        processDetailsObj0.put("arrivalTime", 0.000);
        processDetailsObj0.put("burstTime", 1.000);

        JSONObject jsonProcessObject0 = new JSONObject();
        jsonProcessObject0.put("process", processDetailsObj0);

        //Second Process
        JSONObject processDetailsObj1 = new JSONObject();
        processDetailsObj1.put("ProcessN", "1");
        processDetailsObj1.put("waitTime", 0.000);
        processDetailsObj1.put("executionTime", 3.000);
        processDetailsObj1.put("arrivalTime", 2.000);
        processDetailsObj1.put("burstTime", 3.000);

        JSONObject jsonProcessObject1 = new JSONObject();
        jsonProcessObject1.put("process", processDetailsObj1);

        //Third Process
        JSONObject processDetailsObj2 = new JSONObject();
        processDetailsObj2.put("ProcessN", "2");
        processDetailsObj2.put("waitTime", 0.000);
        processDetailsObj2.put("executionTime", 6.000);
        processDetailsObj2.put("arrivalTime", 3.000);
        processDetailsObj2.put("burstTime", 6.000);

        JSONObject jsonProcessObject2 = new JSONObject();
        jsonProcessObject2.put("process", processDetailsObj2);
        //fourth Process
        JSONObject processDetailsObj3 = new JSONObject();
        processDetailsObj3.put("ProcessN", "3");
        processDetailsObj3.put("waitTime", 0.000);
        processDetailsObj3.put("executionTime", 10.000);
        processDetailsObj3.put("arrivalTime", 4.000);
        processDetailsObj3.put("burstTime", 10.000);

        JSONObject jsonProcessObject3 = new JSONObject();
        jsonProcessObject3.put("process", processDetailsObj3);

        //Fifth Process
        JSONObject processDetailsObj4 = new JSONObject();
        processDetailsObj4.put("ProcessN", "4");
        processDetailsObj4.put("waitTime", 0.000);
        processDetailsObj4.put("executionTime", 3.000);
        processDetailsObj4.put("arrivalTime", 5.000);
        processDetailsObj4.put("burstTime", 3.000);

        JSONObject jsonProcessObject4 = new JSONObject();
        jsonProcessObject4.put("process", processDetailsObj4);

        //Sixth Process
        JSONObject processDetailsObj5 = new JSONObject();
        processDetailsObj5.put("ProcessN", "5");
        processDetailsObj5.put("waitTime", 0.000);
        processDetailsObj5.put("executionTime", 2.000);
        processDetailsObj5.put("arrivalTime", 6.000);
        processDetailsObj5.put("burstTime", 2.000);

        JSONObject jsonProcessObject5 = new JSONObject();
        jsonProcessObject5.put("process", processDetailsObj5);
        //Seventh Process
        JSONObject processDetailsObj6 = new JSONObject();
        processDetailsObj6.put("ProcessN", "6");
        processDetailsObj6.put("waitTime", 0.000);
        processDetailsObj6.put("executionTime", 2.000);
        processDetailsObj6.put("arrivalTime", 7.000);
        processDetailsObj6.put("burstTime", 2.000);

        JSONObject jsonProcessObject6 = new JSONObject();
        jsonProcessObject6.put("process", processDetailsObj6);
        //Eigth Process
        JSONObject processDetailsObj7 = new JSONObject();
        processDetailsObj7.put("ProcessN", "7");
        processDetailsObj7.put("waitTime", 0.000);
        processDetailsObj7.put("executionTime", 2.000);
        processDetailsObj7.put("arrivalTime", 8.000);
        processDetailsObj7.put("burstTime", 2.000);

        JSONObject jsonProcessObject7 = new JSONObject();
        jsonProcessObject7.put("process", processDetailsObj7);
        //Ninth Process
        JSONObject processDetailsObj8 = new JSONObject();
        processDetailsObj8.put("ProcessN", "8");
        processDetailsObj8.put("waitTime", 0.000);
        processDetailsObj8.put("executionTime", 2.000);
        processDetailsObj8.put("arrivalTime", 9.000);
        processDetailsObj8.put("burstTime", 2.000);

        JSONObject jsonProcessObject8 = new JSONObject();
        jsonProcessObject8.put("process", processDetailsObj8);
        JSONArray processArrayList = new JSONArray();

        //Add employees to list
        processArrayList.add(jsonProcessObject0);
        processArrayList.add(jsonProcessObject1);
        processArrayList.add(jsonProcessObject2);
        processArrayList.add(jsonProcessObject3);
        processArrayList.add(jsonProcessObject4);
        processArrayList.add(jsonProcessObject5);
        processArrayList.add(jsonProcessObject6);
        processArrayList.add(jsonProcessObject7);
        processArrayList.add(jsonProcessObject8);


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
            //System.out.println(processArrayList2);
            //Iterate over process array
            /*
            for(Object proo : processArrayList2) {
                parseProcessObject((JSONObject) proo);
            }
             */
            processArrayList2.forEach(pro -> parseProcessObject((JSONObject) pro));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<PCB> pcbArray = new ArrayList<PCB>();
        try (FileReader jsonReader = new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/processesJSON2.json")) {
            //Read JSON file
            Object obj = jsonParser2.parse(jsonReader);
            JSONArray jsonArray = (JSONArray) obj;
            //PCB array to hold return
            pcbArray = new ArrayList<PCB>(9);
            //Iterate over process array
            //Lambda should use final so created constant copy
            List<PCB> finalPcbArray = pcbArray;
            jsonArray.forEach(json -> finalPcbArray.add(createProcessControlBlockFromJson((JSONObject) json)));
            //Print out and verify pcbArray which can now be given to scheduler
            /*
            System.out.println("Starting \"Verifying pcbArray and createPCB()\"...");
            int count = 0;

            for (PCB x : pcbArray) {
                System.out.println("Start PCB at index " + count + ": ");
                x.print();
                System.out.println("End PCB at index " + count);
                count += 1;
                pcbArray = finalPcbArray;
            }

             */
            pcbArray = finalPcbArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (ArrayList<PCB>) pcbArray;
    }

























    private static void parseProcessObject(JSONObject proc) {
        //Get
        JSONObject processObject = (JSONObject) proc.get("process");
        //Get
        String name = (String) processObject.get("ProcessN");
        //System.out.println(name);
        ///Get
        Double waitTime = (Double) processObject.get("waitTime");
        //System.out.println(waitTime);
        //Get
        Double executionTime = (Double) processObject.get("executionTime");
        //System.out.println(executionTime);
        //Get
        Double arrivalTime = (Double) processObject.get("arrivalTime");
        //System.out.println(arrivalTime);
        //Get
        Double burstTime = (Double) processObject.get("burstTime");
        //System.out.println(burstTime);
    }
    private static void setProcessControlBlock(JSONObject proc, PCB pcb) {
        //PCB pcb = new PCB();
        //Get
        System.out.println("Started setPCB()...");
        JSONObject processObject = (JSONObject) proc.get("process");
        //Get
        String name = (String) processObject.get("ProcessN");
        pcb.setName(name);
        //System.out.println("ProcessN: " + name + " , ");
        //Get
        Double waitTime = (Double) processObject.get("waitTime");
        pcb.setWaitTime(waitTime);
        //System.out.println("waitTime: " + waitTime + " , ");
        //Get
        Double executionTime = (Double) processObject.get("executionTime");
        pcb.setExecuted(executionTime);
       // System.out.println("executionTime: " + executionTime + " , ");
        //Get
        Double arrivalTime = (Double) processObject.get("arrivalTime");
        pcb.setArrivalTime(arrivalTime);
        //System.out.println("arrivalTime: " + arrivalTime + " , ");
        //Get
        Double burstTime = (Double) processObject.get("burstTime");
        pcb.setBurstTime(burstTime);
        //System.out.println("burstTime: " + burstTime + " , ");
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
        //System.out.println("ProcessN: " + name + " , ");
        //Get
        Double waitTime = (Double) processObject.get("waitTime");
        pcb.setWaitTime(waitTime);
       // System.out.println("waitTime: " + waitTime + " , ");
        //Get
        Double executionTime = (Double) processObject.get("executionTime");
        pcb.setExecuted(executionTime);
        //System.out.println("executionTime: " + executionTime + " , ");
        //Get
        Double arrivalTime = (Double) processObject.get("arrivalTime");
        pcb.setArrivalTime(arrivalTime);
        //System.out.println("arrivalTime: " + arrivalTime + " , ");
        //Get
        Double burstTime = (Double) processObject.get("burstTime");
        pcb.setBurstTime(burstTime);
        //System.out.println("burstTime: " + burstTime + " , ");
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

