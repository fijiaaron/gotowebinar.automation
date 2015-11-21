package citrix.gotowebinar.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyWebinarsPage extends WebDriverPage {

    public MyWebinarsPage(WebDriver driver) {
        super(driver);
        initializeElements();
    }

    By scheduleWebinar;
    By searchBox;

    @Override
    public void initializeElements() {
        url = "https://global.gotowebinar.com/webinars.tmpl";
        title = "My Webinars";

        scheduleWebinar = By.id("scheduleWebinar");
        searchBox = By.id("upcomingWebinar-searchWebinarSearchBox");
    }

    public void scheduleNewWebinar() {
        driver.findElement(scheduleWebinar).click();
    }

    public void searchForWebinar(String title) {
        driver.findElement(searchBox).sendKeys(title);
        driver.findElement(By.linkText(title)).click();
    }
}
