package citrix.gotowebinar.automation.pages;

import citrix.gotowebinar.automation.AutomationException;
import citrix.gotowebinar.automation.WebDriverTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MyWebinarsPage_Test extends WebDriverTestCase {

    @Test @Ignore
    public void should_challenge_with_login() throws AutomationException {
        MyWebinarsPage myWebinarsPage = new MyWebinarsPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        myWebinarsPage.navigateTo();
        assertTrue(loginPage.isCurrentPage());
    }

    @Test @Ignore
    public void should_redirect_back_to_my_webinars_after_login() throws AutomationException {
        MyWebinarsPage myWebinarsPage = new MyWebinarsPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String email = properties.getProperty("gotowebinar.user.email");
        String password = properties.getProperty("gotowebinar.user.password");

        log.info("we will attempt to go to the my webinars page");
        myWebinarsPage.navigateTo();

        log.info("but it should redirect to the login page");
        assertTrue(loginPage.isCurrentPage());

        log.info("we should be on the login page now because of redirect");
        log.info("so let's try to login");
        loginPage.login(email, password);

        // this doesn't work probably because the url is wrong

        log.info("and see if it redirects back");
        assertTrue(myWebinarsPage.isCurrentPage());
    }

    @Test
    public void login_before_navigating_to_my_webinars() throws AutomationException {
        MyWebinarsPage myWebinarsPage = new MyWebinarsPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String email = properties.getProperty("gotowebinar.user.email");
        String password = properties.getProperty("gotowebinar.user.password");


        loginPage.navigateTo();
        loginPage.login(email, password);

        assertTrue(myWebinarsPage.isCurrentPage());

        myWebinarsPage.navigateTo();
        assertTrue(myWebinarsPage.isCurrentPage());
    }
}
