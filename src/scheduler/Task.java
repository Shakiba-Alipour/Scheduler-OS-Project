package scheduler;

public class Task {

    private final String name;
    private final char type; //X or Y or Z
    private int priority;
    private int executionTime; //needed time to completely execute the task
    private char[] resources; //needed resources for considered task
    private String state; //running, waiting or ready
    private int onCPUTime; //the time that the task is on cpu 

    public Task(String name, char type, int executionTime) {
        this.name = name;
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

    public char[] getResources() {
        return this.resources;
    }

    public String getState() {
        return this.state;
    }

    public int getOnCPUTime() {
        return this.onCPUTime;
    }
}
