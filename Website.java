
public class Website {
    private String websiteID;
    private String url;
    private String lastUpdated;

    public Website(String websiteID, String url) {
        this.websiteID = websiteID;
        this.url = url;
        this.lastUpdated = "Initial State";
    }

    public boolean checkUpdates() { // Mocked update check with random result
        boolean hasChanged = Math.random() > 0.5; // Randomly simulating a change in website content
        if (hasChanged) {
            this.lastUpdated = new java.util.Date().toString(); // Update timestamp to current time if changes are detected
        }
        return hasChanged;
    }

    public String getWebsiteID() { return websiteID; }
    public String getUrl() { return url; }
    public String getLastUpdated() { return lastUpdated; }
}