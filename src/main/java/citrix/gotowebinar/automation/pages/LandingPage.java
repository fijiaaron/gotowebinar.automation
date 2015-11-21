package citrix.gotowebinar.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends WebDriverPage {

    By signInLink;
    By scheduleWebinarButton;
    By manageWebinarsButton;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initializeElements() {
        setUrl("http://www.gotomeeting.com/webinar/customer");
        setExpectedTitle("Webinar & Online Conference | GoToWebinar");

        signInLink = By.linkText("Sign In");
        scheduleWebinarButton = By.xpath("//a[contains(@href,'/redirect/schedule-webinar')");
        manageWebinarsButton = By.xpath("//a[contains(@href, '/redirect/manage-webinar')");
    }

    public void signIn() {
        driver.findElement(signInLink).click();
    }

    public void scheduleWebinar() {
        driver.findElement(scheduleWebinarButton).click();
    }

    public void manageWebinars() {
        driver.findElement(manageWebinarsButton).click();
    }
}
