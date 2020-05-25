package com.softwaretest.Controllers;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController
{
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    // Add to favorites
    @PostMapping("/product")
    public String addToFavoriteList(@ModelAttribute Product product)
    {

        //productService.addProductToFavoriteList(product);
        return "";
    }

    // Create User
    @GetMapping("/user/create")
    public String createUser(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);

        return "createCustomer";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user)
    {
        userService.createOrUpdateUser(user);
        return "redirect:/";
    }

    /*
    @PostMapping("/postman/user/create")
    public String postManCreateUser()
    {
        User user = new User(1L,"Matias","matias12","matias@matias.dk","ADMIN",1);
        userService.createOrUpdateUser(user);
        return "redirect:/";
    }

     */

    @GetMapping("/user/update")
    public String updateUser(@Param("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "updateCustomer";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute User user)
    {
        userService.createOrUpdateUser(user);
        return "redirect:/";
    }

    @GetMapping("/user/details")
    public String getUser(@RequestParam("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "customer";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@ModelAttribute User user)
    {
        userService.deleteUser(user);
        return "redirect:/";
    }


    //For testing purposes, don't know the right aproach with Spring
    public boolean login(User user, String userName, String password)
    {
        if (user != null && userName == user.getUserName() && password == user.getPassword())
        {
            return true;
        }
        return false;
    }
}
