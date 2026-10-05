package ru.saraev.pages.appleinsider;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends MainPage {

    @FindBy(css = "h2.entry-title a")
    private ElementsCollection titles;

    public String getHrefFirstTitle() {
        return titles.shouldHave(CollectionCondition.size(10)).first().getAttribute("href");
    }

    public int countTitlesContaining(String text) {
        titles.shouldHave(sizeGreaterThan(0));
        int count = 0;
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).getText().toLowerCase().contains(text.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
}
