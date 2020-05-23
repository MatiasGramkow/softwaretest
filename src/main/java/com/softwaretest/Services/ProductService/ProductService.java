package com.softwaretest.Services.ProductService;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import com.softwaretest.Models.Product;
import com.softwaretest.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService
{
    @Autowired
    ProductRepository productRepository;

    @Override
    public void createOrUpdateProduct(Product product)
    {
        productRepository.save(product);
    }

    @Override
    public Product findSpecificProduct(long productId)
    {
        return productRepository.getOne(productId);
    }

    @Override
    public void deleteProduct(Product product)
    {
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }


}
