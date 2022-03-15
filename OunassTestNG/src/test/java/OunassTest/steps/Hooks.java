package OunassTest.steps;

import OunassTest.utilities.ConfigurationReader;
import OunassTest.utilities.Driver;
import OunassTest.utilities.MyScreenRecorder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

public class Hooks implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        TakesScreenshot scrShot = ((TakesScreenshot) Driver.get());
        File SourceFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("yourpath/screenshot1.png");
        try {
            FileUtils.copyFile(SourceFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

        try {
            MyScreenRecorder.deleteRecorded();
        } catch (Exception e) {
            System.out.println(e);
        }

        Driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
        try {
            MyScreenRecorder.startRecording("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Driver.get().get(ConfigurationReader.get("url"));
        Driver.get().manage().deleteAllCookies();

    }

    @Override
    public void onFinish(ITestContext context) {
        Driver.closeDriver();
        try {
            MyScreenRecorder.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
