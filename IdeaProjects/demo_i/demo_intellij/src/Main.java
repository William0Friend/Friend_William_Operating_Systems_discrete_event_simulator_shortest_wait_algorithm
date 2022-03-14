public class Main {
    /**
     * - invalidArguments(): void
     * - invalidFile(): void
     * - openAndPrepareProcess(filename: String): void
     * - normalDistribution(nProcess: int, mean: float, standardDeviation: float) : void
     * + main(args: String[]): void
     *
     *
     * @param args
     */

    /**
Initialized scheduler with 3 processes.
Policy: Round Robin
Quantum: 3.0
-> Context Change at time = 9.329
---------------------------------------
| Exits            | Enters           |
---------------------------------------
| Process0         | Process1         |
---------------------------------------
-> Context Change at time = 11.233
---------------------------------------
| Exits            | Enters           |
---------------------------------------
| Process1         | Process2         |
---------------------------------------
---------------------------------------------------------------------------
| Process        | Wait Time | Execution Time | Arrival Time | Burst Time |
---------------------------------------------------------------------------
|Process0        |0.000      |3.767           |0.000         |3.767       |
|Process1        |0.000      |1.904           |4.763         |1.904       |
|Process2        |1.904      |5.735           |9.329         |3.831       |
+++++++++++++++++++++++++++++++++++++++++++++
|Average         |3.744      |6.912           |Total Burst   |9.502       |
---------------------------------------------------------------------------
     */
    private void invalidArguments(){
        //implement
    }
    private void invalidFile(){
        //implement
        BufferedReader br = new BufferedReader(new FileReader("process.json"));
    }
    private void  openAndPrepareProcess(String filename){
        //implement
    }
    private void normalDistribution(int nProcess, float mean, float standardDeviation){
        //implement
    }
    public static void main(String[] args) {
        //implement
        System.out.println("Hello world!");

  }
}