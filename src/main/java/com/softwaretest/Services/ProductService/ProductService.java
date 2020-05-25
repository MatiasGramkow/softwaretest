package com.softwaretest.Services.ProductService;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import com.softwaretest.Models.Product;
import com.softwaretest.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.softwaretest.Exceptions.Constants.*;

@Service
public class ProductService implements IProductService
{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Long createOrUpdateProduct(Product product)
    {
        ErrorPrerequisites.productNameLength(product.getName());
        ErrorPrerequisites.productDescriptionLength(product.getDescription());
        return productRepository.save(product).getProductId();
    }

    @Override
    public Product findSpecificProduct(long productId)
    {
        return productRepository.getOne(productId);
    }

    @Override
    public void deleteProduct(Product product)
    {
        ErrorPrerequisites.notNull(product, FIELD_REQUIRED);
        productRepository.delete(product);
    }

    @Override
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }


}
