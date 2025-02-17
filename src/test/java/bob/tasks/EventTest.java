package bob.tasks;

import bob.tasks.Event;
import bob.utils.DateUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventToString() {
        Event event = new Event("Project meeting", "2024-03-15 14:00", "2024-03-15 16:00");
        String expected = "[E] [ ] Project meeting (from: 2024-03-15 14:00 to: 2024-03-15 16:00)";
        assertEquals(expected, event.toString());

        event.markAsDone();
        expected = "[E] [X] Project meeting (from: 2024-03-15 14:00 to: 2024-03-15 16:00)";
        assertEquals(expected, event.toString());

    }


}