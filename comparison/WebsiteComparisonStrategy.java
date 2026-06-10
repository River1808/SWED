package comparison;

import service.WebsiteSnapshot;

/**
 * Strategy interface for comparing website snapshots.
 */
public interface WebsiteComparisonStrategy {
    /**
     * Determines whether the website has changed between two snapshots.
     * @param previous previous snapshot of the website content
     * @param current current snapshot of the website content
     * @return true if content is different according to the strategy, false otherwise
     */
    boolean hasChanged(WebsiteSnapshot previous, WebsiteSnapshot current);

    /**
     * Returns the strategy name.
     * @return strategy name
     */
    String getName();
}
