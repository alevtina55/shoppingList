package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ProductDiscountValidationRuleTest {

    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();
    private ProductDTO input;

    @Test
    public void shouldThrowProductValidationException() {
        input = productDTO(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product discount must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToAllowedDiapason() {
        input = productDTO(new BigDecimal(120));

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount should not be less than 0 and greater than 100");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = productDTO(new BigDecimal(50));

        victim.validate(input);
    }

    public ProductDTO productDTO(BigDecimal discount) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("PRODUCT_NAME");
        productDTO.setPrice(new BigDecimal(20));
        productDTO.setDiscount(discount);

        return productDTO;
    }
}