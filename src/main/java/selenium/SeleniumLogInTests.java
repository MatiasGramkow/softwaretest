package selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class SeleniumLogInTests
{
    SeleniumConfig config = new SeleniumConfig();

    @BeforeClass
    public static void setUp() {

    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void T1_userWithCorrectCredentials_ShouldLogin()
    {
        String expected = "Homepage";

        config.getDriver().get("http://localhost:8081/login");
        assertEquals("Login", config.getDriver().getTitle());

        WebElement elementEmail = config.getDriver().findElement(By.id("email"));

        WebElement elementPassword = config.getDriver().findElement(By.id("password"));

        WebElement elementSubmit = config.getDriver().findElement(By.id("submit"));

        elementEmail.sendKeys("test@test.dk");

        elementPassword.sendKeys("testpassword");

        elementSubmit.click();

        String result = config.getDriver().getTitle();

        assertEquals(expected,result);
    }

    @ParameterizedTest(name ="testCase={0}, email={1}, password={2}, expected={3}")
    @CsvFileSource(resources = "/SeleniumData/LoginCredentials.csv", numLinesToSkip = 1)
    public void login_WithInvalidCredentials_ShouldReturnErrorMesssage(String testCase, String email, String password, String expected)
    {
        config.getDriver().get("http://localhost:8081/login");
        assertEquals("Login", config.getDriver().getTitle());

        WebElement elementEmail = config.getDriver().findElement(By.id("email"));

        WebElement elementPassword = config.getDriver().findElement(By.id("password"));

        WebElement elementSubmit = config.getDriver().findElement(By.id("submit"));

        elementEmail.sendKeys(email);

        elementPassword.sendKeys(password);

        elementSubmit.click();
        String error = config.getDriver().findElement(By.id("login-error")).getText();
        assertEquals(expected,error);

        config.getDriver().quit();
    }
}
