package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.rule.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rule.ProductValidationRule;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {
    private Set<ProductValidationRule> validationRules = new HashSet<>();

    public ProductValidationService() {
        validationRules.add(new ProductNameValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
        validationRules.add(new ProductPriceValidationRule());
    }

    public void validate(Product product) {
        for (ProductValidationRule validationRule : validationRules) {
            validationRule.validate(product);
        }
    }
}
