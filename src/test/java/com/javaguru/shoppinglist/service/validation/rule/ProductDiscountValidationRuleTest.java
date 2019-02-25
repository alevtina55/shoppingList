package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ProductDiscountValidationRuleTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();
    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount must be not null");

        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToAllowedDiapason() {
        input = product(new BigDecimal(120));

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Discount should not be less than 0 and greater than 100");

        victim.validate(input);
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = product(new BigDecimal(50));

        victim.validate(input);
    }

    public Product product(BigDecimal discount) {
        Product product = new Product();
        product.setName("PRODUCT_NAME");
        product.setPrice(new BigDecimal(20));
        product.setDiscount(discount);

        return product;
    }
}