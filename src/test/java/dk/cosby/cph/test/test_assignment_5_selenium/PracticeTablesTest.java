package dk.cosby.cph.test.test_assignment_5_selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PracticeTablesTest {
    /**
     * The root URL where all test start
     */
    private final String URL = "https://sqengineer.com/practice-sites/practice-tables-selenium/";

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
    public void clickLinkInFirstDataRowOfTable() {
        $("#table1").shouldBe(visible);

        $("#table1").$("tbody").$$("tr").get(1).$("a").click();

        var title = webdriver().driver().getWebDriver().getTitle();

        assertEquals("Selenium", title);
    }

    /**
     * Clicking the link in the second data row in the table
     * then verify the title
     */
    @Test
    public void clickLinkInSecondDataRowOfTable() {
        $("#table1").shouldBe(visible);

        $("#table1").$("tbody").$$("tr").get(2).$("a").click();

        var title = webdriver().driver().getWebDriver().getTitle();

        assertEquals("Automate Functional Testing from UI to the API | UFT One | Micro Focus", title);
    }

    /**
     * Clicking the link in the third data row in the table
     * then verify the title
     */
    @Test
    public void clickLinkInThirdDataRowOfTable() {
        $("#table1").shouldBe(visible);

        $("#table1").$("tbody").$$("tr").get(3).$("a").click();

        var title = webdriver().driver().getWebDriver().getTitle();

        assertEquals("Test Automation for GUI Testing | Ranorex", title);
    }

    /**
     * Clicking the link in the fourth data row in the table
     * then verify the title
     */
    @Test
    public void clickLinkInFourthDataRowOfTable() {
        $("#table1").shouldBe(visible);

        $("#table1").$("tbody").$$("tr").get(4).$("a").click();

        var title = webdriver().driver().getWebDriver().getTitle();

        assertEquals("TestComplete Automated UI Testing Tool | SmartBear", title);
    }


}
