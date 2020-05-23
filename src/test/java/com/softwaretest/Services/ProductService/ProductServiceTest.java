package com.softwaretest.Services.ProductService;

import com.softwaretest.Controllers.AdminController;
import com.softwaretest.Controllers.ProductController;
import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest
{
    private AdminController adminController;
    private ProductController productController;
    private ProductService productService;


    @BeforeEach
    void setup()
    {
        productController = new ProductController();
        adminController = new AdminController();
        productService = mock(ProductService.class);
    }

    @Test
    void findSpecificProduct_ShouldReturn_Product()
    {
        // Given
            when(productService.findSpecificProduct(anyLong()))
                    .thenReturn(new Product());
        // When
        Product result = productService.findSpecificProduct(1);
        // Then
        assertEquals(Product.class.getName(), result.getClass().getName());
    }





}