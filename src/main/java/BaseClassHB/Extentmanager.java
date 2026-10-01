package BaseClassHB;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Creates one ExtentReports object for the whole run.
 * Report is generated at: test-output/ExtentReport.html
 */
public class Extentmanager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Holybharat Automation Report");
            spark.config().setReportName("Selenium Regression Suite");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Amith M");
            extent.setSystemInfo("Framework", "Selenium + TestNG + Maven");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}
