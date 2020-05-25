package com.softwaretest.Services.ProductService;

import com.softwaretest.Models.Product;

import java.util.List;

public interface IProductService
{
    Long createOrUpdateProduct(Product product);

    Product findSpecificProduct(long productId);

    boolean deleteProduct(Product product);

    List<Product> getAllProducts();
}
