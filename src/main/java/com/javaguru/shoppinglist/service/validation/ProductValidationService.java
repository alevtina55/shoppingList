package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.rule.ProductValidationRule;

import java.util.Set;

public class ProductValidationService {
    private final Set<ProductValidationRule> validationRules;

    public ProductValidationService(Set<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        for (ProductValidationRule validationRule : validationRules) {
            validationRule.validate(product);
        }
    }
}
