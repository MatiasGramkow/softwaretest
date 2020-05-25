package com.softwaretest.Services.ProductService;

import static com.softwaretest.Exceptions.Constants.*;
import com.softwaretest.Controllers.AdminController;
import com.softwaretest.Controllers.ProductController;
import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.Product;
import com.softwaretest.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest
{
    @InjectMocks
    private ProductService productService;
    private Product product;

    @BeforeEach
    void setup()
    {
        productService = new ProductService();
        product = new Product();
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

    @ParameterizedTest
    @ValueSource(strings = {""})
    void createOrUpdateProductWithNoName_ShouldReturn_PersonalException(String input)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            product.setName(input);
            productService.createOrUpdateProduct(product);
        });

        assertEquals(personalException.getMessage(), FIELD_REQUIRED);
    }



}