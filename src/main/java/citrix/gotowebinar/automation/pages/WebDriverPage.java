package citrix.gotowebinar.automation.pages;

import citrix.gotowebinar.automation.AutomationException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public abstract class WebDriverPage<T>{

    protected WebDriver driver;
    protected WebDriverWait wait;
    public String url;
    public String title;
    public long timeout = 60;
    protected Logger log;

    public WebDriverPage(WebDriver driver) {
        initializeLogger();
        this.driver = driver;
        wait = new WebDriverWait(this.driver, timeout);
        initializeElements();
    }

    public void setUrl(String url) { this.url = url; }
    public String getUrl() { return url; }

    public void setExpectedTitle(String title) { this.title = title; }
    public String getExpectedTitle() { return title; }
    /**
     * This needs implemented for each page.
     * It injects webdriver as needed.
     */
    public abstract void initializeElements();

    /**
     *
     * Performs whatever checks deemed necessary to verify we are on the expected page.
     * May be overridded to check more than title.
     *
     * @return
     */
    public Boolean isCurrentPage() throws AutomationException {
        try {
            wait.until(ExpectedConditions.titleIs(getExpectedTitle()));
        } catch (WebDriverException e) {
            log.warning("not on expected page: " + getPageName());
            log.info(e.getMessage());
            return false;
        }

        return true;
    }

    public WebDriverPage<T> navigateTo() throws AutomationException {
        driver.get(url);
        checkIfCurrentPage();
        return this;
    }

    protected void checkIfCurrentPage() throws AutomationException {
        if (! isCurrentPage()) {
            throw new AutomationException("not on expected page: " + getPageName());
        }
    }

    protected void initializeLogger() {
        String page = this.getClass().getSimpleName();
        log = Logger.getLogger(page);
    }

    public String getPageName() {
        return this.getClass().getSimpleName();
    }
}