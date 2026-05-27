package de.frauas.websitemonitor;

public class CommunicationChannel {
    private String channelType;

    public CommunicationChannel(String channelType) {
        this.channelType = channelType;
    }

    public void deliverMessage(String message) {
        // Mocked implementation of sending a message
        System.out.println("[" + channelType.toUpperCase() + " Delivery]: " + message);
    }

    public String getChannelType() {
        return channelType;
    }
}
