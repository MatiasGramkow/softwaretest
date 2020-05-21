package com.softwaretest.Services.UserService;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;

import java.util.List;

public interface IUserService
{
    List<User> getAllUsers();

    User findSpecificUser(long userId);

    void deleteUser(User user);
}
