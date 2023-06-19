import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestSnippets extends TestBase {
    @Test
    public void testDownload() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn(email, password);
        mainPage.waitForElementToAppear(mainPage.logoutButtonBy);
        Assert.assertTrue(mainPage.navbarExists());
        List<String> inputs = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            int rand = new Random().nextInt(99999);
            String text = String.valueOf(rand);
            inputs.add(text);
            mainPage.enterText(text);
            ResultPage resultPage = mainPage.submit();
            resultPage.waitForResult();
            Assert.assertEquals(text, resultPage.getFirstLine());
            mainPage.goBack();
            
        }
        SnippetsPage snippets = new SnippetsPage(driver);
        inputs.forEach((input) -> {
            ResultPage result = snippets.loadResultBySearchTerm(input);
            result.download();
            snippets.goBack();
        });

    }
}