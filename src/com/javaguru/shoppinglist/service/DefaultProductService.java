package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class DefaultProductService implements ProductService {

    private ProductRepository repository = new ProductRepository();
    private ProductValidationService validationService = new ProductValidationService();

    public Product findProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Long create(Product product) {
        validationService.validate(product);
        checkIfNameIsUnique(product.getName());
        repository.insert(product);
        return product.getId();
    }

    private void checkIfNameIsUnique(String name) {
        if (repository.existsWithName(name)) {
            throw new IllegalArgumentException("Name is not unique");
        }
    }
}
