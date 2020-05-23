package com.softwaretest.Services.UserService;

import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
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

    @BeforeEach
    void setup()
    {
        this.user = new User();
    }

    @ParameterizedTest
    @ValueSource(strings = {"MatiasGramkow", "MatiasG", "Ma10as"})
    void should_Return_Correct_When_Username_Is_Correct(String usernames)
    {
        // Given
        String username = usernames;
        // When
        user.setUserName(username);
        // Then
        assertEquals(username, user.getUserName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ma", "Ti", " "})
    void should_Return_False_When_Username_Is_Too_Short(String usernames)
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
}