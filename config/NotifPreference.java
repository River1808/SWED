package config;

import channels.CommunicationChannel;
import java.util.Objects;

/**
 * Represents user preferences for receiving notifications.
 * Specifies the frequency and channel for notifications.
 */
public class NotifPreference {
    private String frequency;
    private CommunicationChannel channel;

    public NotifPreference(String frequency, CommunicationChannel channel) {
        this.frequency = Objects.requireNonNull(frequency, "Frequency cannot be null");
        this.channel = Objects.requireNonNull(channel, "Channel cannot be null");
        if (frequency.trim().isEmpty()) throw new IllegalArgumentException("Frequency cannot be empty");
    }

    public String getFrequency() { return frequency; }
    public CommunicationChannel getChannel() { return channel; }

    public void setFrequency(String frequency) {
        if (frequency == null || frequency.trim().isEmpty()) throw new IllegalArgumentException("Frequency cannot be empty");
        this.frequency = frequency;
    }

    public void setChannel(CommunicationChannel channel) {
        if (channel == null) throw new IllegalArgumentException("Channel cannot be null");
        this.channel = channel;
    }
}