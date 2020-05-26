package selenium;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumTestScripts {

    SeleniumConfig config = new SeleniumConfig();

    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public static void tearDown() {

    }


    @Test
    public void whenNameIsUpdated_thenDetailsPageIsLoadedWithUpdatedName(){
        String username = RandomStringUtils.randomAlphabetic(5, 20);

        config.getDriver().get("http://localhost:8081/user/details?userId=5");
        WebElement elementUpdate = config.getDriver().findElement(By.id("update"));
        elementUpdate.click();
        WebElement element = config.getDriver().findElement(By.id("userName"));
        element.clear();
        element.sendKeys(username);
        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();
        String name = config.getDriver().findElement(By.id("username")).getText();
        assertEquals(username, name);
        config.getDriver().close();
    }

    @Test
    public void whenUserIsSignedUp_thenUserCanLogIn() {
        String email = RandomStringUtils.randomAlphabetic(6, 50) + "@gmail.com";
        String username = RandomStringUtils.randomAlphabetic(5, 20);
        String password = RandomStringUtils.randomAlphabetic(8, 16);

        config.getDriver().get("http://localhost:8081/user/create");
        assertEquals(config.getDriver().getTitle(),"Create User");
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
