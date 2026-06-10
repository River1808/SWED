package comparison;

import service.WebsiteSnapshot;

public class HtmlContentComparisonStrategy implements WebsiteComparisonStrategy {
    @Override
    public boolean hasChanged(WebsiteSnapshot previous, WebsiteSnapshot current) {
        if (previous == null || current == null) return true;
        return !previous.getHtmlContent().equals(current.getHtmlContent());
    }

    @Override
    public String getName() {
        return "Identical HTML content";
    }
}
