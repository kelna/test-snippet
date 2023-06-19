import org.junit.Test;
import org.junit.Assert;

public class TestMainPage extends TestBase {
    @Test
    public void testNotSignedIn() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertFalse(mainPage.navbarExists());
    }
}
