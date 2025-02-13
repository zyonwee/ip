import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDate  fromDate = null;
    protected LocalDate  toDate = null;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.toDate = DateUtils.checkIsDate(from);
        if (fromDate != null){
            this.from = DateUtils.formatDate(this.fromDate);
        }
        this.toDate = DateUtils.checkIsDate(to);
        if (toDate != null){
            this.to = DateUtils.formatDate(this.toDate);
        }
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + super.toString() + " ( from:" + from + " to : " + to + ")";
    }
}
