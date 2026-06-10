package comparison;

import service.WebsiteSnapshot;

public class ContentSizeComparisonStrategy implements WebsiteComparisonStrategy {
    @Override
    public boolean hasChanged(WebsiteSnapshot previous, WebsiteSnapshot current) {
        if (previous == null || current == null) return true;
        return previous.getContentSize() != current.getContentSize();
    }

    @Override
    public String getName() {
        return "Identical content size";
    }
}
