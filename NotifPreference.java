package de.frauas.websitemonitor;

public class NotifPreference {
    public String frequency;
    public CommunicationChannel channel;

    public NotifPreference(String frequency, CommunicationChannel channel) {
        this.frequency = frequency;
        this.channel = channel;
    }
}
