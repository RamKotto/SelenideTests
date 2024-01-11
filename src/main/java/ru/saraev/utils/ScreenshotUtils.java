package ru.saraev.utils;

import static ru.saraev.utils.StringUtils.getUniqueString;
import com.codeborne.selenide.WebDriverRunner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Slf4j
public class ScreenshotUtils {

    private final static String SCREENSHOT_DIRECTORY = System.getProperty("user.dir") + "/screenshots/";
    private final static String SCREENSHOT = SCREENSHOT_DIRECTORY + getUniqueString() + ".jpg";

    public static void takeScreenshot() {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(
            ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE)));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(SCREENSHOT))) {
            int i;
            while ((i = in.read()) != -1) {
                out.write((byte) i);
            }
            log.info("Скриншот: " + SCREENSHOT + " создан успешно");
        } catch (IOException exception) {
            log.error(exception.getMessage());
            log.info("Скриншот не был создан...");
        }
    }
}
