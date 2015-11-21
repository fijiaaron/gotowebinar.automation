import citrix.gotowebinar.automation.AutomationException;
import citrix.gotowebinar.automation.DateUtil;
import citrix.gotowebinar.automation.GoToWebinarDriver;
import citrix.gotowebinar.automation.WebDriverTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ScheduleNewWebinar_Test extends WebDriverTestCase {

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
        String timestamp = DateUtil.generateTimestamp();
        String webinarTitle = "This is a unique webinar title " + timestamp;
        String webinarDescription = "This is a unique webinar description " + timestamp;

        // landing page
        log.info("going to landing page");
        gotowebinar.landingPage.navigateTo();
        gotowebinar.landingPage.signIn();
        assertTrue(gotowebinar.loginPage.isCurrentPage());

        // login page
        log.info("going to login page to login");
        gotowebinar.loginPage.navigateTo();
        gotowebinar.loginPage.login(gotowebinar_user_email, gotowebinar_user_password);

        // my webinars page
        log.info("on my webinars page to schedule a new webinar");
//        gotowebinar.myWebinarsPage.navigateTo();
        assertTrue(gotowebinar.myWebinarsPage.isCurrentPage());
        gotowebinar.myWebinarsPage.scheduleNewWebinar();

        // schedule webinar page
        log.info("on schedule webinar page to enter details");
//        gotowebinar.scheduleWebinarPage.navigateTo();
        assertTrue(gotowebinar.scheduleWebinarPage.isCurrentPage());
        gotowebinar.scheduleWebinarPage.enterTitle(webinarTitle);
        gotowebinar.scheduleWebinarPage.enterDescription(webinarDescription);
        //TODO: need to be able to manipulate date-picker
//        gotowebinar.scheduleWebinarPage.enterDate("how to enter date?");

        gotowebinar.scheduleWebinarPage.save();

        // manage webinars page
        log.info("on manage webinars page to verify");
        assertTrue(gotowebinar.manageWebinarPage.isCurrentPage());
        assertThat(gotowebinar.manageWebinarPage.getTitle(), is(webinarTitle));
        assertThat(gotowebinar.manageWebinarPage.getDescription(), is(webinarDescription));
        //TODO: date should be same as entered
    }
}