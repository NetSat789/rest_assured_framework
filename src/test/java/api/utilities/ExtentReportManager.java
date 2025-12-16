package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        // Timestamp
        String timestamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

        // Dynamic 4-digit ID
        int uniqueId = new Random().nextInt(9000) + 1000;

        
        String reportPath = "C:\\Users\\mnetk\\eclipse-workspace\\rest_assured_framework\\ExtentReports2"
                + "\\TestReport_" + uniqueId + "_" + timestamp + ".html";

        // Report Path
//        String reportPath = System.getProperty("user.dir")
//                + "/ExtentReports/TestReport_" + uniqueId + "_" + timestamp + ".html";

//        String reportPath = System.getProperty("user.dir") + "/ExtentReports/TestReport.html";

        // Spark Reporter
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("API Test Execution Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("UTF-8");

        // Extent Report Object
        extent = new ExtentReports();
        extent.attachReporter(spark);

        //  Adding System Information
        extent.setSystemInfo("Application", "Swagger Users API and Crud Operation");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
       extent.setSystemInfo("Executed By", "Satish Netke");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Report ID", String.valueOf(uniqueId));
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
        test.get().assignAuthor("Satish Netke");
        test.get().assignCategory("Regression");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        test.get().assignAuthor("Satish Netke");
        test.get().assignCategory("Regression");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }
}
