package com.booking.util;

import static com.booking.core.WebDriverTestBase.getProperty;

public class TestData {

    public static class WebDriver {
        public static final String WEB_DRIVER_GECKO = getProperty("webdriver.gecko"),
                WEB_DRIVER_CHROME = getProperty("webdriver.chrome"),
                CHROME_PATH_IOS = getProperty("chrome.path.ios"),
                CHROME_PATH_WIN = getProperty("chrome.path.win"),
                GECKO_DRIVER_PATH_WIN = getProperty("geckodriver.path.win"),
                GECKO_DRIVER_PATH_IOS = getProperty("geckodriver.path.ios"),
                SCRIPT_TIMEOUT = getProperty("webdriver.script.timeout"),
                LOAD_TIMEOUT = getProperty("webdriver.load.timeout"),
                IMPLICIT_WAIT = getProperty("webdriver.implicit.wait"),
                IMPLICIT_MINIMUM_WAIT = getProperty("webdriver.minimum.implicit.wait"),
                SCREENSHOTS_FOR_SUCCESS_PATH = getProperty("screenshots.path.success"),
                SCREENSHOTS_FOR_FAILED_PATH = getProperty("screenshots.path.failed");
    }

    public static class General {
        public static final String BOOKING_BASE_URL = getProperty("booking.base.url");
    }

    public static class MainPage {
        public static final String MAIN_PAGE_TITLE = getProperty("main.page.title"),
            MAIN_PAGE_NEW_YORK_TEXT= getProperty("main.page.new.york.city.text");
    }

    public static class ResultPage {
        public static final String RESULT_PAGE_NEW_YORK = getProperty("result.page.new.york.text"),
                RESULT_PAGE_5_NIGHT_STAY = getProperty("result.page.5.night.stay.text"),
                RESULT_PAGE_PRICE_FOR_5_NIGHTS = getProperty("result.page.price.for.5.nights.text"),
                RESULT_PAGE_40_TIMES_APPEAR = getProperty("result.page.minimum.number.of.5.nights.text.appears"),
                RESULT_PAGE_4_TIMES_APPEAR = getProperty("result.page.minimum.number.of.5.nights.text.not.appears");
    }

}