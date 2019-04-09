package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductNameValidationRuleTest {

    private ProductNameValidationRule victim = new ProductNameValidationRule();

    private ProductDTO input;

    @Test
    public void shouldThrowProductValidationException() {
        input = productDTO(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToMinLengths() {
        input = productDTO("ab ");

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Name is too short");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = productDTO("product name");

        victim.validate(input);
    }

    private ProductDTO productDTO(String name) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        return productDTO;
    }
}