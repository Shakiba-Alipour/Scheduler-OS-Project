/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

/**
 *
 * @author Shakiba
 */
public class Task {

    private final char type; //X or Y or Z
    private int priority;
    private int executionTime; //needed time to completely execute the task
    private char[] resources; //needed resources for considered task

    public Task(char type, int executionTime) {
        this.type = type;
        this.executionTime = executionTime;
        this.setPriority();
        this.setResources();
    }

    private void setPriority() {
        if (this.type == 'X') {
            this.priority = 3;
        } else if (this.type == 'Y') {
            this.priority = 2;
        } else if (this.type == 'Z') {
            this.priority = 1;
        }
    }

    private void setResources() {
        this.resources = new char[2];
        if (this.type == 'X') {
            this.resources[0] = 'A';
            this.resources[1] = 'B';
        } else if (this.type == 'Y') {
            this.resources[0] = 'B';
            this.resources[1] = 'C';
        } else if (this.type == 'Z') {
            this.resources[0] = 'A';
            this.resources[1] = 'C';
        }
    }

    public char getType() {
        return this.type;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getExecutionTime() {
        return this.executionTime;
    }

    public char[] getResources() {
        return this.resources;
    }
}
