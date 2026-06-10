package observer;

/**
 * Observable interface for the Observer design pattern.
 * Defines the contract for objects that maintain observers and notify them of updates.
 */
public interface Observable {
    /**
     * Registers an observer to be notified of future updates.
     * @param observer The observer to register
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from notifications.
     * @param observer The observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers of an update event.
     * @param event The event to notify observers about
     */
    void notifyObservers(UpdateEvent event);
}
