package OunassTest.steps;

import OunassTest.pages.RegisterAccount_page;
import OunassTest.utilities.ConfigurationReader;
import OunassTest.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

@Listeners(Hooks.class)

public class RegisterAccountSteps extends Hooks {
    RegisterAccount_page rp = new RegisterAccount_page();

    @Test
    public void as_a_user_I_should_click_account_button() throws InterruptedException {
        Driver.get().findElement(By.xpath("//*[@class='No thanks']")).click();

        Actions ac = new Actions(Driver.get());
        ac.moveToElement(rp.LoginAccount).perform();
        rp.LoginAccount.click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = {"as_a_user_I_should_click_account_button"})
    public void as_a_user_I_should_login_with_valid_credentials() throws InterruptedException {
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        rp.emailBox.sendKeys(ConfigurationReader.get("validUsername"));
        rp.passwordBox.sendKeys(ConfigurationReader.get("validPassword"));
        rp.loginButton.click();
        Thread.sleep(15000);

    }


    @Test(dependsOnMethods = {"as_a_user_I_should_login_with_valid_credentials"})
    public void verify_that_homepage_is_displayed() {

        assertEquals("You are not on home page", Driver.get().getTitle(), "OUNASS UAE | The Definitive Home of Luxury");
        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

}
