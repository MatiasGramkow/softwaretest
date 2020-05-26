package com.softwaretest.Services.ProductService;

import com.softwaretest.Exceptions.ErrorPrerequisites;
import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import com.softwaretest.Repositories.ProductRepository;
import com.softwaretest.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.softwaretest.Exceptions.Constants.*;

@Service
public class ProductService implements IProductService
{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

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
    public boolean deleteProduct(Product product)
    {
        ErrorPrerequisites.notNull(product, FIELD_REQUIRED);
        try
        {
            productRepository.delete(product);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    public void addProductToFavoriteList(Product product, User user)
    {
        Set<Product> favoriteList = user.getProducts();
        favoriteList.add(product);
        user.setProducts(favoriteList);
        System.out.println("User før: " + user);
        userService.updateUserWithoutHash(user);
    }
}
