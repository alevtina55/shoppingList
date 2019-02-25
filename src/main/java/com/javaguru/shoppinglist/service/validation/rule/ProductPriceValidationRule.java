package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {
    private static final BigDecimal PRICE_MIN_VALUE = BigDecimal.ZERO;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        BigDecimal price = product.getPrice();
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
