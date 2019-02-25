package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductNameValidationRuleTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductNameValidationRule victim = new ProductNameValidationRule();

    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name must be not null");

        victim.validate(input);
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToMinLengths() {
        input = product("ab ");

        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Name is too short");

        victim.validate(input);
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