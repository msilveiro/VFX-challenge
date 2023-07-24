package googleSearch;

import com.microsoft.playwright.*;
import org.junit.Before;
import org.junit.Test;
import pages.GooglePage;

import static junit.framework.Assert.assertEquals;

public class GoogleSearchTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private GooglePage googlePage;

    @Before
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        googlePage = new GooglePage(page);
    }

    @Test
    public void validateSearch() {

        googlePage.navigateToGoogle();
        googlePage.acceptCookies();

        googlePage.searchForText("VFX Financial");

        assertEquals("Google search failed!", "VFX Financial Plc", googlePage.getSearchResult());
    }
}