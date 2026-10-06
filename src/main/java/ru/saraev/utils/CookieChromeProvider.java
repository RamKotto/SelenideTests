package ru.saraev.utils;

import com.codeborne.selenide.WebDriverProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Провайдер Chrome, который до первого перехода на сайт добавляет в браузер куки из browser-cookies.txt
 * и разворачивает окно на весь экран.
 * Подключается через Configuration.browser = CookieChromeProvider.class.getName().
 */
public class CookieChromeProvider implements WebDriverProvider {

    private static final String COOKIES_RESOURCE = "/browser-cookies.txt";
    private static final String SET_COOKIE_COMMAND = "Network.setCookie";
    private static final String FIELD_SEPARATOR = "\\|";

    /**
     * Создает Chrome с настройками Selenide и устанавливает в него куки из файла ресурсов, затем включает полноэкранный режим.
     *
     * @param capabilities настройки браузера, собранные Selenide
     * @return запущенный драйвер с добавленными куки
     */
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        ChromeDriver driver = new ChromeDriver(new ChromeOptions().merge(capabilities));
        readCookies().forEach(cookie -> driver.executeCdpCommand(SET_COOKIE_COMMAND, cookie));
        driver.manage().window().fullscreen();
        return driver;
    }

    /**
     * Читает куки из файла ресурсов. Формат строки: имя|значение|домен|путь|secure|httpOnly.
     *
     * @return параметры команды Network.setCookie для каждой куки
     */
    private List<Map<String, Object>> readCookies() {
        try (InputStream stream = CookieChromeProvider.class.getResourceAsStream(COOKIES_RESOURCE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return reader.lines()
                .filter(line -> !line.isBlank())
                .map(this::toCookieParameters)
                .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    private Map<String, Object> toCookieParameters(String line) {
        String[] fields = line.split(FIELD_SEPARATOR);
        Map<String, Object> cookie = new HashMap<>();
        cookie.put("name", fields[0]);
        cookie.put("value", fields[1]);
        cookie.put("domain", fields[2]);
        cookie.put("path", fields[3]);
        cookie.put("secure", Boolean.parseBoolean(fields[4]));
        cookie.put("httpOnly", Boolean.parseBoolean(fields[5]));
        return cookie;
    }
}
