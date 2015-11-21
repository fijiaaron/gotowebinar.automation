import citrix.gotowebinar.automation.AutomationException;
import citrix.gotowebinar.automation.GoToWebinarDriver;
import citrix.gotowebinar.automation.WebDriverTestCase;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleNewWebinar extends WebDriverTestCase {

    String gotowebinar_user_email;
    String gotowebinar_user_password;

    GoToWebinarDriver gotowebinar;

    @Before
    public void getTestData() {
        gotowebinar_user_email = properties.getProperty("gotowebinar.user.email");
        gotowebinar_user_password = properties.getProperty("gotowebinar.user.password");

        log.info("gotowebinar_user_email: " + gotowebinar_user_email);
        log.info("gotowebinar_user_password: " + gotowebinar_user_password);

        gotowebinar = new GoToWebinarDriver(driver);
    }

    @Test
    public void should_create_new_webinar() throws AutomationException {
        String webinarTitle = "This is a unique webinar title " + generateTimestamp();
        String webinarDescription = "This is a unique webinar description " + generateTimestamp();

        // landing page
//        log.info("going to landing page");
//        gotowebinar.landingPage.navigateTo();
//        gotowebinar.landingPage.signIn();

        // login
        log.info("going to login page");
        gotowebinar.loginPage.navigateTo();
        gotowebinar.loginPage.login(gotowebinar_user_email, gotowebinar_user_password);

        // schedule webinar
        log.info("going to my webinars page");
        gotowebinar.myWebinarsPage.navigateTo();
        gotowebinar.myWebinarsPage.scheduleNewWebinar();

        // enter webinar info
        log.info("going to schedule webinar page");
        gotowebinar.scheduleWebinarPage.navigateTo();
        gotowebinar.scheduleWebinarPage.enterTitle(webinarTitle);
        gotowebinar.scheduleWebinarPage.enterDescription(webinarDescription);

        // verify webinar was scheduled
        log.info("goto to my webinars page to verify");
    }

    private String generateTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(new Date());
    }
}

