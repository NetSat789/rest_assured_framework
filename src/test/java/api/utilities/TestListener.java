package api.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(">>> STARTING TEST: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✔ TEST PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ TEST FAILED: " + result.getName());
        logger.error("REASON: ", result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("🏁 ALL TESTS FINISHED for: " + context.getName());
        logger.info("Total Passed: " + context.getPassedTests().size());
        logger.info("Total Failed: " + context.getFailedTests().size());
        logger.info("Total Skipped: " + context.getSkippedTests().size());
    }
}
