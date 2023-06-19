import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

public class MainPage extends PageBase {
    private static final String URL = "https://snippet.host/";

    protected By loginLinkBy = By.xpath("/html/body/header/div/a[1]");
    protected By loginEmailBy = By.id("email");
    protected By loginPasswordBy = By.id("password");
    protected By loginSubmitBy = By.xpath("/html/body/main/div/div/section/div[2]/form/button");
    protected By logoutButtonBy = By.id("logout");
    protected By headerBy = By.xpath("/html/body/header");
    protected By mySnippetsBy = By.xpath("//*[@id='top-bar']/ul/li[2]/a");
    protected By mainFormBy = By.id("snippet-form");
    protected By mainFormLanguageBy = By.id("snippet-language");
    protected By mainFormSubmitBy = By.xpath("//*[@id='snippet-form']/div/button");

    public MainPage(WebDriver driver) {
        super(driver, URL);
    }

    public WebElement getLoginLink() {
        return driver.findElement(loginLinkBy);
    }

    public boolean isSignedIn() {
        return driver.findElements(By.id("top-bar")).size() > 0;
    }

    public void signIn(String email, String password) {
        driver.findElement(loginLinkBy).click();
        waitForElementToAppear(By.id("email"));
        driver.findElement(loginEmailBy).sendKeys(email);
        driver.findElement(loginPasswordBy).sendKeys(password);
        driver.findElement(loginSubmitBy).click();
    }

    public void signOut() {
        driver.findElement(logoutButtonBy).click();
    }

    public void enterText(String text) {
        WebElement snippetContent = driver.findElement(By.id("snippet-content"));
        Actions actions = new Actions(driver);
        actions.moveToElement(snippetContent).click().perform();
        snippetContent.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        snippetContent.sendKeys(Keys.DELETE);
        snippetContent.sendKeys(text);
    }

    public ResultPage submit() {
        driver.findElement(mainFormSubmitBy).click();
        return new ResultPage(driver);
    }

    public SnippetsPage loadMySnippets() {
        driver.findElement(mySnippetsBy).click();
        return new SnippetsPage(driver);
    }
}
