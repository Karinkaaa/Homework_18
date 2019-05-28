package dom_parser;

public class Event {

    private String eventId;
    private String eventDate;
    private EventParameters eventParameters;

    public Event() {
        this.eventParameters = new EventParameters();
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String event_id) {
        this.eventId = event_id;
    }

    public String getEventDate() {
        return eventDate;
    }

    public EventParameters getEventParameters() {
        return eventParameters;
    }

    public void setEventParameters(EventParameters eventParameters) {
        this.eventParameters = eventParameters;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("\nEVENT:\n");
        sb.append("\nEvent ID: ").append(eventId).append("\nEvent date: ").append(eventDate).append(eventParameters);
        return sb.toString();
    }
}
