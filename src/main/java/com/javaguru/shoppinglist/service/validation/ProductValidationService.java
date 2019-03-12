package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.rule.ProductValidationRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductValidationService {
    private final Set<ProductValidationRule> validationRules;

    @Autowired
    public ProductValidationService(Set<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        for (ProductValidationRule validationRule : validationRules) {
            validationRule.validate(product);
        }
    }
}
