package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.ShoppingCartItem;
import com.javaguru.shoppinglist.service.validation.ItemValidationException;

public interface ItemValidationRule {
    void validate(ShoppingCartItem shoppingCartItem);

    default void checkNotNull(ShoppingCartItem shoppingCartItem) {
        if (shoppingCartItem == null) {
            throw new ItemValidationException("Shopping Cart Item must be not null");
        }
    }
}
