package scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Scheduler {

    public static int numberOfTotalTasks;
    public static LinkedList<Task> readyQueue;

    public static void main(String[] args) {
        File file = new File("input.txt");
        if (!file.exists()) {
            System.out.println("File does not exist!");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Task> tasks = new ArrayList<Task>();

            // read line 1
            String str = br.readLine();
            numberOfTotalTasks = Integer.valueOf(str);

            // read line 2 and so on
            int i = 0;
            while ((str = br.readLine()) != null && i < numberOfTotalTasks) {
                String[] split = str.split(" ");
                Task task = new Task(split[0], Integer.valueOf(split[1]));
                tasks.add(task);
                i++;
            }

            br.close();

            // fill the ready queue based on desired algorithm and print it
            readyQueue = FCFS(tasks);
            printQueue("FCFS");
            readyQueue = SJF(tasks);
            printQueue("SJF");
            readyQueue = RR(tasks);
            printQueue("RR");
            readyQueue = HRRN(tasks);
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
                System.out.print(((Task) readyQueue.get(idx)).getName() + "   ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------");
    }

    public static LinkedList<Task> SJF(ArrayList<Task> tasks) {
        LinkedList<Task> ready = new LinkedList<Task>();
        // processes enter the ready queue
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setState("ready");
            ready.add(tasks.get(i));
        }
        // find proper place for each process
        for (int i = 0; i < ready.size() - 1; i++) {
            for (int j = 0; j < ready.size() - i - 1; j++) {
                if (ready.get(j).getExecutionTime() > ready.get(j + 1).getExecutionTime()) {
                    // set process in proper place
                    Task temp = ready.get(j);
                    ready.set(j, ready.get(j + 1));
                    ready.set(j + 1, temp);
                }
            }
        }
        return ready;
    }

    public static LinkedList<Task> FCFS(ArrayList<Task> tasks) {
        LinkedList<Task> ready = new LinkedList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setState("ready");
            ready.add(tasks.get(i));
        }
        return ready;
    }

    public static LinkedList<Task> RR(ArrayList<Task> tasks) {
        LinkedList<Task> ready = new LinkedList<>();
        int level = 0; // to check if a process is completed or not
        while (true) {
            int completed = 0;
            for (int i = 0; i < tasks.size(); i++) {
                if (((Task) tasks.get(i)).getExecutionTime() - level > 0) {
                    tasks.get(i).setState("ready");
                    ready.add(tasks.get(i));
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

    public static LinkedList<Task> HRRN(ArrayList<Task> tasks) {
        LinkedList<Task> ready = new LinkedList<>();
        int[] responseRatio = new int[tasks.size()];
        int[] waitingTime = new int[tasks.size()];
        // arrival time for each process = its index in tasks array list
        int sumBurstTime = 0;
        // find waiting time
        waitingTime[0] = 0;
        System.out.println("Waiting Time:\nw[1] = " + waitingTime[0]);
        for (int i = 1; i < tasks.size(); i++) {
            // arrival time for each process = its index in tasks array list
            sumBurstTime += tasks.get(i - 1).getExecutionTime();
            waitingTime[i] = sumBurstTime - i;
            System.out.println("w[" + (i + 1) + "] = " + waitingTime[i]);
        }
        System.out.println("-------------");
        // find response ratio and fill ready queue
        for (int i = 0; i < tasks.size(); i++) {
            // ratio = (waiting time / execution time) + 1
            responseRatio[i] = (waitingTime[i] / tasks.get(i).getExecutionTime()) + 1;
            System.out.println("r[" + (i + 1) + "] = " + responseRatio[i]);
            ready.add(tasks.get(i));
        }
        System.out.println("-------------");
        // sort ready queue
        for (int i = 0; i < ready.size() - 1; i++) {
            for (int j = 0; j < ready.size() - i - 1; j++) {
                if (responseRatio[j] > responseRatio[j + 1]) {
                    // set process in proper place
                    Task temp = ready.get(j);
                    ready.set(j, ready.get(j + 1));
                    ready.set(j + 1, temp);
                }
            }
        }
        return ready;
    }
}
