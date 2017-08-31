package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.booking.util.TestData.WebDriver.IMPLICIT_MINIMUM_WAIT;
import static com.booking.util.TestData.WebDriver.IMPLICIT_WAIT;

public class SearchResultPage extends AbstractPage {

    private By addressByXpath = By.xpath(".//div[contains(@class, 'address')]"),
            xNightStay = By.xpath(".//span[@class='price_for_x_nights_format']"),
            infoBlock = By.xpath("//*[@id='hotellist_inner']//div[@class='sr_item_main_block']"),
            priceBlock = By.xpath("//*[@id='hotellist_inner']//div[@class='sr_rooms_table_block clearfix']");

    public boolean checkIfAllTheProposesContains(By blockToSearch, By pathToSearch, String value) {
        for (WebElement propose : getAllTheProposesList(blockToSearch)) {
            scrollToElement(propose);
            WebElement proposeAddress = propose.findElement(pathToSearch);
            if (!proposeAddress.getText().contains(value)) {
                return false;
            }
        }
        return true;
    }

    public int[] checkHowManyProposesContainsText(By blockToSearch, By pathToSearch, String value1, String value2) {
        int quantityOfElemetsThatContains = 0;
        int quantityOfElemetsThatNotContains = 0;
        driver.manage().timeouts().implicitlyWait(Integer.valueOf(IMPLICIT_MINIMUM_WAIT), TimeUnit.SECONDS);
        for (WebElement propose : getAllTheProposesList(blockToSearch)) {
            scrollToElement(propose);
            try {
                WebElement proposeNightsQuantity = propose.findElement(pathToSearch);
                if (proposeNightsQuantity.getText().contains(value1) ||
                        proposeNightsQuantity.getText().contains(value2)) {
                    quantityOfElemetsThatContains++;
                }
            } catch (NoSuchElementException e) {
                quantityOfElemetsThatNotContains++;
            }
        }
        driver.manage().timeouts().implicitlyWait(Integer.valueOf(IMPLICIT_WAIT), TimeUnit.SECONDS);
        return new int[]{quantityOfElemetsThatContains, quantityOfElemetsThatNotContains};
    }

    public By getAddressByXpath() {
        return addressByXpath;
    }

    public By getxNightStayByXpath() {
        return xNightStay;
    }

    public By getInfoBlock() {
        return infoBlock;
    }

    public By getPriceBlock() {
        return priceBlock;
    }

    public List<WebElement> getAllTheProposesList(By path) {
        return driver.findElements(path);
    }

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
}