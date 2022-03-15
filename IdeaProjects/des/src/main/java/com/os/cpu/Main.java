package com.os.cpu;

import java.io.IOException;
import java.io.FileReader;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        JSONArray proc;
        try {
            //Object obj = parser.parse(new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/crunchify.json"));
            Object obj = (Object) parser.parse(new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/process.json"));
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/process.json"));
            JSONObject parser2 = (JSONObject) obj;
            JSONObject jsonObject2 = parser2;
                    // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            /* Array
              JSONParser parser = new JSONParser();
              Object obj  = parser.parse(content);
              JSONArray array = new JSONArray();
              array.add(obj);
             */
            /* No array
              JSONParser parser = new JSONParser();
              Object obj  = parser.parse(content);
             */
            /* direct fix
            JSONObject unitsObj = parser.parse(new FileReader("file.json");
             */
            JSONObject jsonObject3 = (JSONObject) obj;
            // A JSON array. JSONObject supports java.util.List interface.
            //JSONArray companyList = (JSONArray) jsonObject.get("Company List");
            proc = (JSONArray) jsonObject.get("process");
            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            /*
            //a
            Iterator<JSONObject> iterator = proc.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            */
            //b
            for (JSONObject object : (Iterable<JSONObject>) proc) {
                System.out.println(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<PCB> pcbAL = new ArrayList<>();
        Queue<PCB> readyQueue = new PriorityQueue<>();
        PCB[] pcbArr = new PCB[3];
        System.out.println("Hello world!");
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
        pcbArr[0] = pcb0;
        pcbArr[1] = pcb1;
        pcbArr[2] = pcb2;


//READ FILE 1
        try {
            HashMap<String, String> procHM = new HashMap<String, String>();
            //pull processes from file
            Object objP = (Object) parser.parse(new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/process.json"));
            //copy
            JSONObject jsonObjectP = (JSONObject) objP;
            //create JSON ArrayList
            JSONArray procP = (JSONArray) jsonObjectP.get("process");
            //Iterate over file
            Iterator<JSONObject> iteratorP = procP.iterator();
            try { //}
                while (iteratorP.hasNext()) {
                    System.out.println(iteratorP.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

//READ FILE 2
        //JSON parser object to parse read file
        JSONParser jsonParser = new
                JSONParser();

        try (FileReader reader = new FileReader("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/des/src/main/java/com/os/cpu/process.json")) {
            //Read JSON file
            Object pcbObj = jsonParser.parse(reader);
            JSONArray pcbList = (JSONArray) pcbObj;
            System.out.println(pcbList);
            //Iterate over employee array
            pcbList.forEach(pcbEmp -> parseProcessObject((JSONObject) pcbEmp));

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }



        private static void parseProcessObject(JSONObject procc){

            JSONObject pcbJ0 = (JSONObject) procc.get("process");

            String name = (String) pcbJ0.get("processN");
            System.out.println(name);

            float waitTime = (float) pcbJ0.get("waitTime");
            System.out.println(waitTime);

            float executionTime = (float) pcbJ0.get("executionTime");
            System.out.println(executionTime);

            float arrivalTime = (float) pcbJ0.get("arrivalTime");
            System.out.println(arrivalTime);

            float burstTime = (float) pcbJ0.get("burstTime");
            System.out.println(burstTime);
        }
        private static void parseEmployeeObject(JSONObject employee)
        {
            //Get employee object within list
            JSONObject employeeObject = (JSONObject) employee.get("employee");

            //Get employee first name
            String firstName = (String) employeeObject.get("firstName");
            System.out.println(firstName);

            //Get employee last name
            String lastName = (String) employeeObject.get("lastName");
            System.out.println(lastName);

            //Get employee website name
            String website = (String) employeeObject.get("website");
            System.out.println(website);
    }
}


/*
  import org.json.simple.JSONArray;
  import org.json.simple.JSONObject;
  import org.json.simple.parser.JSONParser;
  <p>
  import java.io.FileReader;
  import java.util.Iterator;
  <p>
 
   * @author Crunchify.com
   * How to Read JSON Object From File in Java?
  <p>
 public class CrunchifyJSONReadFromFile {
 *
    @SuppressWarnings("unchecked")
 *

    public static void main(String[] args) {
 
 
 */