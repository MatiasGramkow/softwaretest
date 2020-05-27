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

public class SeleniumSignUpTests {

    SeleniumConfig config = new SeleniumConfig();



    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void whenUsernameProvidedOnSignUpIsTooShort_thenErrorMessageRecieved(){

        String expected = "Username too short";

        config.getDriver().get("http://localhost:8081/user/create");
        assertEquals("Create User", config.getDriver().getTitle());

        WebElement elementEmail = config.getDriver().findElement(By.id("email"));
        WebElement elementUsername = config.getDriver().findElement(By.id("username"));
        WebElement elementPassword = config.getDriver().findElement(By.id("password"));
        WebElement elementConfirmPassword = config.getDriver().findElement(By.id("retypePassword"));
        WebElement elementSubmit = config.getDriver().findElement(By.id("submit"));

        elementEmail.sendKeys("test@test.dk");
        elementUsername.sendKeys("ttt");
        elementPassword.sendKeys("thispassword");
        elementConfirmPassword.sendKeys("thispassword");
        elementSubmit.click();
        String error = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(expected,error);
    }

    @Test
    public void T12_whenPasswordProvidedOnSignUpIsTooShort_thenErrorMessageRecieved(){
        String expected = "Password too short";

        config.getDriver().get("http://localhost:8081/user/create");
        assertEquals("Create User", config.getDriver().getTitle());

        WebElement elementEmail = config.getDriver().findElement(By.id("email"));
        WebElement elementUsername = config.getDriver().findElement(By.id("userName"));
        WebElement elementPassword = config.getDriver().findElement(By.id("password"));
        WebElement elementConfirmPassword = config.getDriver().findElement(By.id("retypePassword"));
        WebElement elementSubmit = config.getDriver().findElement(By.id("submit"));

        elementEmail.sendKeys("test@test.dk");
        elementUsername.sendKeys("ttt");
        elementPassword.sendKeys("thispassword");
        elementConfirmPassword.sendKeys("1234567");
        elementSubmit.click();
        String error = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(expected,error);
    }

}
