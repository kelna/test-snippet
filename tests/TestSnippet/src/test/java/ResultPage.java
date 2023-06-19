import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ResultPage extends PageBase {
    protected By rawTextBy = By.xpath("/html/body/pre");
    protected By firstLineBy = By.id("first-line");
    protected By downloadBy = By.xpath("//*[@id='snippet-details']/span[10]/a");

    public ResultPage(WebDriver driver) {
        super(driver);
        waitForElementToAppear(firstLineBy);
    }

    public ResultPage(WebDriver driver, String id) {
        super(driver, "https://snippet.host/" + id + "/raw");
    }

    public String getText() {
        return driver.findElement(rawTextBy).getText();
    }

    public String getFirstLine() {
        return driver.findElement(firstLineBy).getText();
    }

    public void waitForResult() {
        waitForElementToAppear(firstLineBy, 30);
    }

    public String getId() {
        String url = driver.getCurrentUrl();
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    public void download() {
        // driver.findElement(downloadBy).click();
        System.out.println("DOWNLOAD");
        String downurl = driver.getCurrentUrl() + "/download";
        String path = "/home/selenium/tests/TestSnippet/downloads/";
        String filename = path + downurl + ".txt";
        try (BufferedInputStream in = new BufferedInputStream(new URL(downurl).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                // handle exception
            }
    }
}
