package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.service.validation.ItemValidationException;

import org.springframework.stereotype.Component;

@Component
public class ItemCountValidationRule implements ItemValidationRule {
    private static final Integer COUNT_MIN_VALUE = 1;

    @Override
    public void validate(ShoppingCartItem shoppingCartItem) {
        Integer count = shoppingCartItem.getCountOfProducts();
        checkNotNull(shoppingCartItem);
        checkIfCountNotNull(count);
        checkCountMinValue(count);
    }

    private void checkCountMinValue(Integer count) {
        if (count < COUNT_MIN_VALUE) {
            throw new ItemValidationException("Count of the product should not be less than "
                    + COUNT_MIN_VALUE);
        }
    }

    private void checkIfCountNotNull(Integer count) {
        if (count == null) {
            throw new ItemValidationException("Product count must be not null");
        }
    }
}
