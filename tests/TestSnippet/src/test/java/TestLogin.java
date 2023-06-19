import java.util.Random;
import java.util.Map;
import org.junit.Test;
import org.junit.Assert;

public class TestLogin extends TestBase {
    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(email, password);
        mainPage.waitForElementToAppear(mainPage.logoutButtonBy);
        Assert.assertTrue(mainPage.navbarExists());
    }

    @Test
    public void testLogOut() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(email, password);
        mainPage.waitForElementToAppear(mainPage.logoutButtonBy);
        Assert.assertTrue(mainPage.navbarExists());

        mainPage.signOut();
        mainPage.waitForElementToAppear(mainPage.headerBy);
        Assert.assertTrue(mainPage.headerExists());
    }

    @Test
    public void testNewSnippetSignedIn() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(email, password);
        mainPage.waitForElementToAppear(mainPage.logoutButtonBy);
        Assert.assertTrue(mainPage.navbarExists());

        int rand = new Random().nextInt(99999);
        String text = String.valueOf(rand);

        mainPage.enterText(text);
        ResultPage resultPage = mainPage.submit();

        resultPage.waitForResult();
        Assert.assertEquals(String.valueOf(rand), resultPage.getFirstLine());
    }
}
