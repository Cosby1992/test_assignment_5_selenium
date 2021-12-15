package dk.cosby.cph.test.test_assignment_5_selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 *
 */
public class VimeoCatVideosTest {
    /**
     * The root URL where all test start
     */
    private final String URL = "https://vimeo.com/";

    /**
     * Setting up the browser and logger
     */
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    /**
     * Before each test, open the root url
     */
    @BeforeEach
    public void setUp() {
        open(URL);
    }

    /**
     * Clicking the link in the first data row in the table
     * then verify the title
     */
    @Test
    public void findFirstTenCatVideosWhenSearchingForCatVideo() {

        var webdriver = webdriver().driver().getWebDriver();

        $("nav #menu-container .right-container .search-container a").shouldBe(visible);

        $("nav #menu-container .right-container .search-container a").click();
        $(".js-search_autocomplete").sendKeys("Cat Videos");
        $(".js-search_autocomplete").sendKeys(Keys.RETURN);

        JavascriptExecutor js = (JavascriptExecutor) webdriver;

        js.executeScript("window.scrollBy(0, 500)");


        $(".iris_rail-filter__collapse, .iris_rail-filter__reveal").click();

        var filters = $$(".iris_radio");
        SelenideElement weekFilter = null;
        for (int i = 0; i < filters.size(); i++) {
            if(filters.get(i).getText().equals("Last 7 days")) {
                System.out.println("Clicking on Date Uploaded - Last 7 days filter");
                filters.get(i).click();
                break;
            }
        }

        try{
            System.out.println("Waiting to load components");
            new WebDriverWait(webdriver, 3).until(ExpectedConditions.elementToBeClickable($(".iris_p_infinite__item").$$("iris-annotation-layer").get(0)));
        } catch (Exception e) {
            System.out.println("Continuing");
        }
        //

        var videos = $$(".iris_p_infinite__item");

        List<String> videoStrings = new ArrayList<>(17);

        for (int i = 0; i < Math.min(videos.size(), 10); i++) {
            videoStrings.add(videos.get(i).getText());
        }

        videoStrings = removeDuplicates(videoStrings);

        for (int i = 0; i < Math.min(videoStrings.size(), 10); i++) {
            var videoText = videoStrings.get(i).split("\n");
            System.out.println("Video name " + i + ": " + videoText[0] + ", Created by: " + videoText[1]);
        }


        assertEquals("https://vimeo.com/search?q=Cat+Videos&uploaded=this-week", webdriver.getCurrentUrl());

    }

    private static <E> List<E> removeDuplicates(List<E> list) {
        List<E> returnList = new ArrayList<>();
        for (E e:list) {
            if(!returnList.contains(e)) {
                returnList.add(e);
            }
        }
        return returnList;
    }

}

