package com.softwaretest.Services.UserService;

import com.softwaretest.Models.User;

import java.util.List;

public interface IUserService
{
    Long createOrUpdateUser(User user);

    User findSpecificUser(long userId);

    List<User> getAllUsers();

    void deleteUser(User user);
}
