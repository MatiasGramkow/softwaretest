package com.softwaretest.Services.UserService;

import com.softwaretest.Models.User;
import com.softwaretest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
