package citrix.gotowebinar.automation.pages;

import citrix.gotowebinar.automation.AutomationException;
import citrix.gotowebinar.automation.GoToWebinarDriver;
import citrix.gotowebinar.automation.WebDriverTestCase;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LandingPage_Tests extends WebDriverTestCase {

    @Test
    public void should_navigate_to_landing_page() throws AutomationException {
        LandingPage page = new LandingPage(driver);
        page.navigateTo();
        assertThat(driver.getTitle(), is(page.title));
    }

    @Test
    public void should_goto_login_from_landing_page() throws AutomationException {
        LandingPage page = new LandingPage(driver);
        page.navigateTo();
        page.signIn();

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isCurrentPage());
    }

    @Test
    public void using_site() throws AutomationException {
        GoToWebinarDriver gotowebinar = new GoToWebinarDriver(driver);
        gotowebinar.landingPage.navigateTo();
        gotowebinar.landingPage.signIn();
    }
}
