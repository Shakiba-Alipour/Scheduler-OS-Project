package scheduler;

public class Task {

    private final String name;
    private final char type; //X or Y or Z
    private int priority;
    private int executionTime; //needed time to completely execute the task
    private String state; //running, waiting or ready
    private int onCPUTime; //the time that the task is on cpu 

    public Task(String name, char type, int executionTime) {
        this.name = name;
        this.type = type;
        this.executionTime = executionTime;
        this.setPriority();
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

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return this.name;
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

    public String getState() {
        return this.state;
    }

    public int getOnCPUTime() {
        return this.onCPUTime;
    }
}
