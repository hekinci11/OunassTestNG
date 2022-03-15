package OunassTest.pages;

import OunassTest.utilities.Driver;
import OunassTest.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ItemPage extends BasePage {

    @FindBy(xpath = "(//*[@class='ContentPage']/div)[11]")
    public WebElement tshirt;


    @FindBy(xpath = "//*[@class='Select-control']")
    public WebElement selectSizeDropdown;

    @FindBy(xpath = "//*[@class='AddToBag']")
    public WebElement addToBagButton;

    public WebElement colour(String str) {
        return Driver.get().findElement(By.xpath("//*[@class='ColorSelection']//button[@title='" + str + "']"));
    }

    public WebElement selectedTshirt(String str) {
        return Driver.get().findElement(By.xpath("(//*[@class='Product-contents']/a[contains(@href,'" + str + "')])[1]"));
    }

    public void size(String str) throws InterruptedException {

//        WebElement wb = Driver.get().findElement(By.xpath("//*[@id='react-select-2--value-item'][//*[contains(text(),'"+str+"')]]"));
     /*   WebElement wb2 =Driver.get().findElement(By.xpath("//*[@class='Select-menu-outer']"));
        WebElement wb3 = Driver.get().findElement(By.xpath("//*[@class='Select-placeholder']"));
        Actions ac = new Actions(Driver.get());
        wb3.click();
        ac.moveToElement(wb2).click();

*/
      /*  Driver.get().findElement(By.xpath("//*[@class='Select-control']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        Driver.get().findElement(By.xpath("//*[@class='Select-control']")).sendKeys(Keys.DOWN);
        Thread.sleep(3000);
        Driver.get().findElement(By.xpath("//*[@class='Select-control']")).sendKeys(Keys.ENTER);
        */

       /* WebElement element = Driver.get().findElement(By.cssSelector(".Select-input"));
        Utilities.setAttribute(element,"aria-expanded","true");
        WebElement wb = Utilities.setAttribute(element, "aria-activedescendant", "react-select-2--option-4");
        Utilities.scrollToElement(addToBagButton);
        Thread.sleep(3000);
        Utilities.clickWithJS(wb);
        Thread.sleep(10000);

        */
        Driver.get().findElement(By.xpath("//div[@class='Select-placeholder']")).click();
        WebElement element = Driver.get().findElement(By.cssSelector(".Select-input"));
        Utilities.setAttribute(element, "aria-expanded", "true");
        Utilities.setAttribute(element, "aria-activedescendant", "react-select-2--option-2").click();
        Utilities.scrollToElement(addToBagButton);
        Thread.sleep(3000);
        element.click();

    }


}
