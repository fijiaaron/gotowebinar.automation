package citrix.gotowebinar.automation;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class WebDriverFactory {
    public Browser browser = Browser.FIREFOX;
    public Platform platform = Platform.ANY;

    public String chromeDriverPath;
    public String ieDriverPath;
    public String remoteWebDriverUrl;
    public String sauceUserName;
    public String sauceAccessKey;

    public boolean useRemoteWebDriver = false;
    public boolean useSauceLabs = false;

    public static long TIMEOUT = 30000;

    public WebDriverFactory() {
        configureFromEnvironmentVariables();
    }

    public WebDriverFactory(Properties properties) {
        configureFromProperties(properties);
    }

    public void configureFromEnvironmentVariables() {
        Properties properties = System.getProperties();
        configureFromProperties(properties);
    }

    public void configureFromProperties(Properties properties) {
        for (Object propertyName : properties.keySet()) {
//            if (propertyName.toString().startsWith("webdriver"))
                System.out.println("property: " + propertyName + "=" + properties.get(propertyName));
        }

        if (properties.containsKey("webdriver.chrome.driver")) {
            chromeDriverPath = properties.getProperty("webdriver.chrome.driver");
            System.out.println("chromeDriverPath:" + chromeDriverPath);
        }
        if (properties.containsKey("webdriver.ie.driver")) {
            ieDriverPath = properties.getProperty("webdriver.ie.driver");
        }
        if (properties.containsKey("webdriver.remote.url")) {
            remoteWebDriverUrl = properties.getProperty("webdriver.remote.url");
        }
        if (properties.containsKey("saucelabs.user.name")) {
            sauceUserName = properties.getProperty("saucelabs.user.name");
        }
        if (properties.containsKey("saucelabs.access.key")) {
            sauceAccessKey = properties.getProperty("saucelabs.access.key");
        }

    }

    public WebDriver getInstance() throws AutomationException {
        if (useRemoteWebDriver) {
            return createRemoteWebDriverInstance(browser);
        } else {
            return createLocalWebDriverInstance(browser);
        }
    }

    private WebDriver createLocalWebDriverInstance(Browser browser) {
        switch (browser) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                System.out.println("chromeDriverPath:" + chromeDriverPath);
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                return new ChromeDriver();
            case IE:
                System.setProperty("webdriver.ie.driver", ieDriverPath);
                return new InternetExplorerDriver();
            default:
                return new FirefoxDriver();
        }
    }

    private WebDriver createRemoteWebDriverInstance(Browser browser) throws AutomationException {
        DesiredCapabilities capabilities;

        switch (browser) {
            case FIREFOX:
                capabilities = DesiredCapabilities.firefox();
                break;
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                break;
            case IE:
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            default:
                capabilities = new DesiredCapabilities();
        }

        capabilities.setPlatform(platform);

        if (remoteWebDriverUrl == null) {
            remoteWebDriverUrl = "http://localhost:4444/wd/hub";
        }

        try {
            return new RemoteWebDriver(new URL(remoteWebDriverUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new AutomationException("remote Webdriver URL is not valid: " + remoteWebDriverUrl);
        }
    }

    public WebDriverFactory local() {
        useRemoteWebDriver = false;

        return this;
    }

    public WebDriverFactory remote(String url) {
        useRemoteWebDriver = true;
        remoteWebDriverUrl = url;

        return this;
    }

    public WebDriverFactory browser(Browser browser) {
        this.browser = browser;

        return this;
    }

    public WebDriverFactory platform(Platform platform) {
        this.platform = platform;

        return this;
    }
}
