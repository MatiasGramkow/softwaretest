package com.softwaretest.Services.ProductService;

import static com.softwaretest.Exceptions.Constants.*;
import com.softwaretest.Exceptions.PersonalException;
import com.softwaretest.Models.Product;
import com.softwaretest.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest
{
    @InjectMocks
    private ProductService productService;
    private Product product;


    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setup()
    {
        product = new Product(1L,"ProductName",null,null,"SomeDescription" );
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void createOrUpdateProduct_ShouldReturn_ProductId(Long id)
    {
        //Given
        product.setProductId(id);
        when(productRepository.save(product)).thenReturn(product);

        //When
        Long result = productService.createOrUpdateProduct(product);

        //Then
        assertEquals(result, product.getProductId());
    }

    //Given
    @ParameterizedTest(name = "providedData={0}, expectedResult={1}")
    @CsvFileSource(resources = "/CorrectProductNameData.csv", numLinesToSkip = 1)
    void createOrUpdateProductWithName_ShouldReturn_ProductId(String providedData, long expectedResult)
    {
        when(productRepository.save(product)).thenReturn(product);

        //When
        product.setName(providedData);
        Long result = productService.createOrUpdateProduct(product);
        //Then
        assertEquals(result, expectedResult);
    }

    //Given
    @ParameterizedTest(name = "providedData={0}, expectedResult={1}")
    @CsvFileSource(resources = "/CorrectProductDescriptionData.csv", numLinesToSkip = 1)
    void createOrUpdateProductWithDescription_ShouldReturn_ProductId(String providedData, long expectedResult)
    {
        when(productRepository.save(product)).thenReturn(product);

        //When
        product.setDescription(providedData);
        Long result = productService.createOrUpdateProduct(product);
        //Then
        assertEquals(result, expectedResult);
    }

    @Test
    void deleteProduct_ShouldReturn_True()
    {
        //Given
        //Product used from setup()

        //When
        boolean result = productService.deleteProduct(product);

        //Then
        assertTrue(result);
    }

    //Given
    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/IncorrectProductNameData.csv", numLinesToSkip = 1)
    void createOrUpdateProductWithNoName_ShouldReturn_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            product.setName(providedData);
            productService.createOrUpdateProduct(product);
        });

        //Then
        assertEquals(personalException.getMessage(), expectedError);
    }

    //Given
    @ParameterizedTest(name = "providedData={0}, expectedError={1}")
    @CsvFileSource(resources = "/IncorrectProductDescriptionData.csv", numLinesToSkip = 1)
    void createOrUpdateProductWithNoDescriptionAndTooLongDescription_ShouldReturn_PersonalException(String providedData, String expectedError)
    {
        PersonalException personalException = assertThrows(PersonalException.class, () -> {
            //When
            product.setDescription(providedData);
            productService.createOrUpdateProduct(product);
        });

        //Then
        assertEquals(personalException.getMessage(), expectedError);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void findSpecificProduct_ShouldReturn_Product(long id)
    {
        // Given
        when(productService.findSpecificProduct(anyLong()))
                .thenReturn(new Product());
        // When
        Product result = productService.findSpecificProduct(id);

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