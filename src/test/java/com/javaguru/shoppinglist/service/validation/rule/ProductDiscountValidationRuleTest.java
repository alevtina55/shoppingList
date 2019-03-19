package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ProductDiscountValidationRuleTest {

    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();
    private Product input;

    @Test
    public void shouldThrowProductValidationException() {
        input = product(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToAllowedDiapason() {
        input = product(new BigDecimal(120));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount should not be less than 0 and greater than 100");
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