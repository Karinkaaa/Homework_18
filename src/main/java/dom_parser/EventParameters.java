package dom_parser;

public class EventParameters {

    private String priority;
    private String log_level;
    private String source;

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLogLevel() {
        return log_level;
    }

    public void setLogLevel(String log_level) {
        this.log_level = log_level;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("\nEvent parameters:");
        sb.append("\n\tPriority: ").append(priority).append("\n\tLog level: ").append(log_level);
        sb.append("\n\tSource: ").append(source).append("\n");
        return sb.toString();
    }
}
