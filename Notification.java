
import java.util.Date;

public class Notification {
    private String notifyID;
    private String message;
    private Date timestamp;

    public Notification(String notifyID, String message) {
        this.notifyID = notifyID;
        this.message = message;
        this.timestamp = new Date();
    }

    public void generate() {
        System.out.println("Generating log entry for Notification ID: " + notifyID + " at " + timestamp);
    }

    public void send(CommunicationChannel channel) {
        channel.deliverMessage(this.message);
    }
}
