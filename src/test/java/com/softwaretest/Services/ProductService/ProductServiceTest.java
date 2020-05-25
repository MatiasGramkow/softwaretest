package com.softwaretest.Services.ProductService;

import com.softwaretest.Exceptions.Constants;
import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest
{
    @InjectMocks
    private ProductService productService;
    private Product product;
    private Constants constants;

    @BeforeEach
    void setup()
    {
        productService = new ProductService();
        product = new Product();
        constants = new Constants();
    }

    /*
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

        assertEquals(personalException.getMessage(), constants.MISSING_ARGUMENT);
    }

    */




}