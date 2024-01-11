package base_test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import ru.saraev.utils.ScreenshotUtils;

public abstract class BaseTest {

    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1000";
        Configuration.headless = false;
        Configuration.timeout = 10000L;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeSuite
    public void init() {
        setUp();
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        screenshotAfterFail(testResult);
        Selenide.closeWebDriver();
    }

    private void screenshotAfterFail(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            ScreenshotUtils.takeScreenshot();
        }
    }
}
