package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ProductPriceValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductPriceValidationRule victim = new ProductPriceValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product price must be not null");

        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToMinValue() {
        input = product(new BigDecimal(-5));

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Price should be greater than 0");

        victim.validate(input);
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