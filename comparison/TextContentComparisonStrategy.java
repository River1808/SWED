package comparison;

import service.WebsiteSnapshot;

public class TextContentComparisonStrategy implements WebsiteComparisonStrategy {
    @Override
    public boolean hasChanged(WebsiteSnapshot previous, WebsiteSnapshot current) {
        if (previous == null || current == null) return true;
        return !previous.getTextContent().equals(current.getTextContent());
    }

    @Override
    public String getName() {
        return "Identical text content";
    }
}
