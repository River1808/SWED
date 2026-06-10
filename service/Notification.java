package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import channels.CommunicationChannel;

/**
 * Represents a notification to be sent to a user.
 * Handles notification generation and delivery through communication channels.
 */
public class Notification {
    private String notifyID;
    private String message;
    private LocalDateTime timestamp;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new notification.
     * @param notifyID The unique identifier for this notification
     * @param message The notification message
     * @throws IllegalArgumentException if notifyID or message is null or empty
     */
    public Notification(String notifyID, String message) {
        this.notifyID = Objects.requireNonNull(notifyID, "Notification ID cannot be null");
        this.message = Objects.requireNonNull(message, "Message cannot be null");
        if (notifyID.trim().isEmpty()) throw new IllegalArgumentException("Notification ID cannot be empty");
        if (message.trim().isEmpty()) throw new IllegalArgumentException("Message cannot be empty");
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Generates a log entry for this notification.
     */
    public void generate() {
        System.out.println("Generating log entry for Notification ID: " + notifyID + " at " + timestamp.format(FORMATTER));
    }

    /**
     * Sends the notification through the specified communication channel.
     * @param channel The channel to deliver through
     */
    public void send(CommunicationChannel channel) {
        if (channel == null) throw new IllegalArgumentException("Channel cannot be null");
        channel.deliverMessage(this.message);
    }

    public String getNotifyID() { return notifyID; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
