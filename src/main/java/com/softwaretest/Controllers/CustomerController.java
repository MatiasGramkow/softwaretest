package com.softwaretest.Controllers;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CustomerController
{
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    // Add to favorites
    @PostMapping("/product/details")
    public String addToFavoriteList(@ModelAttribute Product product)
    {
        User user = userService.getCurrentlyLoggedInUser();
        System.out.println("Product: " + product);
        productService.addProductToFavoriteList(product, user);

        return "redirect:/";
    }

    // Add to favorites
    @GetMapping("/postman/product")
    public String postmanAddToFavoriteLists()
    {

        return "postmanFavoriteAddHtml";
    }

    @PostMapping("/postman/product")
    public String postmanAddToFavoriteList()
    {
        User user = userService.getCurrentlyLoggedInUser();
        Product product = new Product(1L,"Niels");
        productService.addProductToFavoriteList(product, user);

        return "redirect:/";
    }

    // Create User
    @GetMapping("/user/create")
    public String createUser(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);

        return "customer/createCustomer";
    }

    @PostMapping("/user/create")
    public String createUser(@Valid @ModelAttribute User user, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "customer/createCustomer";
        }
        else
        {
            userService.createOrUpdateUser(user);
            return "redirect:/";
        }
    }

    @PostMapping("/postman/user/create")
    public String postManCreateUser()
    {
        User user = new User(null,"Matias2","12345678","a@a.dk","ADMIN",1);
        userService.createOrUpdateUser(user);
        return "redirect:/";
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
    @GetMapping("/loginError")
    public String loginError() {
        return "login/loginError";
    }

    @GetMapping("/user/update")
    public String updateUser(@Param("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "customer/update";
    }

    @PostMapping("/user/update")
    public String updateUser(@Param("userId") long userId, User user)
    {
        userService.updateUser(userId, user);
        return "redirect:/";
    }

    @GetMapping("/user/details")
    public String getUser(@RequestParam("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "customer/details";
    }



    @RequestMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model)
    {
        User user = userService.findSpecificUser(id);
        userService.deleteUser(user);
        return "redirect:/products";
    }
}
