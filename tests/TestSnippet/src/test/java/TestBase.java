import java.net.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    protected WebDriver driver;
    protected static Properties properties;
    protected static String email;
    protected static String password;
    protected static Map<String, String> titles;

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException, IOException {
        properties = new Properties();
        InputStream input = new FileInputStream("props.properties");
        properties.load(input);
        email = properties.getProperty("snippet.email");
        password = properties.getProperty("snippet.password");

        titles = new HashMap<String, String>();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith("page.") && key.endsWith(".url")) {
                String url = properties.getProperty(key);
                String titleKey = key.replace(".url", ".title");
                String title = properties.getProperty(titleKey);
                titles.put(url, title);
            }
        }
        input.close();
    }

    @Before
    public void setUp() throws java.net.MalformedURLException {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("download.default_directory", "/home/selenium/tests/TestSnippet/downloads");
        chromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '/home/selenium/tests/TestSnippet/downloads'}");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-dev-shm-usage");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Exception while quitting driver: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
