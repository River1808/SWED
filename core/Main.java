package core;

import comparison.ContentSizeComparisonStrategy;
import comparison.HtmlContentComparisonStrategy;
import comparison.TextContentComparisonStrategy;
import config.NotifPreference;
import channels.CommunicationChannel;
import service.Website;

/**
 * Main entry point for the University Notification System.
 * Demonstrates the Observer design pattern where websites notify users of updates.
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== University Notification System (Observer Pattern) ===\n");
            
            // Create communication channels
            CommunicationChannel emailChannel = new CommunicationChannel("Email");
            CommunicationChannel smsChannel = new CommunicationChannel("SMS");

            // Create users (Observers)
            User user1 = new User("2026", "John Doe", "john@fra-uas.de");
            User user2 = new User("2027", "Jane Smith", "jane@fra-uas.de");
            user1.register();
            user2.register();
            System.out.println();

            // Create websites (Observables) with different comparison strategies
            Website universityPortal = new Website(
                "WEB-04",
                "https://fsdfsdfsdf.de",
                new ContentSizeComparisonStrategy()
            );
            Website newsPortal = new Website(
                "WEB-05",
                "https://news.fra-uas.de",
                new HtmlContentComparisonStrategy()
            );
            Website blogPortal = new Website(
                "WEB-06",
                "https://blog.fra-uas.de",
                new TextContentComparisonStrategy()
            );

            // User 1 subscribes to university portal with daily email notifications
            NotifPreference dailyEmail = new NotifPreference("Daily", emailChannel);
            Subscription sub1 = new Subscription("SUB-001", universityPortal.getUrl(), emailChannel);
            user1.manageSub(sub1, dailyEmail);

            // User 2 subscribes to both portals
            NotifPreference instantSMS = new NotifPreference("Instant", smsChannel);
            Subscription sub2 = new Subscription("SUB-002", universityPortal.getUrl(), smsChannel);
            user2.manageSub(sub2, instantSMS);

            Subscription sub3 = new Subscription("SUB-003", newsPortal.getUrl(), emailChannel);
            user2.manageSub(sub3, dailyEmail);

            Subscription sub4 = new Subscription("SUB-004", blogPortal.getUrl(), smsChannel);
            user1.manageSub(sub4, instantSMS);
            System.out.println();

            // Register users as observers to websites
            System.out.println("=== Registering Observers ===");
            universityPortal.addObserver(user1);
            universityPortal.addObserver(user2);
            newsPortal.addObserver(user2);
            blogPortal.addObserver(user1);
            System.out.println();

            // Simulate website checks and automatic observer notifications
            System.out.println("=== Checking for Website Updates ===");
            simulateWebsiteChecks(universityPortal, 3);
            System.out.println();
            simulateWebsiteChecks(newsPortal, 2);
            System.out.println();
            simulateWebsiteChecks(blogPortal, 2);

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Simulates checking a website for updates multiple times.
     * When updates are detected, all observers are automatically notified.
     */
    private static void simulateWebsiteChecks(Website website, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("\n[Check " + (i + 1) + "] Checking " + website.getUrl() + " for updates...");
            boolean hasUpdates = website.checkUpdates();
            if (!hasUpdates) {
                System.out.println("No changes detected.");
            }
        }
    }
}