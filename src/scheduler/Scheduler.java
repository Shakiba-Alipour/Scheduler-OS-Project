package scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Scheduler {

    public static int numberOfTotalTasks;
    public static HashMap<String, Task> tasks;
    public static LinkedList<Task> readyQueue;

    public static void main(String[] args) {
        File file = new File("input.txt");
        if (!file.exists()) {
            System.out.println("File does not exist!");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            tasks = new HashMap<String, Task>();

            // read line 1
            String str = br.readLine();
            numberOfTotalTasks = Integer.valueOf(str);

            // read line 2 and so on
            int i = 0;
            while ((str = br.readLine()) != null && i < numberOfTotalTasks) {
                String[] split = str.split(" ");
                Task task = new Task(split[0], split[1].charAt(0), Integer.valueOf(split[2]));
                tasks.putIfAbsent(split[0], task);
                i++;
            }

            br.close();

            // fill the ready queue based on desired algorithm and print it
            readyQueue = FCFS();
            printQueue("FCFS");
            readyQueue = SJF();
            printQueue("SJF");
            readyQueue = RR();
            printQueue("RR");
            readyQueue = HRRN();
            printQueue("HRRN");

        } catch (FileNotFoundException ex) {
            System.out.println("File is not found\n" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO Exception\n" + ex.getMessage());
        }
    }

    public static void printQueue(String algorithmName) {
        System.out.println("Algorithm: " + algorithmName);
        while (!readyQueue.isEmpty()) {
            // run first process of the ready list
            Task running = readyQueue.pop();
            running.setState("running");
            System.out.println("Running Process: " + running.getName());
            // print current processes in the ready queue
            System.out.println("Ready Queue:");
            for (int idx = 0; idx < readyQueue.size(); idx++) {
                System.out.print(((Task) readyQueue.get(idx)).getName());
            }
        }
    }

    public static LinkedList<Task> SJF() {
        LinkedList<Task> ready = new LinkedList<Task>();
        for (Object key : tasks.keySet()) {
            // if first process enters the ready queue
            if (ready.isEmpty()) {
                tasks.get(key).setState("ready");
                ready.add(tasks.get(key));
            } // if ready queue is not empty
            else {
                // find proper place for new process
                int i = 0;
                for (; (((Task) ready.get(i)).getExecutionTime() < ((Task) tasks.get(key)).getExecutionTime())
                        && (i < ready.size()); i++) {
                }
                // set process in proper place
                tasks.get(key).setState("ready");
                ready.add(i - 1, tasks.get(key));
            }
        }
        return ready;
    }

    public static LinkedList<Task> FCFS() {
        LinkedList<Task> ready = new LinkedList<Task>();
        for (Object key : tasks.keySet()) {
            tasks.get(key).setState("ready");
            ready.add(tasks.get(key));
        }
        return ready;
    }

    public static LinkedList<Task> RR() {
        LinkedList<Task> ready = new LinkedList<>();
        int level = 1; // to check if a process is completed or not
        while (true) {
            int completed = 0;
            for (Object key : tasks.keySet()) {
                if (((Task) tasks.get(key)).getExecutionTime() - level > 0) {
                    tasks.get(key).setState("ready");
                    ready.add(tasks.get(key));
                } else {
                    completed++;
                }
            }
            // if all of processes are completed
            if (completed == tasks.size()) {
                break;
            }
            // if some of processes are not completed yet
            level++;
        }
        return ready;
    }

    public static LinkedList<Task> HRRN() {
        LinkedList<Task> ready = new LinkedList<>();
        // first column: process index in hash map
        // second column: response ratio
        int[][] responseRatio = new int[tasks.size()][2];
        for (int i = 0; i < tasks.size(); i++) {
            // ratio = (waiting time / execution time) + 1
            int ratio;
        }
        return ready;
    }
}
