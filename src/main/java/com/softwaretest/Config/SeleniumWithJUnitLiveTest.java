package com.softwaretest.Config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumWithJUnitLiveTest {
    private static SeleniumExample seleniumExample;
    private String expectedTitle = "Details";
    SeleniumConfig config = new SeleniumConfig();

    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public static void tearDown() {

    }

    /*
    @Test
    public void whenNameIsUpdated_thenDetailsPageIsLoadedWithUpdatedName(){
        seleniumExample.clickUpdateLink();
        WebElement element = seleniumExample.getElementById("userName");
        element.clear();
        element.sendKeys("Testname");
        WebElement updateBtn = seleniumExample.getElementById("submit");
        updateBtn.click();
        String name = seleniumExample.getTextById("username");
        assertEquals("Testname", name);
    }
*/
    @Test
    public void whenUserIsSignedUp_thenUserCanLogIn() {
        String email = RandomStringUtils.randomAlphabetic(6, 50) + "@gmail.com";
        String username = RandomStringUtils.randomAlphabetic(5, 20);
        String password = RandomStringUtils.randomAlphabetic(8, 16);

        config.getDriver().get("http://localhost:8081/user/create");
        WebElement elementEmail = config.getDriver().findElement(By.id("email"));
        elementEmail.sendKeys(email);
        WebElement elementUsername = config.getDriver().findElement(By.id("userName"));
        elementUsername.sendKeys(username);
        WebElement elementPassword = config.getDriver().findElement(By.id("password"));
        elementPassword.sendKeys(password);
        WebElement elementConfirmPassword = config.getDriver().findElement(By.id("retypePassword"));
        elementConfirmPassword.sendKeys(password);
        WebElement elementSubmit = config.getDriver().findElement(By.id("submit"));
        elementSubmit.click();
        assertEquals("Homepage", config.getDriver().getTitle());
        config.getDriver().close();
    }
}
