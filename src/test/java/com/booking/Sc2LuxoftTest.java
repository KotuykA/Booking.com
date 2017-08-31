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
import static com.booking.util.TestData.ResultPage.RESULT_PAGE_40_TIMES_APPEAR;
import static com.booking.util.TestData.ResultPage.RESULT_PAGE_4_TIMES_APPEAR;
import static com.booking.util.TestData.ResultPage.RESULT_PAGE_5_NIGHT_STAY;
import static com.booking.util.TestData.ResultPage.RESULT_PAGE_PRICE_FOR_5_NIGHTS;
import static org.testng.Assert.assertTrue;

@Listeners({com.booking.core.TestListener.class})
@Title("Second test scenarios for Luxoft")
@Description("There is one test scenario that was invented by the recruiter in accordance with Luxoft's TechnicalTest.txt")
public class Sc2LuxoftTest extends WebDriverTestBase {

    @Test
    @Step("Verify quantity of proposes that contains/notcontains text '5-night stay' or 'Price for 5 Nights' in propose price block")
    public void secondScenarioTest() {
        MainPage mp = new MainPage(driver);
        driver.get(BOOKING_BASE_URL);
        mp.searchFor(MAIN_PAGE_NEW_YORK_TEXT)
                .closeAutocompleteWindow();
        mp.checkIn20AndOut25CurrentMonth()
                .getSearchButton().click();
        SearchResultPage srp = new SearchResultPage(driver);
        int[] numbersOfOccurrencesAndAbsences = srp.checkHowManyProposesContainsText(
                srp.getPriceBlock(), srp.getxNightStayByXpath(), RESULT_PAGE_5_NIGHT_STAY, RESULT_PAGE_PRICE_FOR_5_NIGHTS);
        assertTrue(numbersOfOccurrencesAndAbsences[0] > Integer.valueOf(RESULT_PAGE_40_TIMES_APPEAR));
        assertTrue(numbersOfOccurrencesAndAbsences[1] > Integer.valueOf(RESULT_PAGE_4_TIMES_APPEAR));
    }

}