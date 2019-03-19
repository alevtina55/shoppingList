package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductNameValidationRuleTest {

    private ProductNameValidationRule victim = new ProductNameValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToMinLengths() {
        input = product("ab ");

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Name is too short");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = product("product name");

        victim.validate(input);
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}