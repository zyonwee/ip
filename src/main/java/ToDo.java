/**
 * ToDo has only description
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     *
     * @return String of 'ToDo[T] description'
     */
    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + super.toString();
    }
}
