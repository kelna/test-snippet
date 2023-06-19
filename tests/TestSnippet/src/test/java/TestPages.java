import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestPages extends TestBase {
    @Test
    public void testGoingBack() {
        MainPage page = new MainPage(driver);
        page.signIn(email, password);
        page.waitForElementToAppear(page.logoutButtonBy);
        Assert.assertTrue(page.navbarExists());
        String mainPageURL = page.getURL();
        page.loadMySnippets();
        Assert.assertNotEquals(mainPageURL, page.getURL());
        page.goBack();
        Assert.assertEquals(mainPageURL, page.getURL());
    }

    @Test
    public void testTitles() {
        for (Map.Entry<String, String> entry : titles.entrySet()) {
            String url = entry.getKey();
            String title = entry.getValue();
            PageBase page = new PageBase(driver, url);
            Assert.assertEquals(title, page.getTitle());
        }
    }
}
