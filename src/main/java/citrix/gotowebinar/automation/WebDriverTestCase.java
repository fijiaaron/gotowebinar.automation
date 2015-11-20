package citrix.gotowebinar.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverTestCase {
    @Rule
    public TestName name = new TestName();

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setup() throws AutomationException {
        driver = new WebDriverFactory().local().browser(Browser.CHROME).getInstance();
        wait = new WebDriverWait(driver, WebDriverFactory.TIMEOUT);
    }

    @After
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}


