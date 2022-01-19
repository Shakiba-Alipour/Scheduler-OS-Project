package scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {

    public static int numberOfTotalTasks;
    public static HashMap tasks;
    public static LinkedList readyQueue;

    public static void main(String[] args) {
        File file = new File("input.txt");
        if (!file.exists()) {
            System.out.println("File does not exist!");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            tasks = new HashMap<String, Task>();

            //read line 1
            String str = br.readLine();
            numberOfTotalTasks = Integer.valueOf(str);

            //read line 2 and so on
            int i = 0;
            while ((str = br.readLine()) != null && i < numberOfTotalTasks) {
                String[] split = str.split(" ");
                Task task = new Task(split[0], split[1].charAt(0), Integer.valueOf(split[2]));
                tasks.putIfAbsent(split[0], task);
                i++;
            }

            //fill the ready queue based on desired algorithm
            readyQueue = new LinkedList<>();

        } catch (FileNotFoundException ex) {
            System.out.println("File is not found\n" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO Exception\n" + ex.getMessage());
        }
    }

    public static LinkedList SJF() {
        LinkedList ready = new LinkedList<>();
        for (Object key : tasks.keySet()) {
            //if first process enters the ready queue
            if (ready.isEmpty()) {
                ready.add(tasks.get(key));
            } //if ready queue is not empty
            else {
                //find proper place for new process
                int i = 0;
                for (; (((Task) ready.get(i)).getExecutionTime()
                        < ((Task) tasks.get(key)).getExecutionTime())
                        && (i < ready.size()); i++) {
                }
                //set process in proper place
                ready.add(i - 1, tasks.get(key));
            }
        }
        return ready;
    }
}
