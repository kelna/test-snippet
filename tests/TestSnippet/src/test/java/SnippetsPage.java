import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SnippetsPage extends PageBase {
    private static final String URL = "https://snippet.host/snippets";
    protected By firstLineBy = By.id("first-line");
    protected By searchBarBy = By.xpath("//*[@id='snippet-search']/div/form/input");

    public SnippetsPage(WebDriver driver) {
        super(driver, URL);
    }

    public boolean idExists(String id) {
        driver.findElement(searchBarBy).sendKeys(id + "\n");
        return driver.findElements(By.id("first-line")).size() > 0;
    }

    public ResultPage loadResultBySearchTerm(String term) {
        driver.findElement(searchBarBy).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(searchBarBy).sendKeys(Keys.DELETE);
        driver.findElement(searchBarBy).sendKeys(term + "\n");
        return new ResultPage(driver);
    }
}
