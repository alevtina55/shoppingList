package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        checkNotNull(product);
        BigDecimal discount = product.getDiscount();
        if (discount == null) {
            throw new ProductValidationException("Product name must be not null");
        }

        if (discount.compareTo(BigDecimal.ZERO) < 0 || discount.compareTo(new BigDecimal(100)) > 0) {
            throw new ProductValidationException("Discount should not be less than 0 and greater than 100");
        }
    }
}
