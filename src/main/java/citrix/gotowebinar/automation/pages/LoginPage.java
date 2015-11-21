package citrix.gotowebinar.automation.pages;

import citrix.gotowebinar.automation.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WebDriverPage {

    public By emailAddressField;
    public By passwordField;
    public By signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void initializeElements() {
        url = "https://login.citrixonline.com/login";
        url += "?service=https%3A%2F%2Fglobal.gotowebinar.com%2Fj_spring_cas_security_check";

        title = "Citrix Secure Sign In";

        emailAddressField = By.id("emailAddress");
        passwordField = By.id("password");
        signInButton = By.id("submit");
    }

    public void login(String email, String password) throws AutomationException {
        if (! isCurrentPage()) {
            log.info("going to login page");
            navigateTo();
        }

        driver.findElement(emailAddressField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }

}
