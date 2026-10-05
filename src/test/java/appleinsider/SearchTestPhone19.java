package appleinsider;

import static com.codeborne.selenide.Selenide.open;
import base_test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.saraev.pages.appleinsider.MainPage;

public class SearchTestPhone19 extends BaseTest {

    private final static String BASE_URL = "https://appleinsider.ru/";
    private final static String QUERY_STRING = "iphone 19";

    @Test(groups = {"regress"})
    public void searchPhone19Test() {
        open(BASE_URL);
        int count = new MainPage()
                .search(QUERY_STRING)
                .countTitlesContaining(QUERY_STRING);

        Assert.assertTrue(count >= 2, "Expected at least 2 results with 'iphone 19' in title");
    }
}