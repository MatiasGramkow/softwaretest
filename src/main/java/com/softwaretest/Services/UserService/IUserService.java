package com.softwaretest.Services.UserService;

import com.softwaretest.Models.User;

import java.util.List;

public interface IUserService
{
    Long createOrUpdateUser(User user);

    Long updateUserWithoutHash(User user);

    void updateUser(Long id, User user);

    User findSpecificUser(long userId);

    List<User> getAllUsers();

    boolean deleteUser(User user);
}
