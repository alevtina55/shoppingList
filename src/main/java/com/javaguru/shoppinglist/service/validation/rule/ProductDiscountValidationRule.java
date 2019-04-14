package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {
    private static final BigDecimal DISCOUNT_MIN_VALUE = BigDecimal.ZERO;
    private static final BigDecimal DISCOUNT_MAX_VALUE = new BigDecimal(100);
    private static final BigDecimal MIN_PRICE_TO_SET_DISCOUNT = new BigDecimal(20);

    @Override
    public void validate(ProductDTO productDTO) {
        checkNotNull(productDTO);
        BigDecimal discount = productDTO.getDiscount();
        checkIfDiscountNotNull(discount);
        checkIfInAllowedDiapason(discount);
        checkIfAllowedToSetDiscount(productDTO);
    }

    private void checkIfDiscountNotNull(BigDecimal discount) {
        if (discount == null) {
            throw new ProductValidationException("Product discount must be not null");
        }
    }

    private void checkIfInAllowedDiapason(BigDecimal discount) {
        if (discount.compareTo(DISCOUNT_MIN_VALUE) < 0 || discount.compareTo(DISCOUNT_MAX_VALUE) > 0) {
            throw new ProductValidationException("Discount should not be less than "
                    + DISCOUNT_MIN_VALUE + " and greater than " + DISCOUNT_MAX_VALUE);
        }
    }

    private void checkIfAllowedToSetDiscount(ProductDTO productDTO) {
        if (productDTO.getPrice().compareTo(MIN_PRICE_TO_SET_DISCOUNT) < 0
                && productDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProductValidationException("Can not add discount while price is less than "
                    + MIN_PRICE_TO_SET_DISCOUNT);
        }
    }
}
