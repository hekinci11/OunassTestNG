package OunassTest.steps;

import OunassTest.pages.LoginPage;
import OunassTest.utilities.ConfigurationReader;
import OunassTest.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Hooks.class)
public class FacebookLoginSteps {
    LoginPage lp = new LoginPage();

    @Test
    public void as_a_user_I_should_click_account_button() throws InterruptedException {
        Driver.get().findElement(By.xpath("//*[@class='No thanks']")).click();

        Actions ac = new Actions(Driver.get());
        ac.moveToElement(lp.LoginAccount).perform();
        lp.LoginAccount.click();
        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = {"as_a_user_I_should_click_account_button"})
    public void as_a_user_I_could_click_Facebook_button() throws InterruptedException {
        Thread.sleep(1000);
        lp.facebookButton.click();
        Thread.sleep(10000);

    }

    @Test(dependsOnMethods = {"as_a_user_I_could_click_Facebook_button"})
    public void as_a_user_I_should_login_with_valid_credentials_in_Facebook() throws InterruptedException {
        lp.facebookUsername.sendKeys(ConfigurationReader.get("facebook_username"));
        lp.facebookPassword.sendKeys(ConfigurationReader.get("facebook_password"));
        lp.facebookLoginButton.click();
        Thread.sleep(10000);

    }

}
