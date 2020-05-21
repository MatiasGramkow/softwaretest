package com.softwaretest.Services.ProductService;

import com.softwaretest.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest
{

    @Test
    void createOrUpdateProduct()
    {
    }

    @Test
    void findSpecificProduct()
    {
    }

    @Test
    void deleteProduct()
    {
        String userName = "Matias";

        User user = new User();
        user.setUserName("Matias");

        assertEquals(userName, user.getUserName());
    }

    @Test
    void getAllProducts()
    {
        String userName = "Matias";

        User user = new User();
        user.setUserName("Matias");

        assertEquals(userName, user.getUserName());
    }
}