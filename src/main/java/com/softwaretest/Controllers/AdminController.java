package com.softwaretest.Controllers;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

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

        return "admin/userList";
    }

    @GetMapping("/user")
    public String getUser(@RequestParam("userId") long userId, Model model)
    {
        try
        {
            User user = userService.findSpecificUser(userId);
            model.addAttribute("user", user);
        }
        catch (Exception e)
        {
            model.addAttribute("Error", e);
        }

        return "admin/user";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute User user)
    {
        try
        {
            userService.deleteUser(user);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return "redirect:/admin/users";
    }



}
