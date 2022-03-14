package org.example;
import java.io.*;
import java.nio.*;
public class Test0 {
    File f = new File("/home/break/tools/gits/des/Friend_William_Operating_Systems_discrete_event_simulator_shortest_wait_algorithm/IdeaProjects/test/src/main/java/org/example/process.json");
    BufferedReader buf = new BufferedReader(new FileReader(f));
    String line = "";

   while () {
        //if (buf.readLine() != null) {
        try {
            line = buf.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("json file line " + line);
            // do your changes
        //}
    }

    public Test0() throws FileNotFoundException {
    }
}
