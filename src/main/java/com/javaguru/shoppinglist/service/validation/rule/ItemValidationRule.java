package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.dto.ShoppingCartItemDTO;
import com.javaguru.shoppinglist.service.validation.ItemValidationException;

public interface ItemValidationRule {
    void validate(ShoppingCartItemDTO shoppingCartItem);

    default void checkNotNull(ShoppingCartItemDTO shoppingCartItemDTO) {
        if (shoppingCartItemDTO == null) {
            throw new ItemValidationException("Shopping Cart Item must be not null");
        }
    }
}
