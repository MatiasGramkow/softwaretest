package com.softwaretest.Controllers;

import com.softwaretest.Models.User;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController
{
    @Autowired
    UserService userService;

    // Add Product
    // Delete Product

    // Delete User
    // Read User

    @GetMapping("/users")
    public String getUsers(Model model)
    {
        try
        {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
        }
        catch (Exception e)
        {
            model.addAttribute("Error", e);
        }

        return "Userlist";
    }


}
