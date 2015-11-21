package citrix.gotowebinar.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageWebinarPage extends WebDriverPage {
    public ManageWebinarPage(WebDriver driver) {
        super(driver);
    }

    By heading = By.xpath("//h1(@class='word-wrap-break')");
    By title = By.id("trainingName");
    By description = By.id("trainingDesc");
    By dateTime = By.id("dateTime");

    @Override
    public void initializeElements() {
        url = null; // this is a dynamic URL and cannot be directly navigated to
        setExpectedTitle("Manage Webinar");
    }

    public String getDateAndTime() {
        return driver.findElement(dateTime).getText();
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public String getDescription() {
        return driver.findElement(description).getText();
    }
}
