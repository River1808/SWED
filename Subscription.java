
public class Subscription {
    public String subID;
    public String url;
    public String status;
    public CommunicationChannel communicationType;

    public Subscription(String subID, String url, CommunicationChannel communicationType) {
        this.subID = subID;
        this.url = url;
        this.status = "ACTIVE";
        this.communicationType = communicationType;
    }
}
