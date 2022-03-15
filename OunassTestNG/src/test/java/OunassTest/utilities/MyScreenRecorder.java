package OunassTest.utilities;


import OunassTest.pages.CartPage;
import OunassTest.pages.ItemPage;
import OunassTest.steps.RegisterAccountSteps;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyScreenRecorder extends ScreenRecorder {
    public static ScreenRecorder screenRecorder;
    public String name;

    public MyScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                            Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
            throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;

    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        return new File(movieFolder,
                name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));

    }

    public static void startRecording(String methodName) throws Exception {
        File file = new File("./recordings/");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new MyScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, methodName);

        screenRecorder.start();

    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }

    public static void deleteRecorded() {

        File directory = new File("./recordings");
        File[] files = directory.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    public static class CheckOutPaymentSteps extends RegisterAccountSteps {

        ItemPage ip = new ItemPage();
        CartPage cp = new CartPage();


        @Test
        public void as_a_user_I_could_select_MEN_menu() {
            ip.Menu("men").click();

        }

        @Test(dependsOnMethods = {"as_a_user_I_could_select_MEN_menu"})
        public void as_a_user_I_could_select_TSHIRTS() {
            Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            JavascriptExecutor js = (JavascriptExecutor) Driver.get();
            js.executeScript("window.scrollBy(0,100)", "");
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

            java.util.List<String> excelContent = new ArrayList<>();
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
            java.util.List<WebElement> itemSize = cp.infoInBag("Size");
            FileInputStream fileInputStream = new FileInputStream("on.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook((fileInputStream));
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            for (int i = 0; i < itemSize.size(); i++) {
                assertEquals(itemSize.get(i).getText(), sheet.getRow(i).getCell(0).toString());

            }
            java.util.List<WebElement> itemColour = cp.infoInBag("Colour");
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
}