
public class Website {
    private String websiteID;
    private String url;
    private String lastUpdated;

    public Website(String websiteID, String url) {
        this.websiteID = websiteID;
        this.url = url;
        this.lastUpdated = "Initial State";
    }

    public boolean checkUpdates() {
        // Mocked update check logic: simulates a random website change
        boolean hasChanged = Math.random() > 0.5;
        if (hasChanged) {
            this.lastUpdated = new java.util.Date().toString();
        }
        return hasChanged;
    }

    // Getters
    public String getWebsiteID() { return websiteID; }
    public String getUrl() { return url; }
    public String getLastUpdated() { return lastUpdated; }
}