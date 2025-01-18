public class Task {
    protected String description;
    protected boolean isDone; // State of Task

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets a task as done
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Sets a task as not done
     */
    public void unmarkAsDone(){
        this.isDone = false;
    }
}
