package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.booking.util.ElementsAttributes.Attribute.CLASS;
import static com.booking.util.ElementsAttributes.AttributesValues.AUTOCOMPLETE_CLOSE;

public class MainPage extends AbstractPage {

    private String dataFilter = ".//form[@data-is-first-visible='1']//*[@data-mode='%s']",
            calendarButton = "//div[@class='sb-date-field__display']",
            nextMonthForm = "//following-sibling::div//div[@class='c2-month'][%s]//span[contains(text(),'%s')]";

    private By cityFiealById = By.id("ss"),
            checkInDateCalendarByXpath = By.xpath(String.format(dataFilter, "checkin") + calendarButton),
            september20CheckInByXpath = By.xpath(String.format(dataFilter, "checkin") + String.format(nextMonthForm, "2", "20")),
            checkOutDateCalendarByXpath = By.xpath(String.format(dataFilter, "checkout") + calendarButton),
            september25CheckOutByXpath = By.xpath(String.format(dataFilter, "checkout") + String.format(nextMonthForm, "1", "25")),
            searchButtonById = By.xpath(".//form[@data-is-first-visible='1']//button[@data-sb-id='main']"),
            searchFieldDropDown = By.xpath(".//form[@data-is-first-visible='1']//ul[1]");

    public WebElement getCityField() {
        return findElement(cityFiealById);
    }

    public WebElement getCheckInDateCalendar() {
        return findElement(checkInDateCalendarByXpath);
    }

    public WebElement getSeptember20CheckIn() {
        return findElement(september20CheckInByXpath);
    }

    public WebElement getCheckOutDateCalendar() {
        return findElement(checkOutDateCalendarByXpath);
    }

    public WebElement getSeptember25CheckOut() {
        return findElement(september25CheckOutByXpath);
    }

    public WebElement getSearchButton() {
        return findElement(searchButtonById);
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage searchFor(String cityToSearch) {
        getCityField().clear();
        getCityField().sendKeys(cityToSearch);
        return this;
    }

    public MainPage checkIn20AndOut25CurrentMonth() {
        getCheckInDateCalendar().click();
        getSeptember20CheckIn().click();
        getCheckOutDateCalendar().click();
        getSeptember25CheckOut().click();
        return this;
    }

    public MainPage closeAutocompleteWindow() {
        replaceElementAttribute(findElement(searchFieldDropDown), CLASS, AUTOCOMPLETE_CLOSE);
        return this;
    }

}