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
        User user = userService.getCurrentlyLoggedInUser();
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

        return "createCustomer";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user)
    {
        userService.createOrUpdateUser(user);
        return "redirect:/";
    }


    @PostMapping("/postman/user/create")
    public String postManCreateUser()
    {
        User user = new User(1L,"Matias2","matias12345","niels@ersej.dk","ADMIN",1);
        userService.createOrUpdateUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login/login";
    }



    @GetMapping("/user/update")
    public String updateUser(@Param("userId") long userId, Model model)
    {
        User user = userService.findSpecificUser(userId);
        model.addAttribute("user", user);

        return "customer/updateCustomer";
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

        return "customer/details";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@ModelAttribute User user)
    {
        userService.deleteUser(user);
        return "redirect:/";
    }
}
