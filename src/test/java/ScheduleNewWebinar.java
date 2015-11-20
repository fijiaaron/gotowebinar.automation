import citrix.gotowebinar.automation.WebDriverTestCase;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ScheduleNewWebinar extends WebDriverTestCase {

    String gotowebinar_user_email;
    String gotowebinar_user_password;

    @Before
    public void getTestData() {
        gotowebinar_user_email = System.getProperty("gotowebinar.user.email");
        gotowebinar_user_password = System.getProperty("gotowebinar.user.password");

        System.out.println("gotowebinar_user_email: " + gotowebinar_user_email);
        System.out.println("gotowebinar_user_password: " + gotowebinar_user_password);

    }

    @Test
    public void should_create_new_webinar_with_title_and_description() {
        // Landing Page
        driver.get("http://www.gotomeeting.com/webinar/customer");
        driver.findElement(By.linkText("Sign In")).click();

        // Login Page
        wait.until(ExpectedConditions.elementToBeClickable(By.id("emailAddress")));
        assertThat(driver.getTitle(), is("Citrix Secure Sign In"));
        driver.findElement(By.id("emailAddress")).sendKeys(gotowebinar_user_email);
        driver.findElement(By.id("password")).sendKeys(gotowebinar_user_password);
        driver.findElement(By.id("submit")).click();

        // My Webinars Page
        wait.until(ExpectedConditions.elementToBeClickable(By.id("scheduleWebinar")));
        assertThat(driver.getTitle(), is("My Webinars"));
        assertThat(driver.getCurrentUrl(), is("https://global.gotowebinar.com/webinars.tmpl"));
        driver.findElement(By.id("scheduleWebinar")).click();

        // Schedule a Webinar Page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        assertThat(driver.getTitle(), is("Schedule a webinar"));
        assertThat(driver.getCurrentUrl(), is("https://global.gotowebinar.com/schedule.tmpl"));
        driver.findElement(By.id("name")).sendKeys("test webinar");
        driver.findElement(By.id("description")).sendKeys("this is a test webinar") ;
        driver.findElement(By.id("schedule.submit.button")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("where???"), "This webinar was scheduled. Please finish setting up this webinar."));
    }
}
