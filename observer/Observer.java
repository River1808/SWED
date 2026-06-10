package observer;

/**
 * Observer interface for the Observer design pattern.
 * Defines the contract for objects that want to be notified of website updates.
 */
public interface Observer {
    /**
     * Called when the subject (Website) notifies observers of an update.
     * @param event The update event containing details about the change
     */
    void update(UpdateEvent event);
}
