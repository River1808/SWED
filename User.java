
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;
    private String name;
    private String email;
    
    // Relationships managed via collections matching multiplicities
    private List<Subscription> subscriptions;
    private List<NotifPreference> preferences;

    public User(String userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.subscriptions = new ArrayList<>();
        this.preferences = new ArrayList<>();
    }

    public void register() {
        System.out.println("User " + name + " (" + userID + ") has successfully registered to the monitoring platform.");
    }

    public void manageSub(Subscription sub, NotifPreference pref) {
        this.subscriptions.add(sub);
        this.preferences.add(pref);
        System.out.println("Added new subscription tracking: " + sub.url + " with frequency [" + pref.frequency + "]");
    }

    // Getters to allow monitoring execution engines to loop over user tasks
    public List<Subscription> getSubscriptions() { return subscriptions; }
    public List<NotifPreference> getPreferences() { return preferences; }
    public String getName() { return name; }
}
