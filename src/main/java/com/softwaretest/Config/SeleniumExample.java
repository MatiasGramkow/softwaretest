package com.softwaretest.Config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SeleniumExample {

    private SeleniumConfig config;
    private String url = "http://localhost:8081/user/create";

    public SeleniumExample() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    public String getTitle() {
        return this.config.getDriver().getTitle();
    }

    public String getTextById(String id){
        return this.config.getDriver().findElement(By.id(id)).getText();
    }

    public WebElement getElementById(String id){
        return this.config.getDriver().findElement(By.id(id));
    }

    public void getUserDetailsPage() {
        closeOverlay();
    }

    private void closeOverlay() {
        List<WebElement> webElementList = this.config.getDriver()
                .findElements(By.tagName("a"));
        if (webElementList != null) {
            webElementList.stream()
                    .filter(webElement -> "Close".equalsIgnoreCase(webElement.getAttribute("title")))
                    .filter(WebElement::isDisplayed)
                    .findAny()
                    .ifPresent(WebElement::click);
        }
    }

    public void clickUpdateLink() {
        this.config.getDriver().findElement(By.partialLinkText("Update")).click();
    }
}