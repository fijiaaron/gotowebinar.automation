package citrix.gotowebinar.automation;

import citrix.gotowebinar.automation.pages.*;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class GoToWebinarDriver {

    WebDriver driver;

    protected Logger log = Logger.getLogger("GoToWebinarDriver");

    public GoToWebinarDriver(WebDriver driver) {
        this.driver = driver;
        initialize(this.driver);
    }

    //page objects
    public LandingPage landingPage;
    public LoginPage loginPage;
    public MyWebinarsPage myWebinarsPage;
    public ScheduleWebinarPage scheduleWebinarPage;

    public void initialize(WebDriver driver) {
        landingPage = new LandingPage(this.driver);
        loginPage = new LoginPage(driver);
        myWebinarsPage = new MyWebinarsPage(driver);
        scheduleWebinarPage = new ScheduleWebinarPage(driver);
    }

    public WebDriverPage<? extends WebDriverPage> navigateTo(WebDriverPage<? extends WebDriverPage> page) throws AutomationException {
        page = page.navigateTo();
        return page;
    }

    public Boolean isCurrentPage(WebDriverPage<? extends WebDriverPage> page) throws AutomationException {
        return page.isCurrentPage();
    }

}
