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
    String url = "http://localhost:8081/user/create";


    @BeforeClass
    public static void setUp()
    {

    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void whenUsernameProvidedOnSignUpIsTooShort_thenErrorMessageRecieved(){

        String expected = "Username too short";

        config.getDriver().get(url);
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

    @Test
    public void T13_whenEmailIs_Invalid_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "email";
        String username = "testusername";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("email-error")).getText();
        assertEquals(response, "must be a well-formed email address");

        config.getDriver().close();
    }

    @Test
    public void T14_whenEmailFieldIs_Empty_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "";
        String username = "testusername";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("email-error")).getText();
        assertEquals(response, "email missing");

        config.getDriver().close();
    }

    @Test
    public void T15_whenEmailFieldIs_TooLong_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss@s.dk";
        String username = "testusername";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("email-error")).getText();
        assertEquals(response, "Email too long");

        config.getDriver().close();
    }


    @Test
    public void T16_whenUsernameFieldIs_Empty_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "email@gmail.com";
        String username = "";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(response, "Required field");

        config.getDriver().close();
    }

    @Test
    public void T17_whenUsernameFieldIs_TooShort_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "email@gmail.com";
        String username = "test";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(response, "Username too short");

        config.getDriver().close();
    }

    @Test
    public void T18_whenUsernameFieldIs_TooLong_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "email@gmail.com";
        String username = "usernameisfartoolongtowork";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(response, "Username too long");

        config.getDriver().close();
    }

    @Test
    public void T19_whenUsernameFieldContains_Symbols_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "email@gmail.com";
        String username = "&&!)(¤";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(response, "Invalid username");

        config.getDriver().close();
    }

    @Test
    public void T20_whenUsernameFieldContains_Spaces_OnSignUp_thenErrorMessageIsShown()
    {
        String email = "email@gmail.com";
        String username = "Ole Nielsen";
        String testPassword = "testpassword";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("username-error")).getText();
        assertEquals(response, "Invalid username");

        config.getDriver().close();
    }

    @Test
    public void T21_whenPasswordProvidedOnSignUpIs_TooShort_thenErrorMessageRecieved()
    {
        String email = "email@gmail.com";
        String username = "testusername";
        String testPassword = "1234567";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("password-error")).getText();
        assertEquals(response, "Password too short");

        config.getDriver().close();

    }

    @Test
    public void T22_whenPasswordProvidedOnSignUpIs_TooLong_thenErrorMessageRecieved(){
        String email = "email@gmail.com";
        String username = "testusername";
        String testPassword = "abcdefghilkjqsaws";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("password-error")).getText();
        assertEquals(response, "Password too long");

        config.getDriver().close();
    }

    @Test
    public void T23_whenPasswordProvidedOnSignUpIs_Missing_thenErrorMessageRecieved(){
        String email = "email@gmail.com";
        String username = "testusername";
        String testPassword = "";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(testPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("password-error")).getText();
        assertEquals(response, "Required field");

        config.getDriver().close();
    }

    @Test
    public void T24_whenPasswordAndConfirmPasswordDoNotMatch_thenErrorMessageRecieved(){
        String email = "email@gmail.com";
        String username = "testusername";
        String testPassword = "testpassword";
        String confirmPassword = "incorrectpass";

        config.getDriver().get(url);

        WebElement emailElement = config.getDriver().findElement(By.id("email"));
        emailElement.clear();
        emailElement.sendKeys(email);

        WebElement usernameElement = config.getDriver().findElement(By.id("username"));
        usernameElement.clear();
        usernameElement.sendKeys(username);

        WebElement passwordElement = config.getDriver().findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(testPassword);

        WebElement retypePasswordElement = config.getDriver().findElement(By.id("retypePassword"));
        retypePasswordElement.clear();
        retypePasswordElement.sendKeys(confirmPassword);

        WebElement updateBtn = config.getDriver().findElement(By.id("submit"));
        updateBtn.click();

        String response = config.getDriver().findElement(By.id("password-error")).getText();
        assertEquals(response, "Passwords do not match");

        config.getDriver().close();
    }
}
