package com.softwaretest.Controllers;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HomeController
{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getIndex(Model model)
    {
        try
        {
            User user = userService.getCurrentlyLoggedInUser();
            Set<Product> list = userService.findFiveProducts(user);
            model.addAttribute("list", list);
            return "home/index";
        }
        catch (Exception e)
        {
            return "home/index";
        }

    }
}
