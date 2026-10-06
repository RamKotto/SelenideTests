package ru.saraev.pages.appleinsider;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.saraev.pages.base_page.BasePage;
import ru.saraev.utils.JavaScriptUtils;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input")
    private SelenideElement searchField;

    public SearchResultPage search(String value) {
        JavaScriptUtils.setValue(value, searchField);
        searchField.sendKeys(Keys.ENTER);
        return new SearchResultPage();
    }
}
