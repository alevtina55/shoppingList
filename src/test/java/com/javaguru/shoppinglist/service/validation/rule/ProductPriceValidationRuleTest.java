package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductPriceValidationRuleTest {

    private ProductPriceValidationRule victim = new ProductPriceValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToMinValue() {
        input = product(new BigDecimal(-5));
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Price should be greater than 0");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = product(new BigDecimal(20));

        victim.validate(input);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setDiscount(new BigDecimal(50));
        product.setPrice(price);

        return product;
    }
}