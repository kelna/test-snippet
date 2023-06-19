import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageBase {
    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public PageBase(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
    }

    public void waitForElementToAppear(By by) {
        waitForElementToAppear(by, 10);
    }

    public void waitForElementToAppear(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public boolean headerExists() {
        return driver.findElements(By.xpath("/html/body/header")).size() > 0;
    }

    public boolean navbarExists() {
        return driver.findElements(By.id("top-bar")).size() > 0;
    }
}
