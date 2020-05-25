package com.softwaretest.Services.UserService;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import com.softwaretest.Models.User;
import com.softwaretest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService
{

    @Autowired
    UserRepository userRepository;


    @Override
    public Long createOrUpdateUser(User user)
    {
        ErrorPrerequisites.usernameLength(user.getUserName());

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public User findSpecificUser(long userId)
    {
        return userRepository.getOne(userId);
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(User user)
    {
        ErrorPrerequisites.notNull(user, "Required field");
        userRepository.delete(user);
    }

}
