package com.softwaretest.Controllers;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController
{

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    //  Read Product
    @GetMapping("/product/details")
    public String getProduct(@RequestParam("productId") long productId, Model model)
    {
        Product product = productService.findSpecificProduct(productId);
        model.addAttribute("product", product);
        return "product/details";
    }

    // Add to favorites
    @PostMapping("/product/details")
    public String addToFavoriteList(@ModelAttribute Product product)
    {
        User user = userService.getCurrentlyLoggedInUser();
        Product result = productService.getOneProduct(product.getProductId());
        productService.addProductToFavoriteList(result, user);

        return "redirect:/";
    }

    //  Read Products
    @GetMapping("/products")
    public String getProducts(Model model)
    {
        try
        {
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
        }
        catch (Exception e)
        {
            model.addAttribute("Error", e);
        }

        return "product/products";
    }

    // Favorite products
    @GetMapping("/products/favorites")
    public String getFavoriteProducts(Model model)
    {
        User user = userService.getCurrentlyLoggedInUser();

        model.addAttribute("user", user);

        return "product/favoriteList";
    }
}
