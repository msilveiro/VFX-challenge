package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class GooglePage {
    private final Page page;
    private final Locator buttonAcceptAll;
    private final Locator buttonSearch;
    private final Locator inputSearch;
    private final Locator searchResult;

    public GooglePage(Page page) {
        this.page = page;
        this.buttonAcceptAll = page.locator("button[id='L2AGLb']");
        this.inputSearch = page.locator("textarea[title='Search']");
        this.buttonSearch = page.locator("[value='Google Search']");
        this.searchResult = page.locator("h2[data-dtype='d3ifr']");
    }

    public void navigateToGoogle() {
        page.navigate("http://www.google.pt");
    }

    public void acceptCookies() {
        buttonAcceptAll.click();
    }

    public void searchForText(final String input) {
        inputSearch.click();
        inputSearch.fill(input);

        buttonSearch.first().click();
    }

    public String getSearchResult() {
        return searchResult.textContent();
    }
}