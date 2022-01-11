package scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Scheduler {

    public static int A, B, C;
    public static int numberOfTotalTasks;
    public static HashMap tasks;

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
            String[] split = str.split(" ");
            A = Integer.valueOf(split[0]);
            B = Integer.valueOf(split[1]);
            C = Integer.valueOf(split[2]);

            //read line 2
            str = br.readLine();
            numberOfTotalTasks = Integer.valueOf(str);

            //read line 3 and so on
            while ((str = br.readLine()) != null) {
                split = str.split(" ");
                Task task = new Task(split[0], split[1].charAt(0), Integer.valueOf(split[2]));
                tasks.putIfAbsent(split[0], task);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File is not found\n" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO Exception\n" + ex.getMessage());
        }
    }

}
