package BaseClassHB;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

/**
 * TestNG listener: writes pass / fail / skip into the Extent report
 * and attaches a screenshot when a test fails.
 */
public class Listerners implements ITestListener {

    private static final ExtentReports extent = Extentmanager.getReports();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        test.set(extent.createTest(name, description));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        if (TestBase.driver != null) {
            String name = result.getMethod().getMethodName();
            TestUtilities.captureScreenshot(TestBase.driver, name);
            String base64 = TestUtilities.getBase64Screenshot(TestBase.driver);
            test.get().fail("Screenshot at failure",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
