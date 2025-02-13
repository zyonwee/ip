import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = DateUtils.checkIsDate(by);
        if (byDate != null){
            this.by = DateUtils.formatDate(this.byDate);
        }
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + super.toString() + " ( by: " + by + " )";
    }
}
