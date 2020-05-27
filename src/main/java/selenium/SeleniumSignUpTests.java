package selenium;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Driver;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeleniumSignUpTests {

    SeleniumConfig config = new SeleniumConfig();
    String url = "http://localhost:8081/user/create";


    @BeforeClass
    public static void setUp()
    {

    }

    @AfterClass
    public static void tearDown()
    {

    }

    @Test
    public void T12_whenUserIs_Correctly_SignedUp_thenUserCanLogIn() {
        String email = RandomStringUtils.randomAlphabetic(6, 50) + "@gmail.com";
        String username = RandomStringUtils.randomAlphabetic(5, 20);
        String password = RandomStringUtils.randomAlphabetic(8, 16);

        config.getDriver().get(url);
        assertEquals(config.getDriver().getTitle(),"Create User");
        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);
        WebElement usernameElement = config.getDriver().findElement(By.id("userName"));
        usernameElement.clear();
        usernameElement.sendKeys(username);
        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(password);
        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.sendKeys(password);
        WebElement elementSubmit = config.getDriver().findElement(By.id("submit"));
        elementSubmit.click();
        assertEquals("Homepage", config.getDriver().getTitle());
        config.getDriver().close();
    }

    @ParameterizedTest(name = "testCase={0}, email={1}, username{2}, password={3}, retypePassword{4}, elementID={5}, expected={6}")
    @CsvFileSource(resources = "/SeleniumData/SignUpCredentials.csv", numLinesToSkip = 1)
    public void signUp_WithInvalidCredentials_ShouldReturnErrorMesssage(String testCase, String email, String username, String password, String retypePassword, String elementID, String expected)
    {
        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(password);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(retypePassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id(elementID)).getText();
        assertEquals(expected, response);

        config.getDriver().quit();
    }
}
