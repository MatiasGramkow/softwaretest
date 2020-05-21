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

    //  Create Product
    @GetMapping("/admin/create")
    public String createProduct(Model model)
    {
        Product product = new Product();
        model.addAttribute("product", product);

        return "admin/createProduct";
    }

    @PostMapping("/admin/create/product")
    public String createProduct(@ModelAttribute Product product)
    {
        productService.createOrUpdateProduct(product);

        return "redirect:/admin/users";
    }

    //  Edit Product
    @GetMapping("/admin/update")
    public String updateProduct(@Param("productId") long productId, Model model)
    {
        Product product = productService.findSpecificProduct(productId);
        model.addAttribute("product", product);

        return "admin/updateProduct";
    }
    @PostMapping("/admin/update")
    public String updateProduct(@ModelAttribute Product product)
    {
        productService.createOrUpdateProduct(product);
        return "redirect:/admin/users";
    }

    //  Delete Product
    @PostMapping("/admin/delete")
    public String deleteProduct(@ModelAttribute Product product)
    {
        productService.deleteProduct(product);
        return "redirect:/admin/users";
    }

    //  Read Product
    @GetMapping("/admin/product")
    public String getProduct(@RequestParam("productId") long productId, Model model)
    {
        Product product = productService.findSpecificProduct(productId);
        model.addAttribute("product", product);
        return "admin/product";
    }

    //  Read Products
    @GetMapping("/admin/products")
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

        return "admin/productList";
    }
}
