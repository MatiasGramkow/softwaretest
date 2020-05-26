package com.softwaretest.Services.UserService;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;

import java.util.List;
import java.util.Set;

public interface IUserService
{
    Long createOrUpdateUser(User user);

    Long updateUserWithoutHash(User user);

    void updateUser(Long id, User user);

    User findSpecificUser(long userId);

    List<User> getAllUsers();

    boolean deleteUser(User user);

    Set<Product> findFiveProducts(User user);
}
