package citrix.gotowebinar.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyWebinarsPage extends WebDriverPage {

    public MyWebinarsPage(WebDriver driver) {
        super(driver);
        initializeElements();
    }

    By scheduleWebinar;

    @Override
    public void initializeElements() {
        url = "https://global.gotowebinar.com/webinars.tmpl";
        title = "My Webinars";

        scheduleWebinar = By.id("scheduleWebinar");
    }

    @Override
    public Boolean isCurrentPage() {
        return null;
    }

    public void scheduleNewWebinar() {
        driver.findElement(scheduleWebinar).click();
    }
}
