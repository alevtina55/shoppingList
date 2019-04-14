package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ShoppingCartItemDTO;
import com.javaguru.shoppinglist.service.validation.rule.ItemValidationRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemValidationService {
    private final Set<ItemValidationRule> validationRules;

    @Autowired
    public ItemValidationService(Set<ItemValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ShoppingCartItemDTO shoppingCartItem) {
        validationRules.forEach(rule -> rule.validate(shoppingCartItem));
    }
}
