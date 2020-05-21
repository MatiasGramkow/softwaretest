package com.softwaretest.Services.UserService;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;

import java.util.List;

public interface IUserService
{
    void createOrUpdateUser(User user);

    User findSpecificUser(long userId);

    List<User> getAllUsers();

    void deleteUser(User user);
}
