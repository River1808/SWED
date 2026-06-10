package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import observer.Observable;
import observer.Observer;
import observer.UpdateEvent;
import comparison.WebsiteComparisonStrategy;
import comparison.ContentSizeComparisonStrategy;

/**
 * Represents a website monitored for updates.
 * Implements Observable pattern and comparison strategies for update detection.
 */
public class Website implements Observable {
    private String websiteID;
    private String url;
    private LocalDateTime lastUpdated;
    private WebsiteSnapshot lastSnapshot;
    private WebsiteComparisonStrategy comparisonStrategy;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private List<Observer> observers = new ArrayList<>();

    /**
     * @param websiteID The unique identifier for the website
     * @param url The URL of the website
     * @throws IllegalArgumentException if websiteID or url is null or empty
     */
    public Website(String websiteID, String url) {
        this(websiteID, url, new ContentSizeComparisonStrategy());
    }

    /**
     * @param websiteID The unique identifier for the website
     * @param url The URL of the website
     * @param comparisonStrategy Strategy used to detect website updates
     */
    public Website(String websiteID, String url, WebsiteComparisonStrategy comparisonStrategy) {
        this.websiteID = Objects.requireNonNull(websiteID, "Website ID cannot be null");
        this.url = Objects.requireNonNull(url, "URL cannot be null");
        this.comparisonStrategy = Objects.requireNonNull(comparisonStrategy, "Comparison strategy cannot be null");
        if (websiteID.trim().isEmpty()) throw new IllegalArgumentException("Website ID cannot be empty");
        if (url.trim().isEmpty()) throw new IllegalArgumentException("URL cannot be empty");
        this.lastUpdated = null; // No updates yet
        this.lastSnapshot = null;
    }

    /**
     * Sets the comparison strategy used to determine website changes.
     */
    public void setComparisonStrategy(WebsiteComparisonStrategy strategy) {
        this.comparisonStrategy = Objects.requireNonNull(strategy, "Comparison strategy cannot be null");
    }

    /**
     * Checks if the website has been updated using the configured comparison strategy.
     * If changes are detected, all registered observers are notified.
     * @return true if changes are detected, false otherwise
     */
    public boolean checkUpdates() {
        WebsiteSnapshot currentSnapshot = fetchCurrentSnapshot();
        if (lastSnapshot == null) {
            lastSnapshot = currentSnapshot;
            System.out.println("[" + websiteID + "] Initial website snapshot stored using strategy: " + comparisonStrategy.getName());
            return false;
        }

        boolean hasChanged = comparisonStrategy.hasChanged(lastSnapshot, currentSnapshot);
        if (hasChanged) {
            lastSnapshot = currentSnapshot;
            this.lastUpdated = LocalDateTime.now();
            notifyObservers(new UpdateEvent(
                this.websiteID,
                this.url,
                this.lastUpdated,
                "The website " + this.url + " has been updated!"
            ));
        } else {
            System.out.println("[" + websiteID + "] No changes detected by strategy: " + comparisonStrategy.getName());
        }
        return hasChanged;
    }

    private WebsiteSnapshot fetchCurrentSnapshot() {
        String htmlContent = generateMockHtmlContent();
        String textContent = extractTextContent(htmlContent);
        int contentSize = htmlContent.length();
        return new WebsiteSnapshot(contentSize, htmlContent, textContent);
    }

    private String generateMockHtmlContent() {
        int randomValue = (int) (Math.random() * 1000);
        return "<html>\n  <head><title>" + websiteID + "</title></head>\n  <body>\n    <h1>Current content for " + url + "</h1>\n    <p>Random value: " + randomValue + "</p>\n  </body>\n</html>";
    }

    private String extractTextContent(String htmlContent) {
        return htmlContent.replaceAll("<[^>]+>", "").replaceAll("\\s+", " ").trim();
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer == null) throw new IllegalArgumentException("Observer cannot be null");
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[" + websiteID + "] Observer registered. Total observers: " + observers.size());
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
            System.out.println("[" + websiteID + "] Observer unregistered. Total observers: " + observers.size());
        }
    }

    @Override
    public void notifyObservers(UpdateEvent event) {
        if (event == null) throw new IllegalArgumentException("Event cannot be null");
        System.out.println("[" + websiteID + "] Notifying " + observers.size() + " observer(s) of update...");
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public String getWebsiteID() { return websiteID; }
    public String getUrl() { return url; }
    public String getLastUpdated() {
        return lastUpdated == null ? "Initial State" : lastUpdated.format(FORMATTER);
    }
    public WebsiteComparisonStrategy getComparisonStrategy() {
        return comparisonStrategy;
    }
}