package OunassTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//button[contains(text(),'FACEBOOK')]")
    public WebElement facebookButton;

    @FindBy(id = "email")
    public WebElement facebookUsername;

    @FindBy(id = "pass")
    public WebElement facebookPassword;

    @FindBy(id = "loginbutton")
    public WebElement facebookLoginButton;
}
