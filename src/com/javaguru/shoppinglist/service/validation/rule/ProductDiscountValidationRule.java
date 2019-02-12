package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    private static final BigDecimal DISCOUNT_MIN_VALUE = BigDecimal.ZERO;
    private static final BigDecimal DISCOUNT_MAX_VALUE = new BigDecimal(100);
    private static final BigDecimal MIN_PRICE_TO_SET_DISCOUNT = new BigDecimal(20);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        BigDecimal discount = product.getDiscount();
        checkIfDiscountNotNull(discount);
        checkIfInAllowedDiapason(discount);
        checkIfAllowedToSetDiscount(product);
    }

    private void checkIfDiscountNotNull(BigDecimal discount) {
        if (discount == null) {
            throw new ProductValidationException("Product name must be not null");
        }
    }

    private void checkIfInAllowedDiapason(BigDecimal discount) {
        if (discount.compareTo(DISCOUNT_MIN_VALUE) < 0 || discount.compareTo(DISCOUNT_MAX_VALUE) > 0) {
            throw new ProductValidationException("Discount should not be less than "
                    + DISCOUNT_MIN_VALUE + " and greater than " + DISCOUNT_MAX_VALUE);
        }
    }

    private void checkIfAllowedToSetDiscount(Product product) {
        if (product.getPrice().compareTo(MIN_PRICE_TO_SET_DISCOUNT) < 0
                && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProductValidationException("Can not add discount while price is less than "
                    + MIN_PRICE_TO_SET_DISCOUNT);
        }
    }
}
