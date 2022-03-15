package OunassTest.pages;

import OunassTest.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {


    public List<WebElement> infoInBag(String str) {
        return Driver.get().findElements(By.xpath("//*[@class='CartItem-attributeName'][contains(text(),'" + str + "')]/following-sibling::span"));
    }

    @FindBy(xpath = "//*[@class='CartTotal-actions']")
    public WebElement checkOuTButton;

    @FindBy(xpath = "//*[@class='Map-enterAddressManuallyButton']")
    public WebElement enterAddress;

    public WebElement addressFields(String str) {
        return Driver.get().findElement(By.name(str));
    }

    @FindBy(xpath = "//*[@class='ShippingInformationForm-submitButton']")
    public WebElement continueButton;

    @FindBy(xpath = "//*[@class='ShippingInformationForm-submitButton']")
    public WebElement emirateDropdown;


}
