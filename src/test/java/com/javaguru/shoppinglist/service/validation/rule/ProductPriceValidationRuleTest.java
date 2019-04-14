package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductPriceValidationRuleTest {

    private ProductPriceValidationRule victim = new ProductPriceValidationRule();

    private ProductDTO input;

    @Test
    public void shouldThrowProductValidationException() {
        input = productDTO(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product price must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToMinValue() {
        input = productDTO(new BigDecimal(-5));
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Price should be greater than 0");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = productDTO(new BigDecimal(20));

        victim.validate(input);
    }

    private ProductDTO productDTO(BigDecimal price) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("PRODUCT_NAME");
        productDTO.setDiscount(new BigDecimal(50));
        productDTO.setPrice(price);

        return productDTO;
    }
}