package com.softwaretest.Services.ProductService;

import static com.softwaretest.Exceptions.Constants.*;
import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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

    @BeforeEach
    void setup()
    {
        productService = new ProductService();
        product = new Product();
    }

    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/ProductName.csv", numLinesToSkip = 1)
    void createOrUpdateProductWithNoName_ShouldReturn_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            product.setName(providedData);
            productService.createOrUpdateProduct(product);
        });

        assertEquals(personalException.getMessage(), expectedError);
    }

    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/ProductDescription.csv", numLinesToSkip = 1)
    void createOrUpdateProductWithNoDescriptionAndTooLongDescription_ShouldReturn_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            product.setDescription(providedData);
            productService.createOrUpdateProduct(product);
        });

        assertEquals(personalException.getMessage(), expectedError);
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

    @Test
    void deleteProduct_ShouldThrow_PersonalException()
    {
        //Given
        product = null;

        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            productService.deleteProduct(product);
        });

        //Then
        assertEquals(personalException.getMessage(), FIELD_REQUIRED);
    }
}