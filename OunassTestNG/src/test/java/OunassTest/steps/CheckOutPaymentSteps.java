package OunassTest.steps;

import OunassTest.pages.CartPage;
import OunassTest.pages.ItemPage;
import OunassTest.utilities.Driver;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(Hooks.class)
public class CheckOutPaymentSteps {
    ItemPage ip = new ItemPage();
    CartPage cp = new CartPage();

    @Test
    public void as_a_user_I_could_select_MEN_menu() {
        ip.Menu("men").click();

    }

    @Test(dependsOnMethods = {"as_a_user_I_could_select_MEN_menu"})
    public void as_a_user_I_could_select_TSHIRTS() {

        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ip.tshirt.click();
    }

    @Test(dependsOnMethods = {"as_a_user_I_could_select_TSHIRTS"})
    public void as_a_user_I_could_select(String string) {
        ip.selectedTshirt("qasimi").click();
    }

    @Test(dependsOnMethods = {"as_a_user_I_could_select"})
    public void as_a_user_I_could_select_size_and_colour_and_add_it_to_bag() throws IOException, InterruptedException {
        FileInputStream fileInputStream = new FileInputStream("on.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook((fileInputStream));
        XSSFSheet sheet = workbook.getSheet("Sheet1");


        int rowNum = sheet.getLastRowNum();

        Driver.waitFor(2);

        List<String> excelContent = new ArrayList<>();
        for (int i = 1; i <= rowNum; i++) {
            ip.selectSizeDropdown.click();
            Thread.sleep(2000);
            ip.size(sheet.getRow(i).getCell(0).toString());
            Thread.sleep(2000);
            ip.colour(sheet.getRow(i).getCell(1).toString()).click();
            ip.addToBagButton.click();
        }
        ip.Menu("/cart").click();

    }

    @Test(dependsOnMethods = {"as_a_user_I_could_select_size_and_colour_and_add_it_to_bag"})
    public void verify_that_all_selected_items_are_in_the_bag() throws IOException {
        List<WebElement> itemSize = cp.infoInBag("Size");
        FileInputStream fileInputStream = new FileInputStream("on.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook((fileInputStream));
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        for (int i = 0; i < itemSize.size(); i++) {
            assertEquals(itemSize.get(i).getText(), sheet.getRow(i).getCell(0).toString());

        }
        List<WebElement> itemColour = cp.infoInBag("Colour");
        for (int i = 0; i < itemColour.size(); i++) {
            assertEquals(itemColour.get(i).getText(), sheet.getRow(i).getCell(0).toString());

        }

    }

    @Test(dependsOnMethods = {"verify_that_all_selected_items_are_in_the_bag"})
    public void as_a_user_I_could_click_secure_check_out_button() {
        cp.checkOuTButton.click();
    }

    @Test(dependsOnMethods = {"as_a_user_I_could_click_secure_check_out_button"})
    public void as_a_User_I_could_fill_the_address_information_field() throws IOException, InterruptedException {
        cp.enterAddress.click();
        List<WebElement> itemSize = cp.infoInBag("Size");
        FileInputStream fileInputStream = new FileInputStream("on.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook((fileInputStream));
        XSSFSheet sheet = workbook.getSheet("Sheet2");


        for (int i = 0; i < 4; i++) {

            WebElement wb = cp.addressFields(sheet.getRow(0).getCell(i).toString());
            wb.sendKeys(sheet.getRow(1).getCell(i).toString());
            Thread.sleep(1000);
        }
        Thread.sleep(1000);
        cp.continueButton.click();

    }

    @Test(dependsOnMethods = {"as_a_User_I_could_fill_the_address_information_field"})
    public void verify_that_payment_page_is_launched_succesfully() {
        assertTrue(Driver.get().getCurrentUrl().contains("payment"));
    }
}