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
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

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
    void testss(Long id)
    {
        // Given
        Long currentId = id;
        // When
        user.setUserId(currentId);
        Long result = userService.createOrUpdateUser(user);
        // Then
        assertNotNull(result);
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

    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/PasswordData.csv", numLinesToSkip = 1)
    void passWordWithIncorrectData_ShouldThrow_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            user.setPassword(providedData);
            userService.createOrUpdateUser(user);

        });
        //Then
        assertEquals(personalException.getMessage(), expectedError);
    }

    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/EmailData.csv", numLinesToSkip = 1)
    void emailWithIncorrectData_ShouldThrow_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            user.setEmail(providedData);
            userService.createOrUpdateUser(user);

        });
        //Then
        assertEquals(personalException.getMessage(), expectedError);
    }

    @Test
    void deleteUserWhenUserDoesNotExist_ShouldThrow_PersonalException()
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            userService.deleteUser(null);

        });
        //Then
        assertEquals(personalException.getMessage(), "User does not exist");
    }


}