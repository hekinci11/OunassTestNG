package OunassTest.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Utilities {

    public static WebElement setAttribute(WebElement element, String attributeName, String attributeValue) {
        return (WebElement) ((JavascriptExecutor) OunassTest.utilities.Driver.get()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) OunassTest.utilities.Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) OunassTest.utilities.Driver.get()).executeScript("arguments[0].click();", element);
    }
}
