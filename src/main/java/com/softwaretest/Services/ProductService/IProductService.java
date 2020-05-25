package com.softwaretest.Services.ProductService;

import com.softwaretest.Models.Product;

import java.util.List;

public interface IProductService
{
    Long createOrUpdateProduct(Product product);

    Product findSpecificProduct(long productId);

    void deleteProduct(Product product);

    List<Product> getAllProducts();
}
