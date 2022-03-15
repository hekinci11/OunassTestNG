package OunassTest.pages;

import OunassTest.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegisterAccount_page extends BasePage {

    @FindBy(xpath = "//*[@class='SignInForm-email']")
    public WebElement emailBox;

    @FindBy(name = "password")
    public WebElement passwordBox;

    @FindBy(className = "SignInForm-signInButton")
    public WebElement loginButton;

    @FindBy(xpath = "(//a[@href='/customer/information'])[2]")
    public WebElement editButton;

    public String getAnEmail() {
        return Driver.get().findElement(By.xpath("(//*[@class='MyAccountPage-value'])[2]")).getAttribute("value");
    }

    public boolean containsMessageinEmail() {
        String getText = Driver.get().findElement(By.xpath("(//*[@class='Profile-email'])")).getAttribute("value");
        return getText.contains("(Not editable)");
    }

    public boolean emailBoxDisabled() {
        return Driver.get().findElement(By.xpath("(//*[@class='Profile-email'])")).isEnabled();
    }
}
