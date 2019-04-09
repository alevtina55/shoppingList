package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
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

    private ProductDTO input;

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    @Test
    public void shouldThrowProductValidationExceptionDueToNullName() {
        input = productDTO(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null");
    }

    @Test
    public void shouldThrowProductValidationExceptionDueToUnique() {
        input = productDTO("PRODUCT_NAME");

        when(repository.existsByName("PRODUCT_NAME")).thenReturn(true);
        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name have to be unique");
    }

    @Test
    public void shouldValidateSuccessfully() {
        input = productDTO("PRODUCT_NAME");

        when(repository.existsByName("PRODUCT_NAME")).thenReturn(false);

        victim.validate(input);
    }

    private ProductDTO productDTO(String name) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDiscount(new BigDecimal(50));
        productDTO.setPrice(new BigDecimal(50));

        return productDTO;
    }
}