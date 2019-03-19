package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueNameValidationRuleTest {

    private Product input;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    @Test
    public void shouldThrowProductValidationExceptionDueToNullName() {
        input = product(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToUnique() {
        input = product("PRODUCT_NAME");

        when(repository.existsByName("PRODUCT_NAME")).thenReturn(true);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name have to be unique");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = product("PRODUCT_NAME");

        when(repository.existsByName("PRODUCT_NAME")).thenReturn(false);

        victim.validate(input);
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        product.setDiscount(new BigDecimal(50));
        product.setPrice(new BigDecimal(50));

        return product;
    }
}