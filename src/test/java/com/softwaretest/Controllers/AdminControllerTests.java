package com.softwaretest.Controllers;

import com.softwaretest.Services.UserService.IUserService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTests
{

    @Test
    void getUsers_ShouldReturn_HTMLNameForGetUsers()
    {
        // Given
        AdminController adminController = new AdminController();
        Model model = mock(Model.class);

        // When
       String result = adminController.getUsers(model);

        // Then
        assertEquals("admin/userList", result);
    }

    @Test
    void getUser()
    {
    }

    @Test
    void deleteUser()
    {
    }
}