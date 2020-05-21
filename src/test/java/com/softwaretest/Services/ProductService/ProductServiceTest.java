package com.softwaretest.Services.ProductService;

import com.softwaretest.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest
{

    @Test
    void createOrUpdateProduct()
    {
        String userName = "Niels";

        User user = new User();
        user.setUserName("Matias");

        assertEquals(userName, user.getUserName());
    }

    @Test
    void findSpecificProduct()
    {
    }

    @Test
    void deleteProduct()
    {
    }

    @Test
    void getAllProducts()
    {
    }
}