package citrix.gotowebinar.automation.pages;

import citrix.gotowebinar.automation.AutomationException;
import citrix.gotowebinar.automation.WebDriverTestCase;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoginPage_Tests extends WebDriverTestCase {

    @Test
    public void should_load_login_page() throws AutomationException {
        LoginPage page = new LoginPage(driver);
        page.navigateTo();

        assertThat(driver.getTitle(), is(page.getExpectedTitle()));
    }

    @Test
    public void should_login() throws AutomationException {
        LoginPage page = new LoginPage(driver);
        page.navigateTo();

        String email = properties.getProperty("gotowebinar.user.email");
        String password = properties.getProperty("gotowebinar.user.password");

        page.login(email, password);

        // it gets lost here without the redirect to gotowebinar as part of the URL;

        MyWebinarsPage myWebinarsPage = new MyWebinarsPage(driver);
        assertTrue(myWebinarsPage.isCurrentPage());
    }
}
