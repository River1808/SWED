package channels;

import java.util.Objects;

/**
 * Represents a communication channel for delivering notifications.
 * Supports various delivery methods like Email, SMS, etc.
 */
public class CommunicationChannel {
    private String channelType;

    /**
     * Creates a new communication channel.
     * @param channelType The type of channel (e.g., "Email", "SMS")
     * @throws IllegalArgumentException if channelType is null or empty
     */
    public CommunicationChannel(String channelType) {
        this.channelType = Objects.requireNonNull(channelType, "Channel type cannot be null");
        if (channelType.trim().isEmpty()) throw new IllegalArgumentException("Channel type cannot be empty");
    }

    /**
     * Delivers a message through this communication channel.
     * @param message The message to deliver
     */
    public void deliverMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.err.println("[" + channelType.toUpperCase() + " Delivery]: Warning - Empty message");
            return;
        }
        System.out.println("[" + channelType.toUpperCase() + " Delivery]: " + message);
    }

    public String getChannelType() {
        return channelType;
    }
}