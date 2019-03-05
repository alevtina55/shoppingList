package com.javaguru.shoppinglist.service.validation.rule;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniqueNameValidationRule implements ProductValidationRule {

    private final ProductRepository productRepository;

    @Autowired
    public ProductUniqueNameValidationRule(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);

        String name = product.getName();
        checkIfNameNotNull(name);
        checkIfNameIsUnique(name);
    }

    private void checkIfNameIsUnique(String name) {
        if (productRepository.existsByName(name)) {
            throw new ProductValidationException("Product name have to be unique");
        }
    }

    private void checkIfNameNotNull(String name) {
        if (name == null) {
            throw new ProductValidationException("Product name must be not null");
        }
    }
}
