import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public Task markAsDone(){
        this.isDone = true;
        return  this;
    }

    /**
     * Sets a task as not done
     */
    public Task unmarkAsDone(){
        this.isDone = false;
        return this;
    }


    /**
     * Sets a task as done
     */
    public void saveTask() throws  BobException{
            BobFileManager.writeToFile(this.toString());
    }

    /**
     *
     * @return String of '[Status] description'
     */
    @Override
    public String toString() {
        return this.description;
    }

}
