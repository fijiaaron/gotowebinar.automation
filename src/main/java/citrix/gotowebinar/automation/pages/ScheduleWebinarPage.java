package citrix.gotowebinar.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScheduleWebinarPage extends WebDriverPage {
    public ScheduleWebinarPage(WebDriver driver) {
        super(driver);
    }

    //By copyWebinarActivate = By.id("select2-drop-mask");
    By titleField = By.id("name");
    By descriptionField = By.id("description");
    //By occursDropdown = By.id("recurrenceForm_recurs");
    //By occursActivate = By.id("recurrenceForm_recurs_trig");
    By dateField = By.id("webinarTimesForm.dateTimes_0.baseDate");

    By scheduleButton = By.id("schedule.submit.button");

    @Override
    public void initializeElements() {
        url = "https://global.gotowebinar.com/schedule.tmpl";
        title = "Schedule a webinar";
    }

    public void enterTitle(String title) {
        driver.findElement(titleField).sendKeys(title);
    }

    public void enterDescription(String description) {
        driver.findElement(descriptionField).sendKeys(description);
    }

    public void enterDate(String date) {
        driver.findElement(dateField).click();
        driver.findElement(dateField).clear();
        driver.findElement(dateField).sendKeys(date);
    }

    public void save() {
        driver.findElement(scheduleButton).click();
    }
}
