package com.softwaretest.Controllers;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    //  Create Product
    @GetMapping("/admin/product/create")
    public String createProduct(Model model)
    {
        Product product = new Product();
        model.addAttribute("product", product);

        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute Product product)
    {
        productService.createOrUpdateProduct(product);

        return "admin/product/create";
    }

    //  Edit Product
    @GetMapping("/admin/product/update")
    public String updateProduct(@Param("productId") long productId, Model model)
    {
        Product product = productService.findSpecificProduct(productId);
        model.addAttribute("product", product);

        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(@ModelAttribute Product product)
    {
        productService.createOrUpdateProduct(product);
        return "redirect:/admin/users";
    }

    //  Delete Product
    @PostMapping("/admin/product/delete")
    public String deleteProduct(@ModelAttribute Product product)
    {
        productService.deleteProduct(product);
        return "redirect:/admin/users";
    }

}
