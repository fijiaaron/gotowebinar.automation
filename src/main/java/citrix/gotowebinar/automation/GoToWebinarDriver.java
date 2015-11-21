package citrix.gotowebinar.automation;

import citrix.gotowebinar.automation.pages.*;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class GoToWebinarDriver {

    WebDriver driver;

    public HashMap<String, ? extends WebDriverPage> pages = new HashMap<>();

    public GoToWebinarDriver(WebDriver driver) {
        initialize(this.driver);
    }

    //page objects
    public LandingPage landingPage;
    public LoginPage loginPage;
    public MyWebinarsPage myWebinarsPage;
    public ScheduleWebinarPage scheduleWebinarPage;

    public void initialize(WebDriver driver) {
        this.driver = driver;

        landingPage = new LandingPage(driver);
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
