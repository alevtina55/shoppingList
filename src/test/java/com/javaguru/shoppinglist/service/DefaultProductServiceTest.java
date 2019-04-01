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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        when(repository.findById(100L)).thenReturn(Optional.ofNullable(product()));

        Product result = victim.findProductById(100L);

        assertThat(result).isEqualTo(product());
    }

    @Test
    public void shouldCreateProductSuccessfully() {
        Product product = product();
        when(repository.insert(product)).thenReturn(product.getId());

        Long result = victim.create(product);
        verify(validationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();

        assertThat(captorResult).isEqualTo(product);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldCalculateActualPrice() {
        BigDecimal result = victim.calculateActualPrice(new BigDecimal(40.00),
                new BigDecimal(50.00));
        assertThat(new BigDecimal(20)).isEqualByComparingTo(result);
    }

    private Product product() {
        Product product = new Product();
        product.setId(100L);

        return product;
    }
}