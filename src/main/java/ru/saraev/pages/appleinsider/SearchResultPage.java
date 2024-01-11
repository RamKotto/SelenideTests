package ru.saraev.pages.appleinsider;

import static com.codeborne.selenide.CollectionCondition.size;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends MainPage {

    @FindBy(css = "h2.entry-title a")
    private ElementsCollection titles;

    public String getHrefFirstTitle() {
        return titles.shouldHave(size(10)).first().getAttribute("href");
    }
}
