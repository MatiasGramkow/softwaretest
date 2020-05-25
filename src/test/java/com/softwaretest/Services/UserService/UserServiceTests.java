package com.softwaretest.Services.UserService;

import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.User;
import com.softwaretest.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static com.softwaretest.Exceptions.Constants.*;
import static com.softwaretest.Exceptions.ErrorEnum.USER_DOES_NOT_EXIST_ARGUMENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests
{
    private User user;
    private List<User> users;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setup()
    {
        this.user = new User(1L,"matias","password","test@test.dk","ADMIN", 1);
        this.users = new ArrayList<>();
    }


    @Nested
    class CreateOrUpdateTests
    {
        @ParameterizedTest(name = "providedData={0}")
        @CsvFileSource(resources = "/CorrectUserData.csv", numLinesToSkip = 1)
        void createOrUpdateUserWithCorrectData_ShouldReturn_True(Long id)
        {
            //Given
            user.setUserId(id);
            when(userRepository.save(user)).thenReturn(user);

            //When
            Long result = userService.createOrUpdateUser(user);

            //Then
            assertEquals(result, user.getUserId());
        }

        @ParameterizedTest(name = "providedData={0}, expectedError={1}")
        @CsvFileSource(resources = "/IncorrectUsernameData.csv", numLinesToSkip = 1)
        void userNameWithIncorrectData_ShouldThrow_PersonalException(String providedData, String expectedError)
        {
            PersonalException personalException = assertThrows(PersonalException.class, () ->
            {
                //When
                user.setUserName(providedData);
                userService.createOrUpdateUser(user);

            });
            //Then
            assertEquals(personalException.getMessage(), expectedError);

        }

        @ParameterizedTest(name = "providedData={0}, expectedError={1}")
        @CsvFileSource(resources = "/IncorrectPasswordData.csv", numLinesToSkip = 1)
        void passWordWithIncorrectData_ShouldThrow_PersonalException(String providedData, String expectedError)
        {
            PersonalException personalException = assertThrows(PersonalException.class, () ->
            {
                //When
                user.setPassword(providedData);
                userService.createOrUpdateUser(user);

            });
            //Then
            assertEquals(personalException.getMessage(), expectedError);
        }

        @ParameterizedTest(name = "providedData={0}, expectedError={1}")
        @CsvFileSource(resources = "/IncorrectEmailData.csv", numLinesToSkip = 1)
        void emailWithIncorrectData_ShouldThrow_PersonalException(String providedData, String expectedError)
        {
            PersonalException personalException = assertThrows(PersonalException.class, () ->
            {
                //When
                user.setEmail(providedData);
                userService.createOrUpdateUser(user);

            });
            //Then
            assertEquals(personalException.getMessage(), expectedError);
        }
    }

    @Nested
    class DeleteUserTests
    {
        @ParameterizedTest(name = "providedData={0}")
        @CsvFileSource(resources = "/CorrectUserData.csv", numLinesToSkip = 1)
        void deleteUserWithCorrectData_ShouldReturn_True(long id)
        {
            // Given
            Long currentId = id;
            // When
            user.setUserId(currentId);
            boolean result = userService.deleteUser(user);
            // Then
            assertTrue(result);
        }

        @Test
        void deleteUserWhenUserDoesNotExist_ShouldThrow_PersonalException()
        {
            PersonalException personalException = assertThrows(PersonalException.class, () -> {
                //When
                user.setUserId(null);
                userService.deleteUser(user);

            });
            //Then
            assertEquals(personalException.getMessage(), USER_DOES_NOT_EXIST);
        }
    }

    @Nested
    class FindUsersTests
    {
        @ParameterizedTest
        @ValueSource(longs = {1L, 2L, 3L})
        void findSpecificUserWhenUserDoesNotExist_ShouldReturn_Null(Long id)
        {
            // Given none existing user
            user.setUserId(id);
            // When
            User result = userService.findSpecificUser(user.getUserId());
            // Then
            assertNull(result);
        }

        @Test
        void findSpecificUserWhenUserDoesExist_ShouldReturn_User()
        {
            // Given
            when(userRepository.getOne(user.getUserId())).thenReturn(user);
            // When
            User result = userService.findSpecificUser(user.getUserId());
            // Then
            assertEquals(result, user);
        }

        @Test
        void getAllUsersWhenUsersDoesExist_ShouldReturn_Users()
        {
            when(userRepository.findAll()).thenReturn(users);

            List<User> result = userService.getAllUsers();

            assertEquals(result, users);
        }
    }
}