package com.softwaretest.Services.UserService;

import com.softwaretest.Controllers.CustomerController;
import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests
{
    @InjectMocks
    private UserService userService;
    private User user;
    private CustomerController customerController;

    @BeforeEach
    void setup()
    {
        this.user = new User(1L,"matias","password","test@test.dk","ADMIN", 1);
        this.customerController = new CustomerController();
    }
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void setUsernameWithCorrectData_ShouldReturn_Username(Long ids)
    {
        // Given
        user.setUserId(ids);
        // When
        Long methodId = userService.createOrUpdateUser(user);
        // Then
        assertNotNull(methodId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ma", "Ti", " "})
    void createOrUpdateUserWithIncorrectData_ShouldThrow_PersonalException(String usernames)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            user.setUserName(usernames);
            userService.createOrUpdateUser(user);

        });
        //Then
        assertEquals(personalException.getMessage(), "Username too short");
    }

    @ParameterizedTest
    @ValueSource(strings = {"MatiasMoeskjaerGramkow", "AVeryLongUsernameIsNotValid", "VulapykUsernameIsAlsoNotValid"})
    void should_Return_False_When_Username_Is_Too_Long(String usernames)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            user.setUserName(usernames);
            userService.createOrUpdateUser(user);

        });
        //Then
        assertEquals(personalException.getMessage(), "Username too long");
    }

    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/UsernameData.csv", numLinesToSkip = 1)
    void userNameWithIncorrectData_ShouldThrow_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            user.setUserName(providedData);
            userService.createOrUpdateUser(user);

        });
        //Then
        assertEquals(personalException.getMessage(), expectedError);

    }

    @Test
    void deleteUser()
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            userService.deleteUser(null);

        });
        //Then
        assertEquals(personalException.getMessage(), "Required field");
    }


    //Tried to do some login stuff, but wasnt sure what to do with dataproviders.
    //@ParameterizedTest
    //@ValueSource(strings = {"xXx1337killerxXx", "svingom123"})
    void login_ShouldReturn_True()
    {
        //Given
        user.setUserName("xXx1337killerxXx");
        user.setPassword("svingom123");
        //When
        boolean result = customerController.login(user, user.getUserName(), user.getPassword());
        //Then
        assertTrue(result);
    }

    @Test
    void login_ShouldReturn_False()
    {
        //Given
        user.setUserName("Ole Nielsen");
        user.setPassword("svingom1234");
        String wrongPW = "svingom123";
        //When
        boolean result = customerController.login(user, user.getUserName(), wrongPW);
        //Then
        assertFalse(result);
    }
}