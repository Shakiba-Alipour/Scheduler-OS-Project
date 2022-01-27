package scheduler;

public class Task {

    private final String name;
    private int executionTime; // needed time to completely execute the task
    private String state; // running or ready
    private int onCPUTime; // the time that the task is on cpu

    public Task(String name, int executionTime) {
        this.name = name;
        this.executionTime = executionTime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return this.name;
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
