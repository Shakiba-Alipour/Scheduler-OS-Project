package scheduler;

import java.util.LinkedList;
import java.util.Queue;

public class State {

    private Queue ready;
    private Queue waiting;
    private Task running;

    public State() {
        this.ready = new LinkedList<Task>();
        this.waiting = new LinkedList<Task>();
    }
    
    
}
