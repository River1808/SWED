
package core;

import channels.CommunicationChannel;
import java.util.Objects;

/**
 * Represents a user's subscription to a website.
 * Tracks the subscription details and associated communication channel.
 */
public class Subscription {
    private String subID;
    private String url;
    private String status;
    private CommunicationChannel communicationChannel;

    public Subscription(String subID, String url, CommunicationChannel communicationChannel) {
        this.subID = Objects.requireNonNull(subID, "Subscription ID cannot be null");
        this.url = Objects.requireNonNull(url, "URL cannot be null");
        this.communicationChannel = Objects.requireNonNull(communicationChannel, "Communication channel cannot be null");
        if (subID.trim().isEmpty()) throw new IllegalArgumentException("Subscription ID cannot be empty");
        if (url.trim().isEmpty()) throw new IllegalArgumentException("URL cannot be empty");
        this.status = "ACTIVE";
    }

    public String getSubID() { return subID; }
    public String getUrl() { return url; }
    public String getStatus() { return status; }
    public CommunicationChannel getCommunicationChannel() { return communicationChannel; }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) throw new IllegalArgumentException("Status cannot be empty");
        this.status = status;
    }
}
