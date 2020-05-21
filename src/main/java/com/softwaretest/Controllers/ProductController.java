package com.softwaretest.Controllers;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Services.ProductService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    //  Read Product
    @GetMapping("/product/details")
    public String getProduct(@RequestParam("productId") long productId, Model model)
    {
        Product product = productService.findSpecificProduct(productId);
        model.addAttribute("product", product);
        return "product";
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

        return "productList";
    }
}
