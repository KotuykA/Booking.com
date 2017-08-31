package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected JavascriptExecutor js;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    public void scrollToElement(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void replaceElementAttribute(WebElement element, String attributeName, String attributeNewValue) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                element, attributeName, attributeNewValue);
    }

}