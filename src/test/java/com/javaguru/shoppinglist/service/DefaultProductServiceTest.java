package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ProductValidationService validationService;

    @InjectMocks
    private DefaultProductService victim;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldFindProduct() {
        when(repository.findById(100L)).thenReturn(product());

        Product result = victim.findProductById(100L);
        assertEquals(product(), result);
    }

    @Test
    public void shouldCreateProductSuccessfully() {
        Product product = product();
        when(repository.insert(product)).thenReturn(product.getId());

        Long result = victim.create(product);
        verify(validationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();

        assertEquals(captorResult, product);
        assertEquals(product.getId(), result);
    }

    @Test
    public void shouldCalculateActualPrice() {
        BigDecimal result = victim.calculateActualPrice(new BigDecimal(40.00),
                new BigDecimal(50.00));
        assertTrue(new BigDecimal(20).compareTo(result) == 0);
    }

    private Product product() {
        Product product = new Product();
        product.setId(100L);

        return product;
    }
}