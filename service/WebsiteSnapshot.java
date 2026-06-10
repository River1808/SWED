package service;

import java.util.Objects;

/**
 * Represents a snapshot of website content used for comparison.
 */
public class WebsiteSnapshot {
    private final int contentSize;
    private final String htmlContent;
    private final String textContent;

    public WebsiteSnapshot(int contentSize, String htmlContent, String textContent) {
        this.contentSize = contentSize;
        this.htmlContent = Objects.requireNonNull(htmlContent, "HTML content cannot be null");
        this.textContent = Objects.requireNonNull(textContent, "Text content cannot be null");
    }

    public int getContentSize() {
        return contentSize;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public String getTextContent() {
        return textContent;
    }
}
