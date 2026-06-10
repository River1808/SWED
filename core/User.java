package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import config.NotifPreference;
import observer.Observer;
import observer.UpdateEvent;
import service.Notification;

/**
 * Represents a user in the notification system.
 * Manages subscriptions and notification preferences.
 * Implements Observer pattern to receive website update notifications.
 */
public class User implements Observer {
    private String userID;
    private String name;
    private String email;
    
    private List<Subscription> subscriptions; // List to track user's subscriptions
    private List<NotifPreference> preferences;

    public User(String userID, String name, String email) {
        this.userID = Objects.requireNonNull(userID, "User ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        if (userID.trim().isEmpty()) throw new IllegalArgumentException("User ID cannot be empty");
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be empty");
        this.subscriptions = new ArrayList<>();
        this.preferences = new ArrayList<>();
    }

    public void register() {
        System.out.println("User " + name + " (" + userID + ") has successfully registered");
    }

    /**
     * Manages a subscription and its associated notification preference.
     * @param sub The subscription to add
     * @param pref The notification preference for this subscription
     */
    public void manageSub(Subscription sub, NotifPreference pref) {
        if (sub == null) throw new IllegalArgumentException("Subscription cannot be null");
        if (pref == null) throw new IllegalArgumentException("Preference cannot be null");
        this.subscriptions.add(sub);
        this.preferences.add(pref);
        System.out.println("Added new subscription tracking: " + sub.getUrl() + " with frequency [" + pref.getFrequency() + "]");
    }

    public List<Subscription> getSubscriptions() { return new ArrayList<>(subscriptions); }
    public List<NotifPreference> getPreferences() { return new ArrayList<>(preferences); }
    public String getName() { return name; }
    public String getUserID() { return userID; }
    public String getEmail() { return email; }

    /**
     * Called when a subscribed website is updated.
     * Sends notifications through configured channels based on preferences.
     * @param event The update event containing details about the website change
     */
    @Override
    public void update(UpdateEvent event) {
        if (event == null) return;
        System.out.println("  ➜ User " + name + " received update from " + event.getUrl());
        
        // Find matching subscription and preference for this update
        for (int i = 0; i < subscriptions.size(); i++) {
            Subscription sub = subscriptions.get(i);
            NotifPreference pref = preferences.get(i);
            
            if (sub.getUrl().equals(event.getUrl())) {
                String alertMessage = event.getMessage() + " Content timestamp: " + event.getTimestamp();
                Notification updateNotification = new Notification(
                    "NOTIF-" + System.currentTimeMillis() % 10000,
                    alertMessage
                );
                updateNotification.generate();
                updateNotification.send(pref.getChannel());
                break;
            }
        }
    }

    /**
     * Returns the number of active subscriptions for this user.
     */
    public int getSubscriptionCount() { return subscriptions.size(); }
}
