package observer;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an update event from a website.
 * Contains details about what was updated and when.
 */
public class UpdateEvent {
    private String websiteID;
    private String url;
    private LocalDateTime timestamp;
    private String message;

    public UpdateEvent(String websiteID, String url, LocalDateTime timestamp, String message) {
        this.websiteID = Objects.requireNonNull(websiteID, "Website ID cannot be null");
        this.url = Objects.requireNonNull(url, "URL cannot be null");
        this.timestamp = Objects.requireNonNull(timestamp, "Timestamp cannot be null");
        this.message = Objects.requireNonNull(message, "Message cannot be null");
    }

    public String getWebsiteID() { return websiteID; }
    public String getUrl() { return url; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
}
