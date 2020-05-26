package com.softwaretest.Services.ProductService;

import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;

import java.util.List;

public interface IProductService
{
    Long createOrUpdateProduct(Product product);

    Product findSpecificProduct(long productId);

    boolean deleteProduct(Product product);

    List<Product> getAllProducts();

    void addProductToFavoriteList(Product product, User user);

    Product getOneProduct(Long productId);
}
