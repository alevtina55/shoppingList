package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.rule.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductUniqueNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductValidationRule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {
    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;

    @Mock
    private ProductNameValidationRule productNameValidationRule;

    @Mock
    private ProductPriceValidationRule productPriceValidationRule;

    @Mock
    private ProductUniqueNameValidationRule productUniqueNameValidationRule;

    @Captor
    private ArgumentCaptor<ProductDTO> captor;

    private ProductValidationService victim;

    private ProductDTO productDTO = productDTO();

    @Before
    public void setUp() {
        Set<ProductValidationRule> rules = new HashSet<>();

        rules.add(productDiscountValidationRule);
        rules.add(productNameValidationRule);
        rules.add(productPriceValidationRule);
        rules.add(productUniqueNameValidationRule);

        victim = new ProductValidationService(rules);
    }

    @Test
    public void shouldValidateSuccessfully() {
        victim.validate(productDTO);

        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());
        verify(productUniqueNameValidationRule).validate(captor.capture());

        List<ProductDTO> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(productDTO);
    }


    private ProductDTO productDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("PRODUCT_NAME");
        productDTO.setPrice(new BigDecimal(100));
        productDTO.setDiscount(new BigDecimal(50));

        return productDTO;
    }
}