package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidationRule {
    private static final BigDecimal PRICE_MIN_VALUE = BigDecimal.ZERO;

    @Override
    public void validate(ProductDTO productDTO) {
        checkNotNull(productDTO);
        BigDecimal price = productDTO.getPrice();
        checkIfPriceNotNull(price);
        checkPriceMinValue(price);
    }

    private void checkIfPriceNotNull(BigDecimal discount) {
        if (discount == null) {
            throw new ProductValidationException("Product price must be not null");
        }
    }

    private void checkPriceMinValue(BigDecimal price) {
        if (price.compareTo(PRICE_MIN_VALUE) <= 0) {
            throw new ProductValidationException("Price should be greater than 0");
        }
    }
}
