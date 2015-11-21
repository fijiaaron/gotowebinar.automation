package citrix.gotowebinar.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.logging.Logger;

public class WebDriverTestCase {
    @Rule
    public TestName testName = new TestName();

    protected static Logger log;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static Properties properties;


    @BeforeClass
    public static void initialize() {
        log = Logger.getLogger(getTestClassName());

        log.info("initialize...");
        System.out.println("initialize...");

        properties = PropertyLoader.LoadProperties("test.properties");
    }

    @Before
    public void setup() throws AutomationException {
        log = Logger.getLogger(this.getClass().getSimpleName());
        log.info("setup");
        System.out.println("setup");
        driver = new WebDriverFactory().local().browser(Browser.CHROME).getInstance();
        wait = new WebDriverWait(driver, WebDriverFactory.TIMEOUT);
    }

    @After
    public void cleanup() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }



    protected static String getTestClassName() {
        StackTraceElement[] trace = new Throwable().getStackTrace();
        String testClassName = trace[1].getClassName();
        return testClassName;
    }

}


