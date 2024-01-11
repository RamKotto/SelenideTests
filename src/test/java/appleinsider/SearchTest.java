package appleinsider;

import static com.codeborne.selenide.Selenide.open;
import base_test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.saraev.pages.appleinsider.MainPage;

public class SearchTest extends BaseTest {

    private final static String BASE_URL = "https://appleinsider.ru/";
    private final static String QUERY_STRING = "Чем отличается iPhone 13 от iPhone 12";
    private final static String EXPECTED_PART_OF_HREF = "iphone-13";

    @Test(groups = {"regress"})
    public void checkHrefTest() {
        open(BASE_URL);
        String href = new MainPage()
            .search(QUERY_STRING)
            .getHrefFirstTitle();

        Assert.assertTrue(href.contains(EXPECTED_PART_OF_HREF));
        Assert.assertEquals(true, false);
    }
}
