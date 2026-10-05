package appleinsider;

import static com.codeborne.selenide.Selenide.open;
import base_test.BaseTest;
import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.saraev.pages.appleinsider.MainPage;
import ru.saraev.pages.appleinsider.SearchResultPage;

public class SearchTestPhone19 extends BaseTest {

    private final static String BASE_URL = "https://appleinsider.ru/";
    private final static String QUERY_STRING = "iphone 19";

    @Test(groups = {"regress"})
    public void searchPhone19Test() {
        open(BASE_URL);
        SearchResultPage resultPage = new MainPage()
                .search(QUERY_STRING);
        ElementsCollection titles = resultPage.getTitles();
        
        // Verify at least 2 results contain "iphone 19" in the title
        int count = 0;
        for (int i = 0; i < titles.size(); i++) {
            String titleText = titles.get(i).getText().toLowerCase();
            if (titleText.contains("iphone 19")) {
                count++;
            }
        }
        Assert.assertTrue(count >= 2, "Expected at least 2 results with 'iphone 19' in title");
    }
}