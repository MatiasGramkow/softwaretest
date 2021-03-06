package com.softwaretest.Services.UserService;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public Long createUser(User user)
    {
        ErrorPrerequisites.usernameCheck(user.getUserName());
        ErrorPrerequisites.passwordCheck(user.getPassword());
        ErrorPrerequisites.emailCheck(user.getEmail());
        ErrorPrerequisites.passwordCompare(user.getPassword(),user.getRetypePassword());
        if (user.getRole().equals(""))
        {
            user.setRole("CUSTOMER");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        user.setRetypePassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));

        return userRepository.save(user).getUserId();
    }

    @Override
    public Long updateUserWithoutHash(User user)
    {
        ErrorPrerequisites.usernameCheck(user.getUserName());
        ErrorPrerequisites.emailCheck(user.getEmail());
        User result = userRepository.getOneByUserId(user.getUserId());
        result.setUserName(user.getUserName());
        result.setEmail(user.getEmail());

        return userRepository.save(result).getUserId();
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        User userDB = userRepository.getOneByUserId(getCurrentlyLoggedInUser().getUserId());
        userDB.setUserName(user.getUserName());
        userDB.setEmail(user.getEmail());
        userRepository.save(userDB);
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
    public boolean deleteUser(User user)
    {
        ErrorPrerequisites.idCheck(user.getUserId());
        userRepository.delete(user);
        return true;
    }

    @Override
    public Set<Product> findFiveProducts(User user)
    {
        Set<Product> result = new HashSet<>();
        int counter = 0;
        for (Product product: user.getProducts())
        {
            result.add(product);
            counter++;
            if (counter == 5)
            {
                break;
            }
        }
        return result;
    }


    public User getCurrentlyLoggedInUser() {
        String email;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        email = ((UserDetails) principal).getUsername();

        ErrorPrerequisites.notNull(email, "User does not have an email");

        return userRepository.getOneByEmail(email);

    }

}
