package com.booking;

import com.booking.core.WebDriverTestBase;
import com.booking.pages.MainPage;
import com.booking.pages.SearchResultPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static com.booking.util.TestData.General.BOOKING_BASE_URL;
import static com.booking.util.TestData.MainPage.MAIN_PAGE_NEW_YORK_TEXT;
import static com.booking.util.TestData.MainPage.MAIN_PAGE_TITLE;
import static com.booking.util.TestData.ResultPage.RESULT_PAGE_NEW_YORK;
import static org.testng.Assert.assertTrue;


@Listeners({com.booking.core.TestListener.class})
@Title("First test scenarios for Luxoft")
@Description("There is one test scenario in accordance with Luxoft's TechnicalTest.txt")
public class Sc1LuxoftTest extends WebDriverTestBase {

    @Test
    @Step("Verify search filter by hotels location test")
    public void firstScenarioTest() {
        MainPage mp = new MainPage(driver);
        driver.get(BOOKING_BASE_URL);
        assertTrue(driver.getTitle().matches(MAIN_PAGE_TITLE));
        mp.searchFor(MAIN_PAGE_NEW_YORK_TEXT)
                .closeAutocompleteWindow();
        mp.checkIn20AndOut25CurrentMonth()
                .getSearchButton().click();
        SearchResultPage srp = new SearchResultPage(driver);
        assertTrue(srp.checkIfAllTheProposesContains(srp.getInfoBlock(), srp.getAddressByXpath(), RESULT_PAGE_NEW_YORK));
    }

}