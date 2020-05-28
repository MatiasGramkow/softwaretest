package com.softwaretest.Controllers;

import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CustomerController
{
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    // Create
    @GetMapping("/user/create")
    public String createUser(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);

        return "customer/createCustomer";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user, Model model)
    {
        try
        {
            userService.createUser(user);
            return "redirect:/";
        }

        catch (DataIntegrityViolationException e)
        {
            // Error Message
            model.addAttribute("Error", "User Already Exists");

            // Error Href
            model.addAttribute("ErrorHref", "create");

            // Error Button Text
            model.addAttribute("ErrorButtonText", "Go Back To Create Page");
            return "error/error";
        }
        catch (PersonalException pe)
        {
            // Error Message
            model.addAttribute("Error", pe.getMessage());

            // Error Href
            model.addAttribute("ErrorHref", "create");

            // Error Button Text
            model.addAttribute("ErrorButtonText", "Go Back To Create Page");
            return "error/error";
        }
    }

    @PostMapping("/postman/user/create")
    public String postManCreateUser()
    {
        User user = new User(null,"Matias2","testpassword","testpassword","test@test.dk","ADMIN",1);
        userService.createUser(user);
        return "redirect:/";
    }

    // Update
    @GetMapping("/user/update")
    public String updateUser(@Param("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "customer/update";
    }

    @PostMapping("/user/update")
    @Transactional
    public String updateUser(@Param("userId") long userId, @ModelAttribute User user)
    {
        userService.updateUserWithoutHash(user);
        return "redirect:/user/details?userId=" + userId;
    }

    // Read One
    @GetMapping("/user/details")
    public String getUser(@RequestParam("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "customer/details";
    }

    // Delete
    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model)
    {
        User user = userService.findSpecificUser(id);
        userService.deleteUser(user);
        return "redirect:/products";
    }


    // Login
    @GetMapping("/loginError")
    public String loginError()
    {
        return "login/loginError";
    }
    @GetMapping("/login")
    public String login()
    {
        try
        {
            userService.getCurrentlyLoggedInUser();
            return "redirect:/";
        }
        catch (Exception e)
        {
            return "login/login";
        }

    }
}
